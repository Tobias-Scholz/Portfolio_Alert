package com.example.portfolio_alert

import org.json.JSONArray

class StockManager {
    val stocks: ArrayList<Stock> = ArrayList()

    fun getStockJson() : JSONArray{
        val jsonArray : JSONArray = JSONArray()
        stocks.forEach {
            jsonArray.put(it)
        }
        return jsonArray
    }
}