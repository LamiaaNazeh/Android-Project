package com.android.abdelmun3m.tripplaner;

import android.view.View;
import android.widget.DatePicker;
import android.widget.TextView;

import com.google.android.gms.location.places.Place;
import com.google.android.gms.location.places.ui.PlaceAutocomplete;
import com.google.android.gms.location.places.ui.PlaceAutocompleteFragment;

import java.util.Date;

/**
 * Created by Asmaa on 3/30/2018.
 */

public class viewHolder {

    View convertView;
    TextView tripName;
    Place startPlace;

    Place endPlace;
    DatePicker tripDate;

    TextView tripNotes;
    TextView oneDirTrip;
    TextView roundTrip;

    public viewHolder(View convertView) {
        this.convertView = convertView;
    }

    public TextView getTripName() {
       if (tripName ==null)
           tripName = (TextView)convertView.findViewById(R.id.NameEditText);
       return tripName;

    }

    public Place getStartPlace() {
        if(startPlace == null)
           startPlace = (Place)convertView.findViewById(R.id.place_autocomplete_fragment_start);
        return startPlace;
    }

    public Place getEndPlace() {
        if(endPlace == null)
            endPlace = (Place)convertView.findViewById(R.id.place_autocomplete_fragment_end);
        return endPlace;
    }

    public DatePicker getTripDate() {
        //if (tripDate ==null)
          //  tripDate= (DatePicker) convertView.findViewById(R.id.tripDatePicker);
        return tripDate;
    }

    public TextView getTripNotes() {
        if (tripNotes ==null)
            tripNotes = (TextView)convertView.findViewById(R.id.CommentTextView);
        return tripNotes;
    }

    public TextView getRoundTrip() {
       // if (roundTrip ==null)
           // roundTrip = (TextView)convertView.findViewById(R.id.TowDirection);
        return roundTrip;
    }

    public TextView getOneDirTrip() {
        //if (oneDirTrip ==null)
          //  oneDirTrip = (TextView)convertView.findViewById(R.id.OneDirection);
        return oneDirTrip;
    }


}
