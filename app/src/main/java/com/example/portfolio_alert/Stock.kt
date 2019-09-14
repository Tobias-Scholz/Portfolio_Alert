package com.example.portfolio_alert

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Stock(
    @PrimaryKey @ColumnInfo(name = "name") var name: String,
    @ColumnInfo(name = "symbol") var symbol: String,
    @ColumnInfo(name = "symbol") var nominal: Double,
    var quote: Double = 0.0,
    var diff: Double = 0.0
)