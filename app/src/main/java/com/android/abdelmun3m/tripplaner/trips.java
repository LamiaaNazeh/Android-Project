package com.android.abdelmun3m.tripplaner;

import android.net.Uri;
import android.text.format.Time;
import android.util.ArrayMap;
import android.util.Log;

import com.google.android.gms.location.places.Place;
import com.google.android.gms.location.places.ui.PlaceAutocompleteFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

/**
 * Created by Asmaa on 3/28/2018.
 */

public class trips {

    String tripName;
    CustomPlace startPlace;
    CustomPlace endPlace;
    String tripDate;
    String tripTime;
    ArrayList<String> tripNotes;
    Boolean roundTrip;
    String tripStatus = "upcoming";
    public static final int TRIP_STATUS_UPCOMING = 0;
    public static final int TRIP_STATUS_PAST = 1;
    public static final int TRIP_STATUS_DELETED = 2;
    public static final int TRIP_STATUS_DONE = 3;
    private String rootStatus = "root";
    int status  = TRIP_STATUS_UPCOMING ;//0 upcoming 1 past 2 deleted




    public trips(){

    }


    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public trips(String tripName, Place startPlace, Place endPlace,
                 String tripDate, String tripTime , ArrayList tripNotes, Boolean roundTrip){
        this.tripName = tripName;
        this.startPlace = new CustomPlace(
                startPlace.getId(),
                startPlace.getLatLng().latitude,
                startPlace.getLatLng().longitude,
                startPlace.getName().toString());

        this.endPlace = new CustomPlace(endPlace.getId(),
                endPlace.getLatLng().latitude,
                startPlace.getLatLng().longitude,
                endPlace.getName().toString());

        Log.i("test1",startPlace.getLatLng().latitude+"");

        this.tripDate = tripDate;
        this.tripNotes = tripNotes;
        this.roundTrip = roundTrip;
        this.tripTime = tripTime;
    }

    public String getTripName() {
        return tripName;
    }

    public void setTripName(String tripName) {
        this.tripName = tripName;
    }

    public CustomPlace getStartPlace() {
        return startPlace;
    }

    public void setStartPlace(CustomPlace startPlace) {
        this.startPlace = startPlace;
    }

    public CustomPlace getEndPlace() {
        return endPlace;
    }

    public void setEndPlace(CustomPlace endPlace) {
        this.endPlace = endPlace;
    }

    public String getTripDate() {
        return tripDate;
    }

    public void setTripDate(String tripDate) {
        this.tripDate = tripDate;
    }

    public ArrayList getTripNotes() {
        return tripNotes;
    }

    public void setTripNotes(ArrayList tripNotes) {
        this.tripNotes = tripNotes;
    }

    public Boolean getRoundTrip() {
        return roundTrip;
    }

    public void setRoundTrip(Boolean roundTrip) {
        this.roundTrip = roundTrip;
    }

    @Override
    public String toString() {
        return tripName;
    }


    public String getTripTime() {
        return tripTime;
    }

    public void setTripTime(String tripTime) {
        this.tripTime = tripTime;
    }

    public String getTripStatus() {
        return tripStatus;
    }

    public void setTripStatus(String tripStatus) {
        this.tripStatus = tripStatus;
    }

    public String getRootStatus() {
        return rootStatus;
    }

    public void setRootStatus(String rootStatus) {
        this.rootStatus = rootStatus;
    }
}
