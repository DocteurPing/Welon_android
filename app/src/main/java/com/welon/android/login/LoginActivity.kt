package com.welon.android.login

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.text.method.HideReturnsTransformationMethod
import android.text.method.PasswordTransformationMethod
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.welon.android.R
import com.welon.android.dashboard.DashBoardActivity
import com.welon.android.databases.database.AppDatabase
import com.welon.android.databinding.ActivityLoginBinding
import com.welon.android.signup.SignupActivity
import com.welon.android.utils.Constants.DATABASE_NAME
import com.welon.android.utils.Constants.PREF_NAME
import com.welon.android.utils.Constants.PREF_NAME_EMAIL
import com.welon.android.utils.Constants.PREF_NAME_TOKEN
import com.welon.android.utils.Constants.PRIVATE_MODE


class LoginActivity : AppCompatActivity(), LoginContract.View {

    private lateinit var loginPresenter: LoginPresenter
    private lateinit var binding: ActivityLoginBinding
    private lateinit var sharedPref: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_login)
        loginPresenter = LoginPresenter(this)
        AppDatabase.init(this, AppDatabase::class.java, DATABASE_NAME)
        sharedPref = getSharedPreferences(PREF_NAME, PRIVATE_MODE)
        if (!sharedPref.getString(PREF_NAME_TOKEN, "").isNullOrEmpty()) {
            loginPresenter.authMe()
        }
        binding.checkbox.setOnCheckedChangeListener { _, isCheck ->
            if (isCheck)
                binding.password.transformationMethod =
                    HideReturnsTransformationMethod.getInstance()
            else
                binding.password.transformationMethod = PasswordTransformationMethod.getInstance()
        }
    }

    override fun onClickTextSignUp(view: View) =
        startActivity(Intent(this, SignupActivity::class.java))

    override fun navigateToDashBoard() {
        startActivity(Intent(this, DashBoardActivity::class.java))
        finish()
    }

    override fun getAppContext(): Context = this.applicationContext

    override fun onClickButton(view: View) {
        setEmail(binding.email.text.toString())
        loginPresenter.sign(
            binding.email.text.toString(),
            binding.password.text.toString()
        )
    }

    override fun showFailConnect() =
        Toast.makeText(
            this,
            "Incorrect password for this email or not verified account",
            Toast.LENGTH_LONG
        ).show()

    override fun show(string: CharSequence) =
        Toast.makeText(this, string, Toast.LENGTH_LONG).show()

    override fun setToken(token: String) =
        sharedPref.edit().putString(PREF_NAME_TOKEN, token).apply()

    override fun setEmail(email: String) =
        sharedPref.edit().putString(PREF_NAME_EMAIL, email).apply()

    override fun getToken(): String? = sharedPref.getString(PREF_NAME_TOKEN, "")
}
