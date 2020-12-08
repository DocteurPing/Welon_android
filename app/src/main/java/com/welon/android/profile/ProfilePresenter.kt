package com.welon.android.profile

import com.android.volley.Request
import com.welon.android.utils.Constants
import com.welon.android.utils.VolleyRequest

class ProfilePresenter(private var profileView: ProfileContract.View) : ProfileContract.Presenter {

    override fun deleteAccount() {
        VolleyRequest.init(profileView.getAppContext())
        val url = "${Constants.apilink}/auth/user"
        val volleyRequest = VolleyRequest(url, {}, {})
        val header = HashMap<String, String>()
        header["x-access-token"] = profileView.getToken().toString()
        val params = HashMap<String, String>()
        val email = profileView.getEmail().toString()
        val body = "email=$email"
        volleyRequest.makeRequestToJSON(Request.Method.DELETE, header, params, body)
    }
}