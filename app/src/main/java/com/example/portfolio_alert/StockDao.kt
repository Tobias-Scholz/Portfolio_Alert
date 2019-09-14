package com.example.portfolio_alert

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query

@Dao
interface StockDao{
    @Query("SELECT * FROM stock")
    fun getAll(): List<Stock>

    @Insert
    fun insertAll(vararg stocks: Stock)

    @Delete
    fun delete(stock: Stock)
}