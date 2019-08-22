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
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {

    val stock_list : ArrayList<Stock> = ArrayList()
    val url : String = "https://query1.finance.yahoo.com/v7/finance/quote?lang=en-US&region=US&corsDomain=finance.yahoo.com&symbols={0}"

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

        getQuotes(stock_list)
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

    fun getQuotes(stock_list: ArrayList<Stock>)
    {
        RetrieveQuotes(WeakReference(this), url).execute(*(stock_list.toTypedArray()))
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.add_button -> {
                d("Tobias", "?????")
                val dialog = FireMissilesDialogFragment(this)
                dialog.show(supportFragmentManager, "????")
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    fun createNewStock(name: String, symbol: String, nominal: Double) {
        stock_list.add(Stock(name, symbol, nominal))
        getQuotes(stock_list)
    }
}
