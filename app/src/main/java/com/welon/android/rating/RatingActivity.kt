package com.welon.android.rating

import android.content.Context
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import com.welon.android.R
import com.welon.android.databases.entities.Item
import com.welon.android.databinding.ActivityRatingBinding
import com.welon.android.utils.Constants

class RatingActivity : AppCompatActivity(), RatingContract.View {

    private lateinit var binding: ActivityRatingBinding
    private lateinit var item: Item
    private lateinit var presenter: RatingPresenter
    private lateinit var sharedPref: SharedPreferences


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_rating)
        item = intent.getSerializableExtra(Constants.EXTRA_RATING) as Item
        presenter = RatingPresenter(this)
        sharedPref = getSharedPreferences(Constants.PREF_NAME, Constants.PRIVATE_MODE)
        binding.name.text = item.name
        binding.star1.setOnClickListener { presenter.sendRate(1, item) }
        binding.star2.setOnClickListener { presenter.sendRate(2, item) }
        binding.star3.setOnClickListener { presenter.sendRate(3, item) }
        binding.star4.setOnClickListener { presenter.sendRate(4, item) }
        binding.star5.setOnClickListener { presenter.sendRate(5, item) }
    }

    override fun getAppContext(): Context = this.applicationContext

    override fun getToken(): String? = sharedPref.getString(Constants.PREF_NAME_TOKEN, "")
}
