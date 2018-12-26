package com.example.consultants.match.util

import android.util.Log
import com.example.consultants.match.model.jsondata.Contact

object LocationUtil {

    fun generateLocations(contacts: List<Contact>, lat: Double, lng: Double, near: Boolean): List<Contact> {
        val results: List<Contact> = contacts

        //random function returns num from 0 to 1 to be added to device lat/long (make contacts within range)
        //for nearby contacts, I divide the result, so their distance will be even closer to the device
        for (contact in results) {
            val latOffset = if (near) Math.random()/3 else Math.random()
            val lngOffset = if (near) Math.random()/3 else Math.random()

            contact.location.coordinates.latitude = (lat + latOffset).toString()
            contact.location.coordinates.longitude = (lng + lngOffset).toString()

            Log.i("_tag", "contact lat: ${contact.location.coordinates.latitude} " +
                    "long: ${contact.location.coordinates.longitude}")
        }

        return results
    }
}
