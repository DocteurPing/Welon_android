package com.welon.android.changePassword

import com.android.volley.Request
import com.welon.android.utils.Constants
import com.welon.android.utils.EnumReturnCheckPassword
import com.welon.android.utils.VolleyRequest
import com.welon.android.utils.checkPassword

class ChangePasswordPresenter(private val changepasswordView: ChangePasswordContract.View) :
    ChangePasswordContract.Presenter {

    override fun checkpassword(pass: String, confirmPass: String): Boolean {
        val check = checkPassword(pass, confirmPass)
        if (check == EnumReturnCheckPassword.INVALID) {
            changepasswordView.showInvalidPassword()
            return false
        } else if (check == EnumReturnCheckPassword.NOT_MATCHING) {
            changepasswordView.showNotMatchingPassword()
            return false
        }
        return true
    }

    override fun changePassword(email: String, oldpassword: String, newPassword: String) {
        VolleyRequest.init(changepasswordView.getAppContext())
        val url = "${Constants.apilink}/auth/password/reset/user"
        val volleyRequest = VolleyRequest(
            url,
            { changepasswordView.showPasswordChanged() },
            { changepasswordView.showIncorrectPassword() })
        val header = HashMap<String, String>()
        val params = HashMap<String, String>()
        val body = "email=$email&old_password=$oldpassword&new_password=$newPassword"
        volleyRequest.makeRequestToJSON(Request.Method.POST, header, params, body)
    }
}