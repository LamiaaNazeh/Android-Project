package com.android.abdelmun3m.tripplaner;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.google.android.gms.location.places.Place;

public class listViewActivity extends AppCompatActivity {

    private trips[] tripsArray;

//***Issues
    //(1)###Trips static data


    /*tripName, PlaceAutocompleteFragment startPlace, PlaceAutocompleteFragment endPlace, Date tripDate, String tripNotes, Boolean roundTrip) {
        this.tripName = tripName;
        this.startPlace = startPlace;*/
//    private trips[] tripsArray={
//            new trips("Trip1","mansoura",),
//            new trips("Cake2","Cake2 Desc"),
//            new trips("Cake3","Cake3 Desc"),
//            new trips("Cake4","Cake4 Desc")
//
//
//    };

    private CustomArrayAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_view);

        ListView listView = findViewById(R.id.listTrips);
        adapter =new CustomArrayAdapter(this,R.layout.singlerow,R.id.NameTextView,tripsArray);
        listView.setAdapter(adapter);

    }

    class CustomArrayAdapter extends ArrayAdapter<trips>
    {
        trips[] myTrips;
        public CustomArrayAdapter(@NonNull Context context, int resource, int textViewResourceId, @NonNull trips[] my_Trips) {
            super(context, resource, textViewResourceId, my_Trips);
            // this.context = context;
            myTrips = my_Trips;


        }

        @NonNull
        @Override


        public View getView(int position, @Nullable View view, @NonNull ViewGroup parent) {
            View singleRow = view;
            viewHolder myviewHolder;
            TextView tripName;
            Place startPlace;
            Place endPlace;
            DatePicker tripDate;

            TextView tripNotes;
            TextView oneDirTrip;
            TextView roundTrip;






            if(singleRow == null)
            {
                LayoutInflater inflater =(LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                //singleRow = inflater.inflate(R.layout.singlerow,parent,false);
                myviewHolder = new viewHolder(singleRow);
                singleRow.setTag(myviewHolder);

            }
            else {
                myviewHolder = (viewHolder) singleRow.getTag();
            }

            /*
              Place startPlace;
             Place endPlace;
             DatePicker tripDate;

             TextView tripNotes;
             TextView oneDirTrip;
             TextView roundTrip;
             */

            myviewHolder.getTripName().setText(myTrips[position].getTripName());

            //***Issues
            //(2)###Trips retrieve  places , date and roundTrip(in trips class,there is getRoundTrip() but there is not getoneDirTrip()) data

            //myviewHolder.getStartPlace().setPlace(myTrips[position].getStartPlace());
            //myviewHolder.getEndPlace().setPlace(myTrips[position].getEndPlace());
           // myviewHolder.getTripDate().setDate(myTrips[position].getTripDate());
           // myviewHolder.getTripNotes().setText(myTrips[position].getTripNotes());
            //myviewHolder.getOneDirTrip().setText(myTrips[position].get);
          // myviewHolder.getRoundTrip().setText(myTrips[position].getRoundTrip());
            return singleRow;



        }

    }
}
