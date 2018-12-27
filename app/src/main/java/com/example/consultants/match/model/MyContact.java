package com.example.consultants.match.model;

import android.os.Parcel;
import android.os.Parcelable;

public class MyContact implements Parcelable {

    private String name;
    private Double lat;
    private Double lng;

    public MyContact(String name, Double lat, Double lng) {
        this.name = name;
        this.lat = lat;
        this.lng = lng;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getLat() {
        return lat;
    }

    public void setLat(Double lat) {
        this.lat = lat;
    }

    public Double getLng() {
        return lng;
    }

    public void setLng(Double lng) {
        this.lng = lng;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(name);
        dest.writeDouble(lat);
        dest.writeDouble(lng);
    }

    // Creator
    public static final Parcelable.Creator CREATOR
            = new Parcelable.Creator() {
        public MyContact createFromParcel(Parcel in) {
            return new MyContact(in);
        }

        public MyContact[] newArray(int size) {
            return new MyContact[size];
        }
    };

    // "De-parcel object
    private MyContact(Parcel in) {
        name = in.readString();
        lat = in.readDouble();
        lng = in.readDouble();
    }
}
