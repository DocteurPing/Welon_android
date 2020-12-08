package com.welon.android.history

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.welon.android.utils.Command

class HistoryAdapter(
    private val list: List<Command>,
    private val view: HistoryContract.View
) : RecyclerView.Adapter<HistoryHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HistoryHolder {
        val inflater = LayoutInflater.from(parent.context)
        return HistoryHolder(inflater, parent)
    }

    override fun getItemCount(): Int = list.size

    override fun onBindViewHolder(holder: HistoryHolder, position: Int) {
        val entite: Command = list[position]
        holder.itemView.setOnClickListener {
            view.navigateToCommand(entite.id)
        }
        holder.bind(entite)
    }

}