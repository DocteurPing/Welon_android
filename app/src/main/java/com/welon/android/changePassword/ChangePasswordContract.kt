package com.welon.android.changePassword

import android.content.Context

class ChangePasswordContract {
    interface View {
        fun onClickChangePassword(view: android.view.View)
        fun showNotMatchingPassword()
        fun showInvalidPassword()
        fun getAppContext(): Context
        fun showPasswordChanged()
        fun showIncorrectPassword()
    }

    interface Presenter {
        fun checkpassword(pass: String, confirmPass: String): Boolean
        fun changePassword(email: String, oldpassword: String, newPassword: String)
    }
}