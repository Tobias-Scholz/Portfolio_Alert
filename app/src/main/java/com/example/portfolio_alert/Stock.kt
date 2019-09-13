package com.example.portfolio_alert

import org.json.JSONObject

class Stock (var name: String, var symbol: String, var quote : Double = 0.0, var diff : Double = 0.0, var nominal : Double = 0.0) {
    constructor(name: String, symbol: String, nominal: Double) : this(name, symbol, 0.0, 0.0, nominal)

    fun toJsonString(): JSONObject{
        val string = "{name: $name,\n symbol: $symbol,\n quote: $quote,\n diff: $diff,\n nominal: $nominal}"
        return JSONObject(string)
    }
}