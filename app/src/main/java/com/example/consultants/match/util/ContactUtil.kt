package com.example.consultants.match.util

import android.util.Log
import com.example.consultants.match.model.MyContact
import com.example.consultants.match.model.jsondata.Contact

object ContactUtil {

    fun generateLocations(contacts: List<Contact>, lat: Double, lng: Double, near: Boolean) {

        //random function returns num from 0 to 1 to be added to device lat/long (make contacts within range)
        //for nearby contacts, I divide the result, so their distance will be even closer to the device
        var plus = true
        for (contact in contacts) {
            val latOffset = if (near) Math.random()/50 else Math.random()
            val lngOffset = if (near) Math.random()/50 else Math.random()

            //this alternates whether the offset is added or subtracted
            if (plus) {
                plus = false
                contact.location.coordinates.latitude = (lat + latOffset).toString()
                contact.location.coordinates.longitude = (lng + lngOffset).toString()
            } else {
                plus = true
                contact.location.coordinates.latitude = (lat - latOffset).toString()
                contact.location.coordinates.longitude = (lng - lngOffset).toString()
            }
        }
    }

    fun getMyContacts(contacts: List<Contact>, lat: Double, lng: Double): ArrayList<MyContact> {
        val results = arrayListOf<MyContact>()

        for (contact: Contact in contacts) {
            val myContact = MyContact(contact.name.first, contact.location.coordinates.latitude.toDouble(),
                contact.location.coordinates.longitude.toDouble())

            results.add(myContact)
        }
        results.add(MyContact("You", lat, lng))
        return results
    }
}
