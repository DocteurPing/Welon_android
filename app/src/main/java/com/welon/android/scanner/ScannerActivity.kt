package com.welon.android.scanner

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.welon.android.R
import com.welon.android.command.CommandActivity
import com.welon.android.databases.entities.Restaurant
import com.welon.android.databinding.ActivityScannerBinding
import com.welon.android.ratingList.RatingListActivity
import com.welon.android.utils.Constants
import github.nisrulz.qreader.QREader


class ScannerActivity : AppCompatActivity(), ScannerContract.View {

    private lateinit var binding: ActivityScannerBinding
    private lateinit var qrEader: QREader
    private lateinit var presenter: ScannerPresenter


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_scanner)
        presenter = ScannerPresenter(this)

        qrEader = QREader.Builder(this, binding.cameraView) { data ->
            Log.d("QREader", "Value : $data")
            navigateToCommand(data)
        }.facing(QREader.BACK_CAM)
            .enableAutofocus(true)
            .height(binding.cameraView.height)
            .width(binding.cameraView.width)
            .build()
        qrEader.start()
    }

    override fun onResume() {
        super.onResume()
        qrEader.initAndStart(binding.cameraView)
    }

    override fun onPause() {
        super.onPause()
        qrEader.releaseAndCleanup()
    }

    override fun navigateToRating(restaurant: Restaurant) {
        val intent = Intent(this, RatingListActivity::class.java)
        intent.putExtra(Constants.EXTRA_RESTAURANT, restaurant.serverId)
        startActivity(intent)
    }

    override fun navigateToCommand(commandId: String) {
        val intent = Intent(this, CommandActivity::class.java)
        intent.putExtra(Constants.EXTRA_COMMAND, commandId)
        startActivity(intent)
    }

}
