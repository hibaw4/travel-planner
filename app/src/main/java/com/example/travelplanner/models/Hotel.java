package com.example.travelplanner.models;

import android.os.Parcel;
import android.os.Parcelable;

public class Hotel implements Parcelable {
    private String name;
    private String address;
    private double pricePerNight;
    private double rating;
    private int reviewCount;
    private String description;

    public Hotel(String name, String address, double pricePerNight, double rating,
                 int reviewCount, String description) {
        this.name = name;
        this.address = address;
        this.pricePerNight = pricePerNight;
        this.rating = rating;
        this.reviewCount = reviewCount;
        this.description = description;
    }

    protected Hotel(Parcel in) {
        name = in.readString();
        address = in.readString();
        pricePerNight = in.readDouble();
        rating = in.readDouble();
        reviewCount = in.readInt();
        description = in.readString();
    }

    public static final Creator<Hotel> CREATOR = new Creator<Hotel>() {
        @Override
        public Hotel createFromParcel(Parcel in) {
            return new Hotel(in);
        }

        @Override
        public Hotel[] newArray(int size) {
            return new Hotel[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(name);
        dest.writeString(address);
        dest.writeDouble(pricePerNight);
        dest.writeDouble(rating);
        dest.writeInt(reviewCount);
        dest.writeString(description);
    }

    // Getters
    public String getName() {
        return name;
    }

    public String getAddress() {
        return address;
    }

    public double getPricePerNight() {
        return pricePerNight;
    }

    public double getRating() {
        return rating;
    }

    public int getReviewCount() {
        return reviewCount;
    }

    public String getDescription() {
        return description;
    }
}