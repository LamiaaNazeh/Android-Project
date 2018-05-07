package com.android.abdelmun3m.tripplaner;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.nfc.Tag;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends Activity {

    ArrayList<trips> cardsList;
    RecyclerView recyclerView;
    CardsAdapter cardsAdapter;

    Button startTripBtn;
    ImageView addTripIcon;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if(DBSingleTon.GET_LOGGED_USER(this) == null){
            finish();
        }


        setContentView(R.layout.activity_main);
        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        cardsAdapter = new CardsAdapter(MainActivity.this,cardsList);
        recyclerView.setAdapter(cardsAdapter);
        addTripIcon = findViewById(R.id.addBtn);
        addTripIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this , AddTripActivity.class));
            }
        });

    }



    @Override
    protected void onResume() {
        super.onResume();

        DBSingleTon.GET_TRIP_REFERENCE().addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                cardsList = new ArrayList<>();
                Log.i("temp",dataSnapshot.toString());
                Toast.makeText(MainActivity.this, ""+dataSnapshot.getChildrenCount(), Toast.LENGTH_SHORT).show();

                for(DataSnapshot ds: dataSnapshot.getChildren()) {
                    cardsList.add(ds.getValue(trips.class));
                    Log.i("test1",ds.getValue(trips.class).getStartPlace().latitude+"");
                }
                cardsAdapter.updateCArdList(cardsList);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }
}
