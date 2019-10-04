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
import android.os.AsyncTask
import android.view.*
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.RecyclerView
import androidx.room.Room

class MainActivity : AppCompatActivity() {

    val stockManager : StockManager = StockManager()
    val url : String = "https://query1.finance.yahoo.com/v7/finance/quote?lang=en-US&region=US&corsDomain=finance.yahoo.com&symbols={0}"
    var stocks : List<Stock>? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(findViewById(R.id.my_toolbar))

       /* stockManager.stocks.add(Stock("Microsoft", "msf.de", 24.0))
        stockManager.stocks.add(Stock("SAP", "sap.de", 6.0))
        stockManager.stocks.add(Stock("AT&T", "soba.de", 24.0))
        stockManager.stocks.add(Stock("McDonalds", "mdo.de", 2.34224))
        stockManager.stocks.add(Stock("PepsiCo", "mdo.de", 0.344))
        stockManager.stocks.add(Stock("Wirecard", "wdi.de", 2.3323))
        stockManager.stocks.add(Stock("Amazon", "amz.de", 0.0234))*/

        val db = Room.databaseBuilder(
            applicationContext,
            AppDatabase::class.java, "database"
        ).fallbackToDestructiveMigration().build()

        AsyncTask.execute {
            stocks = db.stockDao().getAll()

            stocks?.sortedByDescending { it.diff }

            runOnUiThread {
                rv_stock_list.layoutManager = LinearLayoutManager(this)
                rv_stock_list.adapter = StockAdapter(stocks!!, this)
            }

            getQuotes(stocks!!)
        }
    }

    fun refresh_rv(){
        stocks?.sortedByDescending { it.diff }
        rv_stock_list.adapter!!.notifyDataSetChanged()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    fun getQuotes(stock_list: List<Stock>)
    {
        RetrieveQuotes(WeakReference(this), url).execute(*(stock_list.toTypedArray()))
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.add_button -> {
                d("Tobias", "?????")
                val dialog = CreateNewStockDialogFragment(this)
                dialog.show(supportFragmentManager, "????")
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    fun createNewStock(name: String, symbol: String, nominal: Double) {
        stockManager.stocks.add(Stock(null, name, symbol, nominal))
        getQuotes(stockManager.stocks)
    }
}
