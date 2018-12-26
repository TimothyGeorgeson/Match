package com.example.consultants.match.ui.contactlist

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter

import java.util.ArrayList

internal class ContactListViewPagerAdapter(manager: FragmentManager, latLong: Pair<Double, Double>)
    : FragmentPagerAdapter(manager) {

    private val fragments = ArrayList<Fragment>()
    private val titles = ArrayList<String>()
    private val lat = latLong.first
    private val lng = latLong.second

    override fun getItem(position: Int): Fragment {
        val bundle = Bundle()
        bundle.putDouble("LAT", lat)
        bundle.putDouble("LONG", lng)
        val fragment = fragments[position]
        fragment.arguments = bundle
        return fragment
    }

    override fun getCount(): Int {
        return titles.size
    }

    override fun getPageTitle(position: Int): CharSequence? {
        return titles[position]
    }

    fun addFragment(fragment: Fragment, title: String) {
        fragments.add(fragment)
        titles.add(title)
    }
}
