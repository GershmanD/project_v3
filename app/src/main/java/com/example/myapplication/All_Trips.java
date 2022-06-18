package com.example.myapplication;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

public class All_Trips extends Daniel_Template_Screen {
    ListView lv;
    ArrayList<Trip> trips;
    TripsAdapter allPostAdapter;
    private DatabaseReference database;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_trips);
        database = FirebaseDatabase.getInstance().getReference(Trip_DatabseName);
        lv = findViewById(R.id.lv_trips);
        this.retriveData();

        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Trip trip = trips.get(position);
                Intent intent = new Intent(All_Trips.this, Busses_In_Trip.class);
                intent.putExtra("trip_key", trip.getKey());
                startActivity(intent);
            }
        });

/*
        lv.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                Post p = posts.get(position);
                DatabaseReference current = FirebaseDatabase.getInstance().getReference("Posts/" + p.key);
                current.removeValue();
                return true;


            }
        });*/
    }

    public void retriveData() {
        database.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                trips = new ArrayList<Trip>();
                for (DataSnapshot data : dataSnapshot.getChildren()) {
                    Trip p = data.getValue(Trip.class);
                    trips.add(p);
                }
                allPostAdapter = new TripsAdapter(All_Trips.this, 0, 0, trips);
                lv.setAdapter(allPostAdapter);

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }
}