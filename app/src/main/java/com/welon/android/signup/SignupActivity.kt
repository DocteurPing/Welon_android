package com.welon.android.signup

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.welon.android.R
import com.welon.android.dashboard.DashBoardActivity
import com.welon.android.databinding.ActivitySignupBinding
import com.welon.android.utils.Constants

class SignupActivity : AppCompatActivity(), SignupContract.View {

    private lateinit var binding: ActivitySignupBinding
    private lateinit var presenter: SignupPresenter
    private lateinit var sharedPref: SharedPreferences
    private var state = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_signup)
        presenter = SignupPresenter(this)
        sharedPref = getSharedPreferences(Constants.PREF_NAME, Constants.PRIVATE_MODE)

    }

    override fun onClickTextSignIn(view: View) = finish()

    override fun onClickNext(view: View) {
        when (state) {
            0 -> presenter.checkFirstFields(
                binding.email.text.toString(),
                binding.password.text.toString(),
                binding.passwordConfirm.text.toString()
            )
            1 -> showThird()
            2 -> presenter.sign(
                binding.email.text.toString(),
                binding.password.text.toString(),
                binding.passwordConfirm.text.toString(),
                binding.firstName.text.toString(),
                binding.lastName.text.toString(),
                binding.phone.text.toString()
            )
        }
    }

    private fun showFirst() {
        binding.button.text = getString(R.string.next)
        binding.second.visibility = View.GONE
        binding.third.visibility = View.GONE
        binding.first.visibility = View.VISIBLE
        state = 0
    }

    override fun showSecond() {
        binding.button.text = getString(R.string.next)
        binding.first.visibility = View.GONE
        binding.third.visibility = View.GONE
        binding.second.visibility = View.VISIBLE
        state = 1
    }

    override fun showThird() {
        if (binding.firstName.text.isNullOrEmpty()) {
            binding.firstName.error = "Veuillez renseigner un prénom"
            return
        }
        if (binding.lastName.text.isNullOrEmpty()) {
            binding.lastName.error = "Veuillez renseigner un nom"
            return
        }
        binding.button.text = getString(R.string.sign_up)
        binding.first.visibility = View.GONE
        binding.second.visibility = View.GONE
        binding.third.visibility = View.VISIBLE
        state = 2
    }

    override fun showInvalidEmail() {
        binding.email.error = "Email invalide"
    }

    override fun showNotMatchingPassword() {
        binding.password.error = "Les mots de passe ne correspondent pas"
    }

    override fun showInvalidPassword() {
        binding.password.error =
            "Le mot de passe doit contenir au minimum 8 lettres 1 majuscule et 1 minuscule"
    }

    override fun showMissPhone() {
        binding.phone.error = "Veuillez renseigner un numéro"
    }

    override fun showMissUsername() {
        binding.username.error = "Veuillez renseigner un pseudo"
    }

    override fun getAppContext(): Context = this.applicationContext

    override fun showFailConnect() {
        Toast.makeText(this, "Cet email est déjà utilisé", Toast.LENGTH_LONG).show()
        showFirst()
    }

    override fun navigateToDashBoard() {
        startActivity(Intent(this, DashBoardActivity::class.java))
        finish()
    }

    override fun setToken(token: String) =
        sharedPref.edit().putString(Constants.PREF_NAME_TOKEN, token).apply()

    override fun getToken(): String? = sharedPref.getString(Constants.PREF_NAME_TOKEN, "")

}