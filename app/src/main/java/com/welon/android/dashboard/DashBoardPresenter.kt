package com.welon.android.dashboard

import android.util.Log
import com.android.volley.Request
import com.welon.android.databases.database.AppDatabase
import com.welon.android.databases.entities.Item
import com.welon.android.databases.entities.Menu
import com.welon.android.databases.entities.Restaurant
import com.welon.android.utils.Constants.apilink
import com.welon.android.utils.VolleyRequest
import org.json.JSONArray
import org.json.JSONObject

class DashBoardPresenter(private var dashboardView: DashBoardContract.View) :
    DashBoardContract.Presenter {

    override fun getRestaurants() {
        VolleyRequest.init(dashboardView.getAppContext())
        val url = "$apilink/auth/restaurant"
        val volleyRequest = VolleyRequest(url, ::getRestaurant, ::error)
        val header = HashMap<String, String>()
        header["x-access-token"] = dashboardView.getToken().toString()
        val params = HashMap<String, String>()
        val body = ""
        volleyRequest.makeRequestToJSON(Request.Method.GET, header, params, body)

    }

    private fun getRestaurant(json: String) {
        try {
            AppDatabase.INSTANCE?.restaurantDAO()?.nukeTable()
            val listRestaurant = JSONArray(json)
            for (i in 0 until listRestaurant.length()) {
                val restaurant = Restaurant()
                val item = JSONObject(listRestaurant.getString(i))
                restaurant.name = item.getString("name")
                restaurant.address = item.getString("address")
                restaurant.serverId = item.getString("_id")
                restaurant.latitude = item.getDouble("latitude")
                restaurant.longitude = item.getDouble("longitude")
                restaurant.rank = item.optInt("rank", 999)
                if (restaurant.rank == 0){
                    restaurant.rank = 999
                }
                AppDatabase.INSTANCE?.restaurantDAO()?.insert(restaurant)
            }
        } catch (e: Exception) {
            Log.d("WELON[Volley]", "Can't get restaurants")
        }
        AppDatabase.INSTANCE?.restaurantDAO()?.getAll()?.forEach {
            getAll(it.serverId.toString())
        }
        dashboardView.updateRestaurant()
    }

    override fun getAll(id: String) {
        AppDatabase.INSTANCE?.itemDAO()?.nukeTable()
        AppDatabase.INSTANCE?.menuDAO()?.nukeTable()
        getItems("$apilink/entrees/$id", 0)
        getItems("$apilink/plats/$id", 1)
        getItems("$apilink/desserts/$id", 2)
        getItems("$apilink/boissons/$id", 3)
        getItems("$apilink/menus/$id", 4)
    }

    private fun getItems(url: String, type: Int) {
        VolleyRequest.init(dashboardView.getAppContext())
        lateinit var volleyRequest: VolleyRequest
        when (type) {
            0 -> volleyRequest = VolleyRequest(url, ::getEntree, ::error)
            1 -> volleyRequest = VolleyRequest(url, ::getPlat, ::error)
            2 -> volleyRequest = VolleyRequest(url, ::getDessert, ::error)
            3 -> volleyRequest = VolleyRequest(url, ::getDrinks, ::error)
            4 -> volleyRequest = VolleyRequest(url, ::getMenus, ::error)
        }
        val header = HashMap<String, String>()
        header["x-access-token"] = dashboardView.getToken().toString()
        val params = HashMap<String, String>()
        val body = ""
        volleyRequest.makeRequestToJSON(Request.Method.GET, header, params, body)
    }

    private fun getEntree(json: String) {
        try {
            val listRestaurant = JSONArray(json)
            for (i in 0 until listRestaurant.length()) {
                val entree = Item()
                val item = JSONObject(listRestaurant.getString(i))
                entree.name = item.getString("name")
                entree.restaurantId = item.getString("id_restaurant")
                entree.serverId = item.getString("_id")
                entree.description = item.getString("description")
                entree.price = item.getString("price")
                entree.type = 0
                AppDatabase.INSTANCE?.itemDAO()?.insert(entree)
            }
        } catch (e: Exception) {
            Log.d("WELON[Volley]", "Can't get entrees")
        }
    }

    private fun getPlat(json: String) {
        try {
            val listRestaurant = JSONArray(json)
            for (i in 0 until listRestaurant.length()) {
                val plat = Item()
                val item = JSONObject(listRestaurant.getString(i))
                plat.name = item.getString("name")
                plat.restaurantId = item.getString("id_restaurant")
                plat.serverId = item.getString("_id")
                plat.description = item.getString("description")
                plat.price = item.getString("price")
                plat.type = 1
                AppDatabase.INSTANCE?.itemDAO()?.insert(plat)
            }
        } catch (e: Exception) {
            Log.d("WELON[Volley]", "Can't get plats")
        }
    }

    private fun getDessert(json: String) {
        try {
            val listRestaurant = JSONArray(json)
            for (i in 0 until listRestaurant.length()) {
                val dessert = Item()
                val item = JSONObject(listRestaurant.getString(i))
                dessert.name = item.getString("name")
                dessert.restaurantId = item.getString("id_restaurant")
                dessert.serverId = item.getString("_id")
                dessert.description = item.getString("description")
                dessert.price = item.getString("price")
                dessert.type = 2
                AppDatabase.INSTANCE?.itemDAO()?.insert(dessert)
            }
        } catch (e: Exception) {
            Log.d("WELON[Volley]", "Can't get desserts")
        }
    }

    private fun getDrinks(json: String) {
        try {
            val listRestaurant = JSONArray(json)
            for (i in 0 until listRestaurant.length()) {
                val drink = Item()
                val item = JSONObject(listRestaurant.getString(i))
                drink.name = item.getString("name")
                drink.restaurantId = item.getString("id_restaurant")
                drink.serverId = item.getString("_id")
                drink.description = item.getString("description")
                drink.price = item.getString("price")
                drink.type = 3
                AppDatabase.INSTANCE?.itemDAO()?.insert(drink)
            }
        } catch (e: Exception) {
            Log.d("WELON[Volley]", "Can't get drinks")
        }
    }

    private fun getMenus(json: String) {
        try {
            val listRestaurant = JSONArray(json)
            for (i in 0 until listRestaurant.length()) {
                val menu = Menu()
                val item = JSONObject(listRestaurant.getString(i))
                menu.name = item.getString("name")
                menu.restaurantId = item.getString("id_restaurant")
                val menuId = item.getString("_id")
                menu.serverId = menuId
                menu.description = item.getString("description")
                menu.price = item.getString("price")
                updateItems(item.getString("id_entree"), menuId)
                updateItems(item.getString("id_plat"), menuId)
                updateItems(item.getString("id_dessert"), menuId)
                updateItems(item.getString("id_boisson"), menuId)
                AppDatabase.INSTANCE?.menuDAO()?.insert(menu)
            }
        } catch (e: Exception) {
            Log.d("WELON[Volley]", "Can't get menus")
        }
    }

    private fun updateItems(json: String, menuId: String) {
        val array = JSONArray(json)
        for (i in 0 until array.length()) {
            val itemId = array.getString(i)
            val item = AppDatabase.INSTANCE?.itemDAO()?.getItemByServerId(itemId) ?: return
            item.menuId = menuId
            AppDatabase.INSTANCE?.itemDAO()?.update(item)
        }
    }

    override fun authMe() {
        VolleyRequest.init(dashboardView.getAppContext())
        val url = "$apilink/auth/me"
        val volleyRequest =
            VolleyRequest(url, ::verifyUser) {}
        val header = HashMap<String, String>()
        header["x-access-token"] = dashboardView.getToken().toString()
        val params = HashMap<String, String>()
        volleyRequest.makeRequestToJSON(Request.Method.GET, header, params, "")
    }

    private fun verifyUser(json: String) {
        try {
            val obj = JSONObject(json)
            if (obj.getString("verified") == "false")
                dashboardView.showNotVerified()
        } catch (e: Exception) {
            Log.d("WELON[Volley]", "Can't get token")
        }
    }
}