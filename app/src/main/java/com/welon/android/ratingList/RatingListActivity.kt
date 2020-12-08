package com.welon.android.ratingList

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import com.welon.android.R
import com.welon.android.databases.database.AppDatabase
import com.welon.android.databinding.ActivityRatingListBinding
import com.welon.android.utils.Constants

class RatingListActivity : AppCompatActivity(), RatingListContract.View {

    private lateinit var binding: ActivityRatingListBinding
    private lateinit var presenter: RatingListPresenter
    private lateinit var id: String
    private lateinit var sharedPref: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_rating_list)
        presenter = RatingListPresenter(this)
        sharedPref = getSharedPreferences(Constants.PREF_NAME, Constants.PRIVATE_MODE)

        id = intent.getStringExtra(Constants.EXTRA_RESTAURANT)!!
        binding.listItem.layoutManager = LinearLayoutManager(this)
        binding.listItem.adapter = RatingListAdapter(
            AppDatabase.INSTANCE?.itemDAO()?.getAllByRestaurantId(id)!!,
            this,
            presenter
        )
        binding.back.setOnClickListener {
            onBackPressed()
        }
        binding.button.setOnClickListener {
            finish()
        }
    }

    override fun getAppContext(): Context = this.applicationContext

    override fun getToken(): String? = sharedPref.getString(Constants.PREF_NAME_TOKEN, "")

    override fun showToastSuccess() = Toast.makeText(this, "Vous avez noté votre plat avec succès", Toast.LENGTH_LONG).show()

}
