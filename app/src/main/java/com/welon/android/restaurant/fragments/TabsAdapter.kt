package com.welon.android.restaurant.fragments

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.welon.android.utils.EnumTypeItem

class TabsAdapter(fm: FragmentManager, private val id: String) : FragmentPagerAdapter(fm,  BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {

    override fun getItem(position: Int): Fragment {
        return when (position) {
            0 -> Menus(id)
            1 -> Items(EnumTypeItem.ENTREE, id)
            2 -> Items(EnumTypeItem.PLAT, id)
            3 -> Items(EnumTypeItem.DESSERT, id)
            else -> Items(EnumTypeItem.BOISSON, id)
        }
    }

    override fun getCount(): Int {
        return 5
    }

    override fun getPageTitle(position: Int): CharSequence {
        return when (position) {
            0 -> "Menus"
            1 -> "Entrees"
            2 -> "Plats"
            3 -> "Desserts"
            else -> "Boisson"
        }
    }
}