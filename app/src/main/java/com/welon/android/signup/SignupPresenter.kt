package com.welon.android.signup

import android.util.Log
import com.android.volley.Request
import com.welon.android.utils.Constants.apilink
import com.welon.android.utils.EnumReturnCheckPassword
import com.welon.android.utils.VolleyRequest
import com.welon.android.utils.checkPassword
import org.json.JSONObject

class SignupPresenter(private var signupView: SignupContract.View) : SignupContract.Presenter {

    override fun sign(
        login: String,
        pass: String,
        confirmPass: String,
        firstname: String,
        lastname: String,
        phone: String
    ) {
        // TODO add check pseudo
        if (phone.isEmpty()) {
            signupView.showMissPhone()
            return
        }
        VolleyRequest.init(signupView.getAppContext())
        val url = "$apilink/auth/register/user"
        val volleyRequest = VolleyRequest(url, ::getToken, ::error)
        val header = HashMap<String, String>()
        val params = HashMap<String, String>()
        val body =
            "email=$login&password=$pass&firstname=$firstname&lastname=$lastname&phone=$phone"
        volleyRequest.makeRequestToJSON(Request.Method.POST, header, params, body)
    }

    private fun getToken(json: String) {
        try {
            val obj = JSONObject(json)
            signupView.setToken(obj.getString("token"))
        } catch (e: Exception) {
            Log.d("WELON[Volley]", "Can't get token")
        }
        if (!signupView.getToken().isNullOrEmpty()) {
            signupView.navigateToDashBoard()
        } else {
            println(json)
        }
    }

    override fun checkFirstFields(login: String, pass: String, confirmPass: String): Boolean {
        val check = checkPassword(pass, confirmPass)
        if (!checkEmail(login)) {
            signupView.showInvalidEmail()
            return false
        }
        if (check == EnumReturnCheckPassword.INVALID) {
            signupView.showInvalidPassword()
            return false
        } else if (check == EnumReturnCheckPassword.NOT_MATCHING) {
            signupView.showNotMatchingPassword()
            return false
        }
        signupView.showSecond()
        return true
    }

    private fun checkEmail(login: String): Boolean =
        (login.isNotEmpty() && android.util.Patterns.EMAIL_ADDRESS.matcher(login).matches())

    private fun error(error: String) {
        Log.d("WELON[Volley]", error)
        signupView.showFailConnect()
    }
}