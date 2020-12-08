package com.welon.android.ratingList

import android.content.Context
import com.welon.android.databases.entities.Item

class RatingListContract {

    interface View {
        fun getAppContext(): Context
        fun getToken(): String?
        fun showToastSuccess()
    }

    interface Presenter {
        fun sendRate(rateQuality: String, rateQuantity: String, ratePrice: String, item: Item)
    }
}