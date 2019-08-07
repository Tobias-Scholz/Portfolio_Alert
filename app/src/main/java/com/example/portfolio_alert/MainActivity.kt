package com.example.portfolio_alert

import android.app.Activity
import android.os.Bundle
import android.util.Log.d
import android.view.View
import android.widget.Button
import androidx.annotation.IdRes
import androidx.appcompat.app.AppCompatActivity
import java.lang.ref.WeakReference
import java.net.URLEncoder
import java.nio.charset.StandardCharsets
import java.text.MessageFormat


class MainActivity : AppCompatActivity() {

    val LATEST_URL = "https://query1.finance.yahoo.com/v7/finance/quote?lang=en-US&region=US&corsDomain=finance.yahoo.com&symbols={0}"
    val button: Button by bindView(R.id.button1)

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        button.setOnClickListener {
            d("Hallo", " Hallo2")
        }

        RetrieveQuotes(WeakReference(this)).execute(MessageFormat.format(LATEST_URL,
            URLEncoder.encode("msf.de", StandardCharsets.UTF_8.name())))
        d("Tobias", "2")

    }

    fun <ViewT : View> Activity.bindView(@IdRes idRes: Int): Lazy<ViewT> {
        return lazy {
            findViewById<ViewT>(idRes)
        }
    }
}
