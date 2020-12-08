package com.welon.android.history

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import com.welon.android.R
import com.welon.android.command.CommandActivity
import com.welon.android.databinding.ActivityHistoryBinding
import com.welon.android.utils.Command
import com.welon.android.utils.Constants

class HistoryActivity : AppCompatActivity(), HistoryContract.View {

    private lateinit var binding: ActivityHistoryBinding
    private lateinit var sharedPref: SharedPreferences
    private lateinit var presenter: HistoryPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_history)
        sharedPref = getSharedPreferences(Constants.PREF_NAME, Constants.PRIVATE_MODE)
        presenter = HistoryPresenter(this)
        binding.back.setOnClickListener { onBackPressed() }
        presenter.getListHistory()
    }

    override fun getAppContext(): Context = this

    override fun getToken(): String? = sharedPref.getString(Constants.PREF_NAME_TOKEN, "")

    override fun updateCommand(listCommand: ArrayList<Command>) {
        binding.listItem.layoutManager = LinearLayoutManager(this)
        binding.listItem.adapter = HistoryAdapter(listCommand, this)
    }

    override fun navigateToCommand(commandId: String) {
        val intent = Intent(this, CommandActivity::class.java)
        intent.putExtra(Constants.EXTRA_COMMAND, commandId)
        startActivity(intent)
    }
}