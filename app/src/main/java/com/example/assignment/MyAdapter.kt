package com.example.assignment

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class MyAdapter(private val context: Context, private val dataList: ArrayList<DataList>):
    RecyclerView.Adapter<MyAdapter.ViewHolder>() {
    class ViewHolder(itemView: View):RecyclerView.ViewHolder(itemView) {
        val crypto_logo = itemView.findViewById<ImageView>(R.id.crypto_logo)
        val crypto_name = itemView.findViewById<TextView>(R.id.crypto_name)
        val crypto_fullname = itemView.findViewById<TextView>(R.id.crypto_fullname)
        val tv_price = itemView.findViewById<TextView>(R.id.tv_price)
        val tv_status = itemView.findViewById<TextView>(R.id.tv_status)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemView = LayoutInflater.from(context).inflate(R.layout.item_recycler_view,parent,false)
        return ViewHolder(itemView)
    }

    override fun getItemCount(): Int {
        return dataList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val currentItem = dataList[position]
        holder.tv_status.text = currentItem.status.toString()

    }
}