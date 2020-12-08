package com.welon.android.map

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import com.welon.android.R
import com.welon.android.databases.database.AppDatabase

class MapActivity : AppCompatActivity(), OnMapReadyCallback {

    private lateinit var mMap: GoogleMap

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_map)
        val mapFragment = supportFragmentManager
            .findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)
    }

    override fun onMapReady(googleMap: GoogleMap) {
        mMap = googleMap

        AppDatabase.INSTANCE?.restaurantDAO()?.getAll()?.forEach {
            val pos = LatLng(it.latitude, it.longitude)
            mMap.addMarker(MarkerOptions().position(pos).title(it.name))
            mMap.moveCamera(CameraUpdateFactory.newLatLng(pos))
        }
    }
}