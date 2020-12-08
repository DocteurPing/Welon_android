package com.welon.android.command

import android.content.Context
import com.welon.android.databases.entities.Item
import com.welon.android.databases.entities.Menu

class CommandContract {
    interface View {
        fun displayCommand(command: String)
        fun setMenus(menus: List<Menu>)
        fun getAppContext(): Context
        fun getToken(): String?
        fun setPrice(price: String)
        fun setItems(items: List<Item>)
        fun goToRating(view: android.view.View)
        fun share(view: android.view.View)
        fun setRestaurant(restaurantId: String)
        fun disableNextButton()
    }

    interface Presenter {
        fun getCommand(command: String)
    }
}