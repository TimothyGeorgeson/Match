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
import android.app.SearchManager
import android.content.Context
import android.view.Menu
import android.support.v7.widget.SearchView
import android.view.MenuItem
import android.widget.Toast
import com.firebase.ui.auth.AuthUI

class ContactListActivity : AppCompatActivity() {

    private lateinit var fusedLocationClient: FusedLocationProviderClient
    private var viewPagerAdapter: ContactListViewPagerAdapter? = null

    //store device location - gave default value because getLastLocation wasn't working with emulator
    private var deviceLatLng: Pair<Double, Double> = Pair(lat, lng)

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
        viewPagerAdapter = ContactListViewPagerAdapter(supportFragmentManager, deviceLatLng)

        viewPagerAdapter?.addFragment(AllFragment(), "All Matches")
        viewPagerAdapter?.addFragment(NearbyFragment(), "Nearby")

        viewpager.adapter = viewPagerAdapter
        tabs.setupWithViewPager(viewpager)
    }

    companion object {
        const val lat = 33.6
        const val lng = -86.6
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.main_menu, menu)
        val searchManager: SearchManager = getSystemService(Context.SEARCH_SERVICE) as SearchManager
        val searchView: SearchView = menu?.findItem(R.id.action_search)?.actionView as SearchView
        searchView.setSearchableInfo(searchManager.getSearchableInfo(componentName))

        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String): Boolean {
                // filter recycler view when query submitted
                if (viewPagerAdapter?.getItem(viewpager.currentItem) is AllFragment) {
                    val fragment = viewPagerAdapter?.getItem(viewpager.currentItem) as AllFragment
                    fragment.sendQuery(query)
                }
                return false
            }

            override fun onQueryTextChange(query: String): Boolean {
                // filter recycler view when text is changed
                if (viewPagerAdapter?.getItem(viewpager.currentItem) is AllFragment) {
                    val fragment = viewPagerAdapter?.getItem(viewpager.currentItem) as AllFragment
                    fragment.sendQuery(query)
                }
                return false
            }
        })
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.menu_sign_out) {
            AuthUI.getInstance().signOut(this)
                .addOnCompleteListener {
                    Toast.makeText(
                        this,
                        "You have been signed out.",
                        Toast.LENGTH_LONG
                    )
                        .show()

                    // Close activity
                    finish()
                }
        }
        return true
    }
}
