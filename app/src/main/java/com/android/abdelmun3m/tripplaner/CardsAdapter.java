package com.android.abdelmun3m.tripplaner;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.firebase.jobdispatcher.Constraint;
import com.firebase.jobdispatcher.FirebaseJobDispatcher;
import com.firebase.jobdispatcher.GooglePlayDriver;
import com.firebase.jobdispatcher.Job;
import com.firebase.jobdispatcher.Lifetime;
import com.firebase.jobdispatcher.RetryStrategy;
import com.firebase.jobdispatcher.Trigger;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import static android.support.v4.content.ContextCompat.startActivity;

/**
 * Created by lamiaa on 24/03/18.
 */

public class CardsAdapter extends RecyclerView.Adapter<CardsAdapter.CardsViewHolder>{

    private Context myCtx;
    private ArrayList<trips> CardsList;
    private FirebaseJobDispatcher dispatcher;

    public CardsAdapter(Context myCtx, ArrayList<trips> cardsList) {
        this.myCtx = myCtx;
        this.CardsList = cardsList;
    }



    @Override
    public void onBindViewHolder(CardsViewHolder holder, final int position) {

        final trips card = CardsList.get(position);
        holder.tripName.setText(card.getTripName());
        holder.tripstrPoint.setText(card.getStartPlace().sName);
        holder.tripendPoint.setText(card.getEndPlace().sName);
        holder.tripDateTime.setText(card.getTripDate());
        holder.tripStatus.setText(card.getTripStatus());
        holder.tripDirStatus.setText(card.getRootStatus());

        dispatcher = new FirebaseJobDispatcher(new GooglePlayDriver(myCtx));


        //Start trip Button
        holder.startTripBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               //String srtpoint = card.getStartPlace().sName;
              //  String endpoint = card.getEndPlace().sName();
                String ur="";
                Toast.makeText(myCtx, card.getStartPlace().latitude+","+card.getStartPlace().longitude +"&" +card.getEndPlace().latitude+","+card.getEndPlace().longitude , Toast.LENGTH_LONG).show();
                final String uri = "https://www.google.com/maps/dir/?api=1&map_action=pano&origin=";
                ur= uri.concat(
                        card.getStartPlace().latitude+"%2C"+card.getStartPlace().longitude+
                                "&destination="+
                                card.getEndPlace().latitude+"%2C"+card.getEndPlace().longitude
                );
                final Intent intent = new Intent(android.content.Intent.ACTION_VIEW, Uri.parse(ur));
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                intent.setClassName("com.google.android.apps.maps",
                        "com.google.android.maps.MapsActivity");
                myCtx.startActivity(intent);
               // Toast.makeText(myCtx, ""+ur, Toast.LENGTH_SHORT).show();

//
                Bundle myExtrasBundle = new Bundle();
                myExtrasBundle.putString("some_key", "some_value");

                Job myJob = dispatcher.newJobBuilder()
                        // the JobService that will be called
                        .setService(TripJobService.class)
                        // uniquely identifies the job
                        .setTag("my-unique-tag")
                        // one-off job
                        .setRecurring(false)
                        // don't persist past a device reboot
                        .setLifetime(Lifetime.UNTIL_NEXT_BOOT)
                        // start between 0 and 60 seconds from now
                        .setTrigger(Trigger.executionWindow(0, 60))
                        // don't overwrite an existing job with the same tag
                        .setReplaceCurrent(false)
                        // retry with exponential backoff
                        .setRetryStrategy(RetryStrategy.DEFAULT_EXPONENTIAL)
                        // constraints that need to be satisfied for the job to run
                        .setConstraints(
                                // only run on an unmetered network
                                Constraint.ON_UNMETERED_NETWORK,
                                // only run when the device is charging
                                Constraint.DEVICE_CHARGING
                        )
                        .setExtras(myExtrasBundle)
                        .build();

                dispatcher.mustSchedule(myJob);


            }
        });

        //edit card
       holder.editCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(myCtx , AddTripActivity.class);
                myCtx.startActivity(intent);

            }
        });


       //delete card
        holder.deletCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                CardsList.remove(position);
                CardsAdapter.this.notifyDataSetChanged();
            }
        });


    }

    @Override
    public int getItemCount() {

        return CardsList!=null? CardsList.size():0;
    }

    public class CardsViewHolder extends RecyclerView.ViewHolder{

        TextView tripName , tripstrPoint , tripendPoint , tripDateTime , tripStatus , tripDirStatus;
        //ImageView deleteCard;
        public Button startTripBtn;
        public ImageView startImage , deletCard , editCard;
        public CardsViewHolder(View itemView) {
            super(itemView);
            tripName = itemView.findViewById(R.id.tripName);
            tripstrPoint = itemView.findViewById(R.id.tripstrPoint);
            tripendPoint = itemView.findViewById(R.id.tripendPoint);
            tripDateTime = itemView.findViewById(R.id.tripDateTime);
            tripStatus = itemView.findViewById(R.id.tripStatus);
            tripDirStatus = itemView.findViewById(R.id.tripDirStatus);

            startTripBtn = itemView.findViewById(R.id.launchTrip);

            editCard = itemView.findViewById(R.id.editCard);

            deletCard = itemView.findViewById(R.id.deletCard);



        }

    }
    @Override
    public CardsViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(myCtx);
        View view = inflater.inflate(R.layout.list_layout, null);
        return new CardsViewHolder(view);

    }

    public void updateCArdList(ArrayList mylist){
        this.CardsList = mylist;
        notifyDataSetChanged();
    }

}
