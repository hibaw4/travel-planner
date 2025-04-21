package com.example.travelplanner.models;

import android.os.Parcel;
import android.os.Parcelable;

public class Flight implements Parcelable {
    private String airline;
    private String departureTime;
    private String arrivalTime;
    private String departureCity;
    private String arrivalCity;
    private double price;
    private String duration;

    public Flight(String airline, String departureTime, String arrivalTime,
                  String departureCity, String arrivalCity, double price, String duration) {
        this.airline = airline;
        this.departureTime = departureTime;
        this.arrivalTime = arrivalTime;
        this.departureCity = departureCity;
        this.arrivalCity = arrivalCity;
        this.price = price;
        this.duration = duration;
    }

    protected Flight(Parcel in) {
        airline = in.readString();
        departureTime = in.readString();
        arrivalTime = in.readString();
        departureCity = in.readString();
        arrivalCity = in.readString();
        price = in.readDouble();
        duration = in.readString();
    }

    public static final Creator<Flight> CREATOR = new Creator<Flight>() {
        @Override
        public Flight createFromParcel(Parcel in) {
            return new Flight(in);
        }

        @Override
        public Flight[] newArray(int size) {
            return new Flight[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(airline);
        dest.writeString(departureTime);
        dest.writeString(arrivalTime);
        dest.writeString(departureCity);
        dest.writeString(arrivalCity);
        dest.writeDouble(price);
        dest.writeString(duration);
    }

    // Getters
    public String getAirline() {
        return airline;
    }

    public String getDepartureTime() {
        return departureTime;
    }

    public String getArrivalTime() {
        return arrivalTime;
    }

    public String getDepartureCity() {
        return departureCity;
    }

    public String getArrivalCity() {
        return arrivalCity;
    }

    public double getPrice() {
        return price;
    }

    public String getDuration() {
        return duration;
    }
}