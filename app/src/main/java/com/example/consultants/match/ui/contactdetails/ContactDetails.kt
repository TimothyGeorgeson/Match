package com.example.consultants.match.ui.contactdetails

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.example.consultants.match.R
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_contact_details.*

class ContactDetails : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_contact_details)

        Picasso.get().load(intent.getStringExtra("PICURL")).into(ivDetailImage)
        tvDetailName.text = "Name: " + intent.getStringExtra("NAME")
        tvDetailGender.text = "Gender: " + intent.getStringExtra("GENDER")
        tvDetailAge.text = "Age: " + intent.getIntExtra("AGE", 25).toString()
        tvDetailEmail.text = intent.getStringExtra("EMAIL")
        tvDetailPhone.text = intent.getStringExtra("PHONE")
        val locStr = "Lat: ${intent.getStringExtra("LAT")} \nLong: ${intent.getStringExtra("LONG")}"
        tvDetailLocation.text = locStr
    }
}
