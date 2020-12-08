package com.welon.android.login

import android.content.Context

class LoginContract {

    interface View {
        fun onClickTextSignUp(view: android.view.View)
        fun navigateToDashBoard()
        fun getAppContext(): Context
        fun onClickButton(view: android.view.View)
        fun showFailConnect()
        fun show(string: CharSequence)
        fun setToken(token: String)
        fun getToken(): String?
        fun setEmail(email: String)
    }

    interface Presenter {
        fun sign(login: String, pass: String)
        fun authMe()
    }
}