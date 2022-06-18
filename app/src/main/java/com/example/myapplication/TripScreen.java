package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.Toast;

import java.util.ArrayList;

public class TripScreen extends AppCompatActivity {

    ArrayList<Bus> buses;
    GridView gridView;
    GridAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trip_screen);

        gridView = findViewById(R.id.gridview_buses_trip);
        buses = new ArrayList<>();

/*
        buses.add(new Bus(1, "Daniel", "Gershman", "0539533447", 25));
        buses.add(new Bus(1, "Daniel", "Gershman", "0539533447", 25));
        buses.add(new Bus(1, "Daniel", "Gershman", "0539533447", 25));
        buses.add(new Bus(1, "Daniel", "Gershman", "0539533447", 25));
        buses.add(new Bus(1, "Daniel", "Gershman", "0539533447", 25));
        buses.add(new Bus(1, "Daniel", "Gershman", "0539533447", 25));
        buses.add(new Bus(1, "Daniel", "Gershman", "0539533447", 25));
        buses.add(new Bus(1, "Daniel", "Gershman", "0539533447", 25));
        buses.add(new Bus(1, "Daniel", "Gershman", "0539533447", 25));

        adapter = new GridAdapter(TripScreen.this, buses);
        gridView.setAdapter(adapter);*/



       /*  gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                //todo - what next after click on current bus
                if(i==1) {
                    Intent intent = new Intent(TripScreen.this, Bus.class);
                    startActivity(intent);
                }
                else if(l==2)
                {
                    Intent intent = new Intent(TripScreen.this, Bus.class);
                    startActivity(intent);

                } else {
                    Intent intent = new Intent(TripScreen.this, Bus.class);
                    startActivity(intent);
                }

    }
            });
*/
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View v, int position, long id) {
                position +=1;
                Toast.makeText(TripScreen.this, "bus #" + position , Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(TripScreen.this, Bus.class);
                startActivity(intent);
            }
        });

        gridView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                //todo - what happens when we long press on single bus
                return true;
            }
        });

    }
}