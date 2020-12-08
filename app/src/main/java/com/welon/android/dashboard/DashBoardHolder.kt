package com.welon.android.dashboard

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.welon.android.R
import com.welon.android.databases.entities.Restaurant
import com.welon.android.utils.Constants
import com.welon.android.utils.DownloadImageTask


class DashBoardHolder(inflater: LayoutInflater, parent: ViewGroup) :
    RecyclerView.ViewHolder(inflater.inflate(R.layout.row_item_restaurant, parent, false)) {

    private var name: TextView? = null
    private var image: ImageView? = null


    init {
        name = itemView.findViewById(R.id.name_restaurant)
        image = itemView.findViewById(R.id.image_restaurant)
    }

    @SuppressLint("SetTextI18n")
    fun bind(restaurant: Restaurant) {
        if (restaurant.rank != 999) {
            name!!.text =
                restaurant.name.toString() + " - " + restaurant.address + " - Rank: " + restaurant.rank
        } else {
            name!!.text = restaurant.name.toString() + " - " + restaurant.address
        }
        DownloadImageTask(image!!).execute("${Constants.apilink}/images/restaurant/" + restaurant.serverId!!)
    }

}
