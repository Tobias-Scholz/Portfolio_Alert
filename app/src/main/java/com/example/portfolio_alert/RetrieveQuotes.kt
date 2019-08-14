package com.example.portfolio_alert;

import android.net.wifi.WifiConfiguration.AuthAlgorithm.strings
import android.os.AsyncTask;
import android.os.Build;
import android.util.Log.d
import androidx.appcompat.app.AppCompatActivity
import org.json.JSONArray
import org.json.JSONObject
import java.lang.ref.WeakReference
import java.lang.reflect.Executable

import java.net.HttpURLConnection
import java.net.URL
import java.net.URLEncoder
import java.nio.charset.StandardCharsets
import java.text.MessageFormat


internal class RetrieveQuotes(var activity: WeakReference<MainActivity>, var url : String) : AsyncTask<Stock, Void, Void>() {

    private var exception: Exception? = null

    override fun doInBackground(vararg symbols: Stock): Void? {
        symbols.forEach {
            sendGet(it, url)
        }
        return null
    }

    fun sendGet(stock : Stock, url_param : String) {
        try {
            val url = URL(MessageFormat.format(url_param, URLEncoder.encode(stock.symbol, StandardCharsets.UTF_8.name())))

            with(url.openConnection() as HttpURLConnection) {
                requestMethod = "GET"  // optional default is GET

                d("Tobias","\nSent 'GET' request to URL : $url; Response Code : $responseCode")

                var response : String = ""

                inputStream.bufferedReader().use {
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                        it.lines().forEach { line ->
                            response += line
                        }
                    }
                }

                val jsonObject = JSONObject(response.substring(response.indexOf("{"), response.lastIndexOf("}") + 1))
                val bid = jsonObject.getJSONObject("quoteResponse").getJSONArray("result").getJSONObject(0).getString("bid").toString()
                val diff = jsonObject.getJSONObject("quoteResponse").getJSONArray("result").getJSONObject(0).getString("regularMarketChangePercent").toString()
                stock.quote = bid.toDouble()
                stock.diff = diff.toDouble()
                d("Tobias", "${stock.symbol} : ${stock.diff} : $bid")
            }
        } catch (e : Exception)
        {
            d("Tobias", "${stock.symbol} $e")
        }

    }

    override fun onPostExecute(result: Void?) {
        activity.get()!!.refresh_rv()
    }
}
