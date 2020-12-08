package com.welon.android.history

import com.android.volley.Request
import com.welon.android.utils.Command
import com.welon.android.utils.Constants
import com.welon.android.utils.VolleyRequest
import org.json.JSONArray
import org.json.JSONObject

class HistoryPresenter(private val historyActivity: HistoryActivity) : HistoryContract.Presenter {
    override fun getListHistory() {
        VolleyRequest.init(historyActivity.getAppContext())
        val url = "${Constants.apilink}/command/user"
        val volleyRequest =
            VolleyRequest(url, ::getCommandList) {}
        val header = HashMap<String, String>()
        header["x-access-token"] = historyActivity.getToken().toString()
        val params = HashMap<String, String>()
        volleyRequest.makeRequestToJSON(Request.Method.GET, header, params, "")
    }

    private fun getCommandList(json: String) {
        val listCommand = ArrayList<Command>()
        val listRestaurant = JSONArray(json)
        for (i in 0 until listRestaurant.length()) {
            val command = Command()
            val item = JSONObject(listRestaurant.getString(i))
            command.restaurandId = item.getString("id_restaurant")
            command.price = item.getString("price").toInt()
            command.id = item.getString("_id")
            listCommand.add(command)
        }
        historyActivity.updateCommand(listCommand)
    }

}