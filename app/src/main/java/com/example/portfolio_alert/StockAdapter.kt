package com.example.portfolio_alert

import android.content.Context
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.appcompat.view.menu.ActionMenuItemView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import org.w3c.dom.Text

class StockAdapter(val stock_list: List<Stock>, val context : Context) : RecyclerView.Adapter<StockAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(context).inflate(R.layout.stock_list_item, parent, false))
    }

    override fun getItemCount(): Int {
        return stock_list.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val stock = stock_list[position]
        val temp_symbol = "(${stock.symbol})"
        holder.stock_symbol.text = temp_symbol;
        holder.stock_name.text = stock.name
        holder.stock_quote.text = context.resources.getString(R.string.quote, stock.quote)
        holder.stock_diff_perc.text = context.resources.getString(R.string.diff_perc, stock.diff)
        if (stock.diff > 0){
            holder.layout.setBackgroundResource(R.drawable.item_background_green)
            holder.stock_diff_perc.setTextColor(ContextCompat.getColor(context, R.color.positive))
        }else if (stock.diff < 0){
            holder.layout.setBackgroundResource(R.drawable.item_background_red)
            holder.stock_diff_perc.setTextColor(ContextCompat.getColor(context, R.color.negative))
        }else{
            holder.layout.setBackgroundResource(R.drawable.item_background_grey)
            holder.stock_diff_perc.setTextColor(Color.BLACK)
        }
        holder.stock_nominal.text = stock.nominal.toString()
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        val stock_name: TextView = itemView.findViewById(R.id.stock_name)
        val stock_symbol: TextView = itemView.findViewById(R.id.stock_symbol)
        val stock_quote: TextView = itemView.findViewById(R.id.quote_text)
        val stock_diff_perc: TextView = itemView.findViewById(R.id.diff_perc_text)
        val stock_nominal: TextView = itemView.findViewById(R.id.nominal_text)
        val layout: LinearLayout = itemView.findViewById(R.id.stock_list_layout)
    }
}
