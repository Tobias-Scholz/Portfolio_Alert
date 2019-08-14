package com.example.portfolio_alert

import android.app.Activity
import android.os.Bundle
import android.util.Log.d
import androidx.annotation.IdRes
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_main.*
import java.lang.ref.WeakReference
import java.net.URLEncoder
import java.nio.charset.StandardCharsets
import java.text.MessageFormat
import kotlin.collections.ArrayList
import android.widget.PopupWindow
import android.widget.LinearLayout
import android.content.Context
import android.view.*

class MainActivity : AppCompatActivity() {

    val stock_list : ArrayList<Stock> = ArrayList()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(findViewById(R.id.my_toolbar))

        stock_list.add(Stock("Microsoft", "msf.de", 24.0))
        stock_list.add(Stock("SAP", "sap.de", 6.0))
        stock_list.add(Stock("AT&T", "soba.de", 24.0))
        stock_list.add(Stock("McDonalds", "mdo.de", 2.34224))
        stock_list.add(Stock("PepsiCo", "mdo.de", 0.344))
        stock_list.add(Stock("Wirecard", "wdi.de", 2.3323))
        stock_list.add(Stock("Amazon", "amz.de", 0.0234))
        
        stock_list.sortByDescending { it.diff }

        rv_stock_list.layoutManager = LinearLayoutManager(this)
        rv_stock_list.adapter = StockAdapter(stock_list, this)

        RetrieveQuotes(WeakReference(this), "https://query1.finance.yahoo.com/v7/finance/quote?lang=en-US&region=US&corsDomain=finance.yahoo.com&symbols={0}").execute(*(stock_list.toTypedArray()))
    }

    fun <ViewT : View> Activity.bindView(@IdRes idRes: Int): Lazy<ViewT> {
        return lazy {
            findViewById<ViewT>(idRes)
        }
    }

    fun refresh_rv(){
        stock_list.sortByDescending { it.diff }
        rv_stock_list.adapter!!.notifyDataSetChanged()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.add_button -> {
                create_stock_form()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    fun create_stock_form()
    {
        val inflater = getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        val popupView = inflater.inflate(R.layout.new_stock_form, null)

        // create the popup window
        val width = LinearLayout.LayoutParams.WRAP_CONTENT
        val height = LinearLayout.LayoutParams.WRAP_CONTENT
        val focusable = true // lets taps outside the popup also dismiss it
        val popupWindow = PopupWindow(popupView, width, height, focusable)

        // show the popup window
        // which view you pass in doesn't matter, it is only used for the window tolken
        popupWindow.showAtLocation(findViewById(R.id.add_button), Gravity.CENTER, 0, 0)
    }
}
