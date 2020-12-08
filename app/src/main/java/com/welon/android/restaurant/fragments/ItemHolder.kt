package com.welon.android.restaurant.fragments

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.welon.android.R
import com.welon.android.databases.entities.Item

class ItemHolder(inflater: LayoutInflater, parent: ViewGroup) :
    RecyclerView.ViewHolder(inflater.inflate(R.layout.row_item, parent, false)) {

    private var name: TextView? = null
    private var price: TextView? = null
    var view = itemView


    init {
        name = itemView.findViewById(R.id.item_name)
        price = itemView.findViewById(R.id.price)
    }

    @SuppressLint("SetTextI18n")
    fun bind(item: Item) {
        name!!.text = item.name
        price!!.text = item.price.toString() + "€"
    }

}