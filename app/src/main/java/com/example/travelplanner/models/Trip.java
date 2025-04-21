package com.example.travelplanner.models;

import android.os.Parcel;
import android.os.Parcelable;

public class Trip implements Parcelable {
    private String destination;
    private String startDate;
    private String endDate;
    private int travelers;
    private String budget;
    private Flight selectedFlight;
    private Hotel selectedHotel;
    private Transportation selectedTransportation;

    public Trip() {
    }

    protected Trip(Parcel in) {
        destination = in.readString();
        startDate = in.readString();
        endDate = in.readString();
        travelers = in.readInt();
        budget = in.readString();
        selectedFlight = in.readParcelable(Flight.class.getClassLoader());
        selectedHotel = in.readParcelable(Hotel.class.getClassLoader());
        selectedTransportation = in.readParcelable(Transportation.class.getClassLoader());
    }

    public static final Creator<Trip> CREATOR = new Creator<Trip>() {
        @Override
        public Trip createFromParcel(Parcel in) {
            return new Trip(in);
        }

        @Override
        public Trip[] newArray(int size) {
            return new Trip[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(destination);
        dest.writeString(startDate);
        dest.writeString(endDate);
        dest.writeInt(travelers);
        dest.writeString(budget);
        dest.writeParcelable(selectedFlight, flags);
        dest.writeParcelable(selectedHotel, flags);
        dest.writeParcelable(selectedTransportation, flags);
    }

    // Getters and setters
    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public int getTravelers() {
        return travelers;
    }

    public void setTravelers(int travelers) {
        this.travelers = travelers;
    }

    public String getBudget() {
        return budget;
    }

    public void setBudget(String budget) {
        this.budget = budget;
    }

    public Flight getSelectedFlight() {
        return selectedFlight;
    }

    public void setSelectedFlight(Flight selectedFlight) {
        this.selectedFlight = selectedFlight;
    }

    public Hotel getSelectedHotel() {
        return selectedHotel;
    }

    public void setSelectedHotel(Hotel selectedHotel) {
        this.selectedHotel = selectedHotel;
    }

    public Transportation getSelectedTransportation() {
        return selectedTransportation;
    }

    public void setSelectedTransportation(Transportation selectedTransportation) {
        this.selectedTransportation = selectedTransportation;
    }
}