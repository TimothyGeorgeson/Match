package com.example.consultants.match.model;

import android.os.Parcel;
import android.os.Parcelable;

public class MyContact implements Parcelable {

    private String name;
    private String gender;
    private Integer age;
    private String email;
    private String phone;
    private String picURL;
    private Double lat;
    private Double lng;

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(name);
        dest.writeString(gender);
        dest.writeInt(age);
        dest.writeString(email);
        dest.writeString(phone);
        dest.writeString(picURL);
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
        gender = in.readString();
        age = in.readInt();
        email = in.readString();
        phone = in.readString();
        picURL = in.readString();
        lat = in.readDouble();
        lng = in.readDouble();
    }
}
