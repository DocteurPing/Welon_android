package com.welon.android.command

import com.android.volley.Request
import com.welon.android.databases.database.AppDatabase
import com.welon.android.databases.entities.Item
import com.welon.android.databases.entities.Menu
import com.welon.android.utils.Constants
import com.welon.android.utils.VolleyRequest
import org.json.JSONArray
import org.json.JSONObject

class CommandPresenter(private var commandView: CommandContract.View) : CommandContract.Presenter {

    override fun getCommand(command: String) {
        VolleyRequest.init(commandView.getAppContext())
        val url = "${Constants.apilink}/command/${command}"
        val volleyRequest =
            VolleyRequest(url, ::update) {}
        val header = HashMap<String, String>()
        header["x-access-token"] = commandView.getToken().toString()
        val params = HashMap<String, String>()
        volleyRequest.makeRequestToJSON(Request.Method.GET, header, params, "")
    }

    private fun update(json: String) {
        if (json == "[]") {
            commandView.disableNextButton()
            return
        }
        val listMenus = arrayListOf<Menu>()
        val listItems = arrayListOf<Item>()
        val listCommand = JSONArray(json)
        val item = JSONObject(listCommand.getString(0))
        val menus = JSONArray(item.getString("menus"))
        val price = item.getString("price")
        val entrees = JSONArray(item.getString("entrees"))
        val plats = JSONArray(item.getString("plats"))
        val desserts = JSONArray(item.getString("desserts"))
        val drinks = JSONArray(item.getString("boissons"))

        for (i in 0 until menus.length()) {
            val menu = menus[i] as JSONArray
            for (nbr in 0 until menu[1].toString().toInt()) {
                listMenus.add(
                    AppDatabase.INSTANCE?.menuDAO()?.getAllByServerId(menu[0].toString())!![0]
                )
            }
        }

        for (i in 0 until entrees.length()) {
            val entree = entrees[i] as JSONArray
            for (nbr in 0 until entree[1].toString().toInt()) {
                listItems.add(
                    AppDatabase.INSTANCE?.itemDAO()?.getItemByServerId(entree[0].toString())!!
                )
            }
        }

        for (i in 0 until plats.length()) {
            val plat = plats[i] as JSONArray
            for (nbr in 0 until plat[1].toString().toInt()) {
                listItems.add(
                    AppDatabase.INSTANCE?.itemDAO()?.getItemByServerId(plat[0].toString())!!
                )
            }
        }

        for (i in 0 until desserts.length()) {
            val dessert = desserts[i] as JSONArray
            for (nbr in 0 until dessert[1].toString().toInt()) {
                listItems.add(
                    AppDatabase.INSTANCE?.itemDAO()?.getItemByServerId(dessert[0].toString())!!
                )
            }
        }

        for (i in 0 until drinks.length()) {
            val drink = drinks[i] as JSONArray
            for (nbr in 0 until drink[1].toString().toInt()) {
                listItems.add(
                    AppDatabase.INSTANCE?.itemDAO()?.getItemByServerId(drink[0].toString())!!
                )
            }
        }

        commandView.setItems(listItems)
        commandView.setMenus(listMenus)
        commandView.setPrice(price)
        commandView.setRestaurant(item.getString("id_restaurant"))
    }
}