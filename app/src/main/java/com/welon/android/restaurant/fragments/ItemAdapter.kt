package com.welon.android.restaurant.fragments

import android.annotation.SuppressLint
import android.app.AlertDialog
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.welon.android.R
import com.welon.android.databases.entities.Item
import kotlinx.android.synthetic.main.popup_item.view.*

class ItemAdapter(
    private val list: List<Item>,
    private val context: Context
) : RecyclerView.Adapter<ItemHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemHolder {
        val inflater = LayoutInflater.from(parent.context)
        return ItemHolder(inflater, parent)
    }

    override fun getItemCount(): Int = list.size

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: ItemHolder, position: Int) {
        val entite: Item = list[position]
        holder.view.setOnClickListener {
            val builder = AlertDialog.Builder(context)
            val dialogViewTrack = View.inflate(context, R.layout.popup_item, null)
            dialogViewTrack.name_item.text = entite.name
            dialogViewTrack.desc_item.text = entite.description
            dialogViewTrack.price_item.text = entite.price.toString() + "€"
            builder.setView(dialogViewTrack)

            val alertDialog = builder.create()
            alertDialog.show()
        }
        holder.bind(entite)
    }

}