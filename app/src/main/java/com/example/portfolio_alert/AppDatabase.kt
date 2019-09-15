package com.example.portfolio_alert

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = arrayOf(Stock::class), version = 2, exportSchema = false)
abstract class AppDatabase : RoomDatabase(){
    abstract fun stockDao(): StockDao
}