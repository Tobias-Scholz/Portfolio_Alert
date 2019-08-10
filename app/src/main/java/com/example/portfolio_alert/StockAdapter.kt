package com.example.portfolio_alert

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.appcompat.view.menu.ActionMenuItemView
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import org.w3c.dom.Text

class StockAdapter(val stock_list: ArrayList<Stock>, val context : Context) : RecyclerView.Adapter<StockAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(context).inflate(R.layout.stock_list_item, parent, false))
    }

    override fun getItemCount(): Int {
        return stock_list.size
    }

    override fun onBindViewHolder(holder: StockAdapter.ViewHolder, position: Int) {
        holder.stock_name.text = stock_list[position].name
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        val stock_name: TextView = itemView.findViewById(R.id.stock_name)
    }
}
