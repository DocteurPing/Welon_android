package com.welon.android.dashboard

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.welon.android.databases.entities.Restaurant

class DashBoardAdapter(
    private val list: List<Restaurant>,
    private val view: DashBoardContract.View
) : RecyclerView.Adapter<DashBoardHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DashBoardHolder {
        val inflater = LayoutInflater.from(parent.context)
        return DashBoardHolder(inflater, parent)
    }

    override fun getItemCount(): Int = list.size

    override fun onBindViewHolder(holder: DashBoardHolder, position: Int) {
        val entite: Restaurant = list[position]
        holder.itemView.setOnClickListener {
            view.navigateToRestaurant(list[position])
        }
        holder.bind(entite)
    }

}
