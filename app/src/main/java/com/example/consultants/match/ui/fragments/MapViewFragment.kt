package com.example.consultants.match.ui.fragments

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.consultants.match.R
import com.example.consultants.match.model.MyContact
import com.google.android.gms.maps.*
import com.google.android.gms.maps.model.CameraPosition

import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions

class MapViewFragment : Fragment() {

    private lateinit var mMapView: MapView
    private var googleMap: GoogleMap? = null
    private var contactList: ArrayList<MyContact>? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val rootView = inflater.inflate(R.layout.fragment_map, container, false)

        //get list of contacts through the bundle that was passed here
        contactList = arguments?.getParcelableArrayList<MyContact>("CONTACTS")

        mMapView = rootView.findViewById(R.id.mapView)
        mMapView.onCreate(savedInstanceState)

        //gets the map to display
        mMapView.onResume()

        try {
            MapsInitializer.initialize(activity!!.applicationContext)
        } catch (e: Exception) {
            e.printStackTrace()
        }

        mMapView.getMapAsync { mMap ->
            googleMap = mMap

            if(contactList != null) {
                for (contact: MyContact in contactList as ArrayList) {
                    // set markers on Map
                    val position = LatLng(contact.lat, contact.lng)
                    googleMap?.addMarker(MarkerOptions().position(position).title(contact.name))

                    //centers camera on your device location
                    if (contact.name == "You") {
                        val cameraPosition = CameraPosition.Builder()
                            .target(position).zoom(12f).build()
                        googleMap?.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition))
                    }
                }
            }
        }

        return rootView
    }

    override fun onResume() {
        super.onResume()
        mMapView.onResume()
    }

    override fun onPause() {
        super.onPause()
        mMapView.onPause()
    }

    override fun onDestroy() {
        super.onDestroy()
        mMapView.onDestroy()
    }

    override fun onLowMemory() {
        super.onLowMemory()
        mMapView.onLowMemory()
    }

}
