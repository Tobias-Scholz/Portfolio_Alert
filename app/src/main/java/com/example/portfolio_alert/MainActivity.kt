package com.example.portfolio_alert

import android.content.Context
import android.net.ConnectivityManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log.d
import kotlinx.android.synthetic.main.activity_main.*
import android.net.NetworkInfo
import android.content.Context.CONNECTIVITY_SERVICE
import android.os.Build
import java.net.HttpURLConnection
import java.net.URL
import java.net.URLEncoder
import java.nio.charset.StandardCharsets
import java.text.MessageFormat


class MainActivity : AppCompatActivity() {

    val LATEST_URL = "https://query1.finance.yahoo.com/v7/finance/quote?lang=en-US&region=US&corsDomain=finance.yahoo.com&symbols={0}"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        button1.setOnClickListener {
            d("Hallo", " Hallo2")
        }

        RetrieveQuotes().execute(MessageFormat.format(LATEST_URL,
            URLEncoder.encode("msf.de", StandardCharsets.UTF_8.name())))

    }


}
