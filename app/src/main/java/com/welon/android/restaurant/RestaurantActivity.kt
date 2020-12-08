package com.welon.android.restaurant

import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.welon.android.R
import com.welon.android.databases.entities.Restaurant
import com.welon.android.databinding.ActivityRestaurantBinding
import com.welon.android.restaurant.fragments.TabsAdapter
import com.welon.android.scanner.ScannerActivity
import com.welon.android.utils.Constants
import com.welon.android.utils.Constants.EXTRA_RESTAURANT

class RestaurantActivity : AppCompatActivity(), RestaurantContract.View {

    private lateinit var binding: ActivityRestaurantBinding
    private lateinit var restaurant: Restaurant
    private lateinit var sharedPref: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_restaurant)

        restaurant = intent.getSerializableExtra(EXTRA_RESTAURANT) as Restaurant
        sharedPref = getSharedPreferences(Constants.PREF_NAME, Constants.PRIVATE_MODE)
        binding.restaurant = restaurant
        binding.viewpager.adapter =
            TabsAdapter(supportFragmentManager, restaurant.serverId.toString())
        binding.tabsRestaurant.setupWithViewPager(binding.viewpager)
        binding.back.setOnClickListener {
            onBackPressed()
        }
    }

    override fun navigateToScanner(view: View) =
        startActivity(Intent(this, ScannerActivity::class.java))

    override fun getAppContext(): RestaurantActivity = this

    override fun getToken(): String? = sharedPref.getString(Constants.PREF_NAME_TOKEN, "")
}
