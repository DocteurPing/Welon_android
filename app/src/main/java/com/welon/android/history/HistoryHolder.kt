package com.welon.android.history

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.welon.android.R
import com.welon.android.databases.database.AppDatabase
import com.welon.android.utils.Command

class HistoryHolder(inflater: LayoutInflater, parent: ViewGroup) :
    RecyclerView.ViewHolder(inflater.inflate(R.layout.row_item, parent, false)) {

    private var name: TextView? = null
    private var price: TextView? = null
    var view = itemView


    init {
        name = itemView.findViewById(R.id.item_name)
        price = itemView.findViewById(R.id.price)
    }

    @SuppressLint("SetTextI18n")
    fun bind(command: Command) {
        val restaurant = AppDatabase.INSTANCE?.restaurantDAO()?.getByServerId(command.restaurandId)
        if (restaurant?.name != null) {
            name!!.text = restaurant.name
            price!!.text = command.price.toString() + "€"
        }
    }

}