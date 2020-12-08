package com.welon.android.signup

import android.content.Context

class SignupContract {

    interface View {
        fun onClickTextSignIn(view: android.view.View)
        fun onClickNext(view: android.view.View)
        fun showSecond()
        fun showThird()
        fun showInvalidEmail()
        fun showNotMatchingPassword()
        fun showInvalidPassword()
        fun showMissPhone()
        fun showMissUsername()
        fun navigateToDashBoard()
        fun getAppContext(): Context
        fun showFailConnect()
        fun setToken(token: String)
        fun getToken(): String?
    }

    interface Presenter {
        fun checkFirstFields(login: String, pass: String, confirmPass: String): Boolean
        fun sign(
            login: String,
            pass: String,
            confirmPass: String,
            firstname: String,
            lastname: String,
            phone: String
        )
    }
}