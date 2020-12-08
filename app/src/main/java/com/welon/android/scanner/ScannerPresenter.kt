package com.welon.android.scanner

import com.welon.android.databases.database.AppDatabase

class ScannerPresenter(private var scannerView: ScannerContract.View): ScannerContract.Presenter {

    override fun checkId(id: String) {
        AppDatabase.INSTANCE?.restaurantDAO()?.getAll()?.forEach {
            if (it.serverId == id) scannerView.navigateToRating(it)
        }
    }
}