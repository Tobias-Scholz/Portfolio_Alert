package com.example.portfolio_alert;

import android.net.wifi.WifiConfiguration.AuthAlgorithm.strings
import android.os.AsyncTask;
import android.os.Build;
import android.util.Log.d

import java.net.HttpURLConnection
import java.net.URL


internal class RetrieveQuotes : AsyncTask<String, Void, Void>() {

    private var exception: Exception? = null

    override fun doInBackground(vararg urls: String): Void? {
        sendGet(urls[0])
        return null
    }

    fun sendGet(url_param : String) {
        val url = URL(url_param)

        with(url.openConnection() as HttpURLConnection) {
            requestMethod = "GET"  // optional default is GET

            d("Tobias","\nSent 'GET' request to URL : $url; Response Code : $responseCode")

            inputStream.bufferedReader().use {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                    it.lines().forEach { line ->
                        d("Tobias", line)
                    }
                }
            }
        }
    }

    override fun onPostExecute(result: Void?) {

    }
}
