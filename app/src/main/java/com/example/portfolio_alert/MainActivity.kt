package com.example.portfolio_alert

import android.app.Activity
import android.os.Bundle
import android.util.Log.d
import android.view.View
import android.widget.Button
import androidx.annotation.IdRes
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_main.*
import java.lang.ref.WeakReference
import java.net.URLEncoder
import java.nio.charset.StandardCharsets
import java.text.MessageFormat


class MainActivity : AppCompatActivity() {

    val LATEST_URL = "https://query1.finance.yahoo.com/v7/finance/quote?lang=en-US&region=US&corsDomain=finance.yahoo.com&symbols={0}"
    val stock_list : ArrayList<Stock> = ArrayList()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        stock_list.add(Stock("Microsoft", "msf.de"))
        stock_list.add(Stock("SAP", "sap.de"))

        setContentView(R.layout.activity_main)

        RetrieveQuotes(WeakReference(this)).execute(MessageFormat.format(LATEST_URL,
            URLEncoder.encode("msf.de", StandardCharsets.UTF_8.name())))
        d("Tobias", "2")

        rv_stock_list.layoutManager = LinearLayoutManager(this)
        rv_stock_list.adapter = StockAdapter(stock_list, this)
    }

    fun <ViewT : View> Activity.bindView(@IdRes idRes: Int): Lazy<ViewT> {
        return lazy {
            findViewById<ViewT>(idRes)
        }
    }
}
