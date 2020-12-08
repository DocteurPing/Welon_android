package com.welon.android.profile

import android.app.AlertDialog
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.welon.android.R
import com.welon.android.changePassword.ChangePasswordActivity
import com.welon.android.databinding.ActivityProfileBinding
import com.welon.android.history.HistoryActivity
import com.welon.android.login.LoginActivity
import com.welon.android.map.MapActivity
import com.welon.android.utils.Constants
import com.welon.android.utils.Constants.PREF_NAME_EMAIL
import com.welon.android.utils.Constants.PREF_NAME_TOKEN

class ProfileActivity : AppCompatActivity(), ProfileContract.View {

    private lateinit var binding: ActivityProfileBinding
    private lateinit var sharedPref: SharedPreferences
    private lateinit var presenter: ProfilePresenter


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_profile)
        sharedPref = getSharedPreferences(Constants.PREF_NAME, Constants.PRIVATE_MODE)
        presenter = ProfilePresenter(this)
        binding.back.setOnClickListener { onBackPressed() }
    }

    override fun onClickLogOut(view: View) {
        val builder = AlertDialog.Builder(this)
        builder.setTitle(getString(R.string.deconnexion))
        builder.setMessage(getString(R.string.text_deconnexion))
        builder.setPositiveButton(getString(R.string.deconnexion)) { _, _ ->
            sharedPref.edit().putString(PREF_NAME_TOKEN, "").apply()
            startActivity(Intent(this, LoginActivity::class.java))
            finishAffinity()
        }
        builder.setNegativeButton(getString(R.string.cancel)) { _, _ -> }
        val dialog: AlertDialog = builder.create()
        dialog.show()
    }

    override fun onClickGoToChangePassword(view: View) {
        startActivity(Intent(this, ChangePasswordActivity::class.java))
    }

    override fun onClickGoToHistory(view: View) {
        startActivity(Intent(this, HistoryActivity::class.java))
    }

    override fun onClickGoToMap(view: View) {
        startActivity(Intent(this, MapActivity::class.java))
    }

    override fun onClickDeleteAccount(view: View) {
        val builder = AlertDialog.Builder(this)
        builder.setTitle(getString(R.string.delete))
        builder.setMessage(getString(R.string.text_delete))
        builder.setPositiveButton(getString(R.string.delete)) { _, _ ->
            presenter.deleteAccount()
            sharedPref.edit().putString(PREF_NAME_TOKEN, "").apply()
            startActivity(Intent(this, LoginActivity::class.java))
            finishAffinity()
        }
        builder.setNegativeButton(getString(R.string.cancel)) { _, _ -> }
        val dialog: AlertDialog = builder.create()
        dialog.show()
    }

    override fun getAppContext(): Context = this.applicationContext

    override fun getToken(): String? = sharedPref.getString(PREF_NAME_TOKEN, "")

    override fun getEmail(): String? = sharedPref.getString(PREF_NAME_EMAIL, "")

}
