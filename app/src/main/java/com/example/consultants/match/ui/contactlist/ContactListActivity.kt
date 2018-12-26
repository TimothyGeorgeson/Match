package com.example.consultants.match.ui.contactlist

import android.location.Location
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.consultants.match.R
import com.example.consultants.match.ui.fragments.AllFragment
import com.example.consultants.match.ui.fragments.NearbyFragment
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import com.tbruyelle.rxpermissions2.RxPermissions
import kotlinx.android.synthetic.main.activity_contact_list.*
import android.Manifest
import android.annotation.SuppressLint

class ContactListActivity : AppCompatActivity() {

    private lateinit var fusedLocationClient: FusedLocationProviderClient

    //store device location - gave default value because getLastLocation wasn't working with emulator
    var deviceLatLng: Pair<Double, Double> = Pair(lat, lng)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_contact_list)

        getLocation()
    }

    @SuppressLint("MissingPermission", "CheckResult")
    private fun getLocation() {

        //initialize fusedLocationClient
        fusedLocationClient = LocationServices.getFusedLocationProviderClient(this)

        //using RxPermissions library for permission check
        val rxPermissions = RxPermissions(this)
        rxPermissions
            .request(Manifest.permission.ACCESS_COARSE_LOCATION) //request permission
            .subscribe { granted ->
                if (granted!!) {
                    // All requested permissions are granted
                    Log.i("_tag", "Permission granted!")
                    fusedLocationClient.lastLocation
                        .addOnSuccessListener { location : Location? ->
                            Log.i("_tag", "location: " + location.toString())
                            if (location != null) {
                                deviceLatLng = Pair(location.latitude, location.longitude)
                            }
                            displayTabs()
                        }
                } else {
                    // permission denied... deviceLocation won't be updated
                    displayTabs()
                }
            }
    }

    private fun displayTabs() {
        //initialize viewpageradapter and add 2 fragment tabs
        val adapter = ContactListViewPagerAdapter(supportFragmentManager, deviceLatLng)

        adapter.addFragment(AllFragment(), "All Matches")
        adapter.addFragment(NearbyFragment(), "Nearby")

        viewpager.adapter = adapter
        tabs.setupWithViewPager(viewpager)
    }

    companion object {
        const val lat = 33.6
        const val lng = -86.6
    }
}
