package com.welon.android.profile

import android.content.Context

class ProfileContract {
    interface View {
        fun onClickLogOut(view: android.view.View)
        fun onClickGoToChangePassword(view: android.view.View)
        fun onClickGoToHistory(view: android.view.View)
        fun onClickGoToMap(view: android.view.View)
        fun onClickDeleteAccount(view: android.view.View)
        fun getAppContext(): Context
        fun getToken(): String?
        fun getEmail(): String?
    }

    interface Presenter {
        fun deleteAccount()
    }
}