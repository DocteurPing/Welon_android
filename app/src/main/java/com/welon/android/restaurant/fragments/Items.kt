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
import com.welon.android.utils.EnumTypeItem

class Items(val type: EnumTypeItem, val id: String) : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val view: View = inflater.inflate(
            R.layout.fragment_item, container,
            false
        )
        val activity = activity as Context
        val listView = view.findViewById<RecyclerView>(R.id.list_item)
        listView.layoutManager =
            LinearLayoutManager(activity)
        listView.adapter = when (type) {
            EnumTypeItem.ENTREE ->
                ItemAdapter(
                    AppDatabase.INSTANCE?.itemDAO()?.getAllByTypeAndRestaurantId(0, id)!!,
                    activity
                )
            EnumTypeItem.PLAT ->
                ItemAdapter(
                    AppDatabase.INSTANCE?.itemDAO()?.getAllByTypeAndRestaurantId(1, id)!!,
                    activity
                )
            EnumTypeItem.DESSERT ->
                ItemAdapter(
                    AppDatabase.INSTANCE?.itemDAO()?.getAllByTypeAndRestaurantId(2, id)!!,
                    activity
                )
            EnumTypeItem.BOISSON ->
                ItemAdapter(
                    AppDatabase.INSTANCE?.itemDAO()?.getAllByTypeAndRestaurantId(3, id)!!,
                    activity
                )
        }
        return view
    }

}