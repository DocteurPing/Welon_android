package com.welon.android.rating

import com.android.volley.Request
import com.welon.android.databases.entities.Item
import com.welon.android.utils.Constants
import com.welon.android.utils.VolleyRequest

class RatingPresenter(private val ratingView: RatingContract.View): RatingContract.Presenter {
    override fun sendRate(rate: Int, item: Item) {
        VolleyRequest.init(ratingView.getAppContext())
        val url = "${Constants.apilink}/rating/${getTypeItem(item.type!!)}/${item.serverId}"
        val volleyRequest =
            VolleyRequest(url, {} , {})
        val header = HashMap<String, String>()
        header["x-access-token"] = ratingView.getToken().toString()
        val params = HashMap<String, String>()
        volleyRequest.makeRequestToJSON(Request.Method.GET, header, params, "")
    }

    private fun getTypeItem(type: Int): String {
        when (type) {
            0 -> return "entrees"
            1 -> return "plats"
            2 -> return "desserts"
            3 -> return "boissons"
            4 -> return "menus"
        }
        return "ERROR"
    }
}