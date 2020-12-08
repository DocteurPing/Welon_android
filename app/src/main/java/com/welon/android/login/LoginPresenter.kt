package com.welon.android.login

import android.util.Log
import com.android.volley.Request
import com.welon.android.utils.Constants.apilink
import com.welon.android.utils.VolleyRequest
import org.json.JSONObject


class LoginPresenter(private var loginView: LoginContract.View) : LoginContract.Presenter {

    override fun sign(
        login: String,
        pass: String
    ) {
        VolleyRequest.init(loginView.getAppContext())
        val url = "$apilink/auth/login/user"
        val volleyRequest = VolleyRequest(url, ::getToken, ::error)
        val header = HashMap<String, String>()
        val params = HashMap<String, String>()
        val body = "email=$login&password=$pass"
        volleyRequest.makeRequestToJSON(Request.Method.POST, header, params, body)
    }

    private fun getToken(json: String) {
        try {
            val obj = JSONObject(json)
            loginView.setToken(obj.getString("token"))
        } catch (e: Exception) {
            Log.d("WELON[Volley]", "Can't get token")
        }
        if (!loginView.getToken().isNullOrEmpty()) {
            loginView.navigateToDashBoard()
        } else {
            println(json)
        }
    }

    override fun authMe() {
        VolleyRequest.init(loginView.getAppContext())
        val url = "$apilink/auth/me"
        val volleyRequest =
            VolleyRequest(url, { loginView.navigateToDashBoard() }, {})
        val header = HashMap<String, String>()
        header["x-access-token"] = loginView.getToken().toString()
        val params = HashMap<String, String>()
        volleyRequest.makeRequestToJSON(Request.Method.GET, header, params, "")
    }

    private fun error(error: String) {
        Log.d("WELON[Volley]", error)
        loginView.showFailConnect()
    }


}