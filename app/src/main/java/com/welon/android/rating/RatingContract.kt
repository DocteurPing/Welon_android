package com.welon.android.rating

import android.content.Context
import com.welon.android.databases.entities.Item

class RatingContract {

    interface View {
        fun getAppContext(): Context
        fun getToken(): String?
    }

    interface Presenter {
        fun sendRate(rate: Int, item: Item)
    }
}