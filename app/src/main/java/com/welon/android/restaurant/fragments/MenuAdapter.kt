package com.welon.android.restaurant.fragments

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.RecyclerView
import com.welon.android.databases.database.AppDatabase
import com.welon.android.databases.entities.Menu
import com.welon.android.menu.MenuActivity
import com.welon.android.utils.Constants

class MenuAdapter(
    private val list: List<Menu>,
    private val context: Context
) : RecyclerView.Adapter<MenuHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MenuHolder {
        val inflater = LayoutInflater.from(parent.context)
        return MenuHolder(inflater, parent)
    }

    override fun getItemCount(): Int = list.size

    override fun onBindViewHolder(holder: MenuHolder, position: Int) {
        val entite: Menu = list[position]
        holder.view.setOnClickListener {
            val intent = Intent(context, MenuActivity::class.java)
            val restaurant = AppDatabase.INSTANCE?.restaurantDAO()?.getByServerId(entite.restaurantId.toString())!!
            intent.putExtra(Constants.EXTRA_RESTAURANT, restaurant)
            intent.putExtra(Constants.EXTRA_MENU, entite)
            startActivity(context, intent, null)
        }
        holder.bind(entite)
    }

}