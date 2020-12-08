package com.welon.android.changePassword

import android.content.Context
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.welon.android.R
import com.welon.android.databinding.ActivityChangePasswordBinding

class ChangePasswordActivity : AppCompatActivity(), ChangePasswordContract.View {

    private lateinit var binding: ActivityChangePasswordBinding
    private lateinit var presenter: ChangePasswordPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_change_password)
        presenter = ChangePasswordPresenter(this)
        binding.back.setOnClickListener { onBackPressed() }
    }

    override fun onClickChangePassword(view: View) {
        if (presenter.checkpassword(
                binding.newPassword.text.toString(),
                binding.confirmPassword.text.toString()
            )
        ) {
            presenter.changePassword(
                binding.email.text.toString(),
                binding.oldPassword.text.toString(),
                binding.newPassword.text.toString()
            )

        }
    }

    override fun showPasswordChanged() {
        Toast.makeText(this, "Le mot de passe a bien été modifié", Toast.LENGTH_LONG).show()
        finish()
    }

    override fun showIncorrectPassword() {
        Toast.makeText(this, "L'ancien mot de passe ne correspond pas'", Toast.LENGTH_LONG).show()
    }

    override fun showNotMatchingPassword() {
        binding.confirmPassword.error = "Les mots de passe ne correspondent pas"
    }

    override fun showInvalidPassword() {
        binding.newPassword.error =
            "Le mot de passe doit contenir au minimum 8 lettres 1 majuscule et 1 minuscule"
    }

    override fun getAppContext(): Context = this.applicationContext
}