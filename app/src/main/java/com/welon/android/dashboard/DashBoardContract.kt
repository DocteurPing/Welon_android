package com.welon.android.dashboard

import com.welon.android.databases.entities.Restaurant

class DashBoardContract {

    interface View {
        fun getAppContext(): DashBoardActivity
        fun updateRestaurant()
        fun navigateToProfile(view: android.view.View)
        fun navigateToRestaurant(restaurant: Restaurant)
        fun setToken(token: String)
        fun getToken(): String?
        fun showNotVerified()
    }

    interface Presenter {
        fun getRestaurants()
        fun getAll(id: String)
        fun authMe()
    }
}