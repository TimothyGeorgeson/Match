package com.example.consultants.match.ui.contactlist

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.GridLayoutManager
import android.util.Log
import com.example.consultants.match.R
import com.example.consultants.match.model.jsondata.Contact
import com.example.consultants.match.ui.tabs.AllFragment
import com.example.consultants.match.ui.tabs.NearbyFragment
import kotlinx.android.synthetic.main.activity_contact_list.*

class ContactListActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_contact_list)

        //initialize viewpageradapter and add 2 fragment tabs
        val adapter = ContactListViewPagerAdapter(supportFragmentManager)

        adapter.addFragment(AllFragment(), "All Matches")
        adapter.addFragment(NearbyFragment(), "Nearby")

        viewpager.adapter = adapter
        tabs.setupWithViewPager(viewpager)
    }
}
