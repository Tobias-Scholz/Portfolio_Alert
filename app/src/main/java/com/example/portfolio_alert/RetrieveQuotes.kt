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


internal class RetrieveQuotes(var activity: WeakReference<MainActivity>) : AsyncTask<String, Void, Void>() {

    private var exception: Exception? = null

    override fun doInBackground(vararg urls: String): Void? {
        d("Tobias", "1")
        sendGet(urls[0])
        return null
    }

    fun sendGet(url_param : String) {
        try {
            val url = URL(url_param)

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
                d("Tobias", bid)
            }
        } catch (e : Exception)
        {
            d("Tobias", e.toString())
        }

    }

    override fun onPostExecute(result: Void?) {
        activity.get()?.button?.setText("Peter")
    }
}
