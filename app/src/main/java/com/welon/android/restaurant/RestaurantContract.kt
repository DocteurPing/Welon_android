package com.welon.android.restaurant

class RestaurantContract {

    interface View {
        fun getAppContext(): RestaurantActivity
        fun navigateToScanner(view: android.view.View)
        fun getToken(): String?
    }

}