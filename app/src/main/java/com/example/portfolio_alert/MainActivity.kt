package com.example.portfolio_alert

import android.app.Activity
import android.os.Bundle
import android.util.Log.d
import android.view.View
import androidx.annotation.IdRes
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_main.*
import java.lang.ref.WeakReference
import java.net.URLEncoder
import java.nio.charset.StandardCharsets
import java.text.MessageFormat
import kotlin.collections.ArrayList


class MainActivity : AppCompatActivity() {

    val LATEST_URL = "https://query1.finance.yahoo.com/v7/finance/quote?lang=en-US&region=US&corsDomain=finance.yahoo.com&symbols={0}"
    val stock_list : ArrayList<Stock> = ArrayList()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(findViewById(R.id.my_toolbar))

        stock_list.add(Stock("Microsoft", "msf.de", 120.3, 2.3, 24.0))
        stock_list.add(Stock("SAP", "sap.de", 103.4, -2.3, 6.0))
        stock_list.add(Stock("AT&T", "soba.de", 25.4, 0.0, 24.0))
        stock_list.add(Stock("McDonalds", "mdo.de", 195.4, 1.0, 2.34224))
        stock_list.add(Stock("PepsiCo", "mdo.de", 114.4, 0.3, 0.344))
        stock_list.add(Stock("Wirecard", "wdi.de", 144.5, -0.3, 2.3323))
        stock_list.add(Stock("Amazon", "amz.de", 1611.5, 0.7, 0.0234))
        
        stock_list.sortByDescending { it.diff }

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
