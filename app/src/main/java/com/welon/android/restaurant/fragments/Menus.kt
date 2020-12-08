package com.welon.android.restaurant.fragments

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.welon.android.R
import com.welon.android.databases.database.AppDatabase

class Menus(val id: String) : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view: View = inflater.inflate(
            R.layout.fragment_item, container,
            false
        )
        val activity = activity as Context
        view.findViewById<RecyclerView>(R.id.list_item).layoutManager =
            LinearLayoutManager(activity)
        view.findViewById<RecyclerView>(R.id.list_item).adapter =
            MenuAdapter(AppDatabase.INSTANCE?.menuDAO()?.getAllByRestaurantId(id)!!, activity)
        return view
    }

}