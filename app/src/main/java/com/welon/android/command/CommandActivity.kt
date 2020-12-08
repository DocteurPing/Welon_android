package com.welon.android.command

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import com.welon.android.R
import com.welon.android.databases.database.AppDatabase
import com.welon.android.databases.entities.Item
import com.welon.android.databases.entities.Menu
import com.welon.android.databases.entities.Restaurant
import com.welon.android.databinding.ActivityCommandBinding
import com.welon.android.ratingList.RatingListActivity
import com.welon.android.restaurant.fragments.ItemAdapter
import com.welon.android.restaurant.fragments.MenuAdapter
import com.welon.android.utils.Constants
import com.welon.android.utils.createMyPDF

class CommandActivity : AppCompatActivity(), CommandContract.View {

    private lateinit var binding: ActivityCommandBinding
    private lateinit var presenter: CommandPresenter
    private lateinit var command: String
    private lateinit var sharedPref: SharedPreferences
    private lateinit var restaurant: Restaurant
    private var sharing = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_command)
        presenter = CommandPresenter(this)
        command = intent.getStringExtra(Constants.EXTRA_COMMAND)!!
        sharedPref = getSharedPreferences(Constants.PREF_NAME, Constants.PRIVATE_MODE)
        presenter.getCommand(command)
        binding.back.setOnClickListener { onBackPressed() }
    }

    override fun goToRating(view: View) {
        val intent = Intent(this, RatingListActivity::class.java)
        intent.putExtra(Constants.EXTRA_RESTAURANT, restaurant.serverId)
        startActivity(intent)
        finish()
    }

    override fun share(view: View) {
        createMyPDF(sharing, "facture_${restaurant.name}.pdf")
        Toast.makeText(this, "PDF crée", Toast.LENGTH_LONG).show()
    }

    override fun setMenus(menus: List<Menu>) {
        if (menus.isEmpty()) {
            binding.listMenu.visibility = View.GONE
        } else {
            binding.listMenu.visibility = View.VISIBLE
        }
        binding.listMenu.layoutManager =
            LinearLayoutManager(this)
        binding.listMenu.adapter =
            MenuAdapter(menus, this)
        for (i in menus) {
            sharing += "${i.name}: ${i.price}€\n"
        }
    }

    override fun setItems(items: List<Item>) {
        if (items.isEmpty()) {
            binding.listItem.visibility = View.GONE
        } else {
            binding.listItem.visibility = View.VISIBLE
        }
        binding.listItem.layoutManager =
            LinearLayoutManager(this)
        binding.listItem.adapter =
            ItemAdapter(items, this)
        for (i in items) {
            sharing += "${i.name}: ${i.price}€\n"
        }
    }

    override fun setPrice(price: String) {
        binding.price.text = "$price€"
        sharing += "Total: $price€"
    }

    override fun setRestaurant(restaurantId: String) {
        restaurant = AppDatabase.INSTANCE?.restaurantDAO()?.getByServerId(restaurantId)!!
        binding.nameRestaurant.text = restaurant.name
    }

    override fun displayCommand(command: String) {

    }

    override fun disableNextButton() {
        binding.button.isEnabled = false
    }

    override fun getAppContext(): Context = this.applicationContext

    override fun getToken(): String? = sharedPref.getString(Constants.PREF_NAME_TOKEN, "")
}
