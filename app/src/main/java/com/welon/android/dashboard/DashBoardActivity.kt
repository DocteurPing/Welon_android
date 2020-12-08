package com.welon.android.dashboard

import android.Manifest
import android.app.AlertDialog
import android.content.Intent
import android.content.SharedPreferences
import android.content.pm.PackageManager
import android.os.Bundle
import android.os.Handler
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import com.welon.android.R
import com.welon.android.databases.database.AppDatabase
import com.welon.android.databases.entities.Restaurant
import com.welon.android.databinding.ActivityDashBoardBinding
import com.welon.android.login.LoginActivity
import com.welon.android.profile.ProfileActivity
import com.welon.android.restaurant.RestaurantActivity
import com.welon.android.utils.Constants
import com.welon.android.utils.Constants.EXTRA_RESTAURANT

class DashBoardActivity : AppCompatActivity(), DashBoardContract.View {

    private lateinit var binding: ActivityDashBoardBinding
    private lateinit var presenter: DashBoardPresenter
    private lateinit var sharedPref: SharedPreferences
    private var backButtonPressed = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        resuqestPermission()
        binding = DataBindingUtil.setContentView(this, R.layout.activity_dash_board)
        sharedPref = getSharedPreferences(Constants.PREF_NAME, Constants.PRIVATE_MODE)
        presenter = DashBoardPresenter(this)
        presenter.authMe()
        presenter.getRestaurants()
    }

    override fun updateRestaurant() {
        binding.listRestaurant.layoutManager = LinearLayoutManager(this)
        val listRestaurant = AppDatabase.INSTANCE?.restaurantDAO()?.getAll()
        if (listRestaurant != null)
            binding.listRestaurant.adapter =
                DashBoardAdapter(listRestaurant, this)
    }

    override fun getAppContext(): DashBoardActivity = this

    override fun navigateToProfile(view: View) =
        startActivity(Intent(this, ProfileActivity::class.java))

    override fun setToken(token: String) {
        sharedPref.edit().putString(Constants.PREF_NAME_TOKEN, token).apply()
    }

    override fun getToken(): String? = sharedPref.getString(Constants.PREF_NAME_TOKEN, "")

    override fun showNotVerified() {
        val builder = AlertDialog.Builder(this)
        builder.setTitle(getString(R.string.verified))
        builder.setMessage(getString(R.string.text_verify))
        builder.setPositiveButton(getString(R.string.conntinue)) { _, _ -> }
        builder.setNegativeButton(getString(R.string.cancel)) { _, _ ->
            sharedPref.edit().putString(Constants.PREF_NAME_TOKEN, "").apply()
            startActivity(Intent(this, LoginActivity::class.java))
            finishAffinity()
        }
        val dialog: AlertDialog = builder.create()
        dialog.show()
    }

    override fun onBackPressed() {
        if (backButtonPressed) {
            finishAffinity()
            return
        }
        backButtonPressed = true
        Toast.makeText(
            this,
            "Cliquer une nouvelle fois pour quitter l'application",
            Toast.LENGTH_LONG
        ).show()
        Handler().postDelayed({ backButtonPressed = false }, 2000)
    }

    override fun navigateToRestaurant(restaurant: Restaurant) {
        val intent = Intent(this, RestaurantActivity::class.java)
        intent.putExtra(EXTRA_RESTAURANT, restaurant)
        startActivity(intent)
    }

    private fun resuqestPermission() {
        if (ContextCompat.checkSelfPermission(
                this,
                Manifest.permission.CAMERA
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            ActivityCompat.requestPermissions(
                this,
                arrayOf(Manifest.permission.CAMERA),
                Constants.MY_PERMISSIONS_REQUEST_CAMERA
            )
        }
        if (ContextCompat.checkSelfPermission(
                this,
                Manifest.permission.READ_EXTERNAL_STORAGE
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            ActivityCompat.requestPermissions(
                this,
                arrayOf(Manifest.permission.READ_EXTERNAL_STORAGE),
                Constants.MY_PERMISSIONS_REQUEST_CAMERA
            )
        }
        if (ContextCompat.checkSelfPermission(
                this,
                Manifest.permission.WRITE_EXTERNAL_STORAGE
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            ActivityCompat.requestPermissions(
                this,
                arrayOf(Manifest.permission.WRITE_EXTERNAL_STORAGE),
                Constants.MY_PERMISSIONS_REQUEST_CAMERA
            )
        }
    }
}
