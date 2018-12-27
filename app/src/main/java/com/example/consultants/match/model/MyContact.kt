package com.example.consultants.match.model

import android.os.Parcel
import android.os.Parcelable

data class MyContact(val name: String?, val lat: Double, val lng: Double) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString(),
        parcel.readDouble(),
        parcel.readDouble())

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(name)
        parcel.writeDouble(lat)
        parcel.writeDouble(lng)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<MyContact> {
        override fun createFromParcel(parcel: Parcel): MyContact {
            return MyContact(parcel)
        }

        override fun newArray(size: Int): Array<MyContact?> {
            return arrayOfNulls(size)
        }
    }
}
