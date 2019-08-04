package com.example.portfolio_alert

import android.content.Context
import android.net.ConnectivityManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log.d
import kotlinx.android.synthetic.main.activity_main.*
import android.net.NetworkInfo
import android.content.Context.CONNECTIVITY_SERVICE



class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        button1.setOnClickListener {
            d("Hallo", " Hallo2")
        }

        InternetCheck(object : InternetCheck.Consumer {
            override fun accept(internet: Boolean?) {
                Log.d("test", "asdasdas")
            }
        })
    }
}
