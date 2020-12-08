package com.welon.android.scanner

import com.welon.android.databases.entities.Restaurant

class ScannerContract {

    interface View {
        fun navigateToRating(restaurant: Restaurant)
        fun navigateToCommand(commandId: String)
    }

    interface Presenter {
        fun checkId(id: String)
    }
}