package com.example.portfolio_alert

class Stock (var name: String, var symbol: String, var quote : Double = 0.0, var diff : Double = 0.0, var nominal : Double = 0.0) {
    constructor(name: String, symbol: String, nominal: Double) : this(name, symbol, 0.0, 0.0, nominal)
}