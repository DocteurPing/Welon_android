package com.welon.android.menu

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import com.welon.android.R
import com.welon.android.databases.database.AppDatabase
import com.welon.android.databases.entities.Menu
import com.welon.android.databases.entities.Restaurant
import com.welon.android.databinding.ActivityMenuBinding
import com.welon.android.profile.ProfileActivity
import com.welon.android.restaurant.fragments.ItemAdapter
import com.welon.android.utils.Constants

class MenuActivity : AppCompatActivity(), MenuContract.View {

    private lateinit var binding: ActivityMenuBinding
    private lateinit var restaurant: Restaurant
    private lateinit var menu: Menu

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_menu)
        restaurant = intent.getSerializableExtra(Constants.EXTRA_RESTAURANT) as Restaurant
        menu = intent.getSerializableExtra(Constants.EXTRA_MENU) as Menu
        binding.restaurant = restaurant
        binding.nameMenu.text = menu.name
        binding.listRestaurant.layoutManager = LinearLayoutManager(this)
        binding.listRestaurant.adapter =
            ItemAdapter(
                AppDatabase.INSTANCE?.itemDAO()?.getAllByMenuId(menu.serverId.toString())!!,
                this
            )
        binding.back.setOnClickListener {
            onBackPressed()
        }
    }

    override fun navigateToProfile(view: View) =
        startActivity(Intent(this, ProfileActivity::class.java))
}
