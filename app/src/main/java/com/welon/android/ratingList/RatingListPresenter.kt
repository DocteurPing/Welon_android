package com.welon.android.ratingList

import android.util.Log
import com.android.volley.Request
import com.welon.android.databases.entities.Item
import com.welon.android.utils.Constants
import com.welon.android.utils.VolleyRequest

class RatingListPresenter(private val ratingListView: RatingListContract.View): RatingListContract.Presenter {
    override fun sendRate(rateQuality: String, rateQuantity: String, ratePrice: String, item: Item) {
        VolleyRequest.init(ratingListView.getAppContext())
        val url = "${Constants.apilink}/rating/${getTypeItem(item.type!!)}/${item.serverId}"
        val volleyRequest =
            VolleyRequest(url,  ::printJSON) {}
        val header = HashMap<String, String>()
        header["x-access-token"] = ratingListView.getToken().toString()
        val params = HashMap<String, String>()
        val string = "id_${getTypeItem(item.type!!)}"
        params[string] = item.serverId!!
        val body = "quality=$rateQuality&quantity=$rateQuantity&price=$ratePrice"
        volleyRequest.makeRequestToJSON(Request.Method.POST, header, params, body)
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

    private fun printJSON(json: String) {
        Log.d("JSON", json)
        ratingListView.showToastSuccess()
    }
}