package com.example.myapplication;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

import android.content.Intent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

public class Busses_In_Trip extends Daniel_Template_Screen {
    EditText etTitle,etBody;
    Button btnSave;
    FirebaseDatabase database;
    DatabaseReference busRef;
    ListView lv_busses;
    String trip_key;
    Trip trip;
    FloatingActionButton fb_all_busses_add;
    ArrayList<Bus> buses;
    GridAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_busses_in_trip);
        Intent intent =getIntent();
        trip_key = intent.getStringExtra("trip_key");
        Toast.makeText(this, ""+trip_key, Toast.LENGTH_SHORT).show();
        lv_busses = findViewById(R.id.lv_busses);
        database = FirebaseDatabase.getInstance();
        retrieveData();

        fb_all_busses_add = findViewById(R.id.fb_all_busses_add);
        fb_all_busses_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Busses_In_Trip.this, Add_Bus.class);
                intent.putExtra("trip_key",trip_key);
                startActivity(intent);
            }
        });

        lv_busses.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Bus bus = buses.get(position);
                Intent intent1 = new Intent(Busses_In_Trip.this,
                        Students_In_Bus.class);
                intent1.putExtra("trip_key", trip_key);
                intent1.putExtra("bus_num", bus.getBus_number());
                startActivity(intent1);

            }
        });
        /*
        etTitle = (EditText) findViewById(R.id.etTitle);
        etBody = (EditText) findViewById(R.id.etBody);
        btnSave = (Button) findViewById(R.id.btnSave);
        btnSave.setOnClickListener(this);
        Intent intent = getIntent();

        trip_key = intent.getExtras().getString("trip_key");
        trip_ref = database.getReference(Trip_DatabseName+"/" + trip_key);
        this.retrieveData();
    }

    public void retrieveData() {
        trip_ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                trip = dataSnapshot.getValue(Trip.class);
                etBody.setText(trip.getDate());
                etTitle.setText(trip.get);

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });


    }
    @Override
    public void onClick(View v) {
        postRef = database.getReference("Posts/" + p.key);

        p.uid = FirebaseAuth.getInstance().getCurrentUser().getUid();
        p.title = etTitle.getText().toString();
        p.body = etBody.getText().toString();
        p.likes = 0;
        postRef.setValue(p);

        finish();


*/
    }
    public void retrieveData() {
        busRef = FirebaseDatabase.getInstance().getReference("Busses/");
        busRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                buses = new ArrayList<Bus>();
                for (DataSnapshot data : dataSnapshot.getChildren()) {
                    Bus bus = data.getValue(Bus.class);
                    String tripcode = data.child("trip_code").getValue().toString();
                    if(tripcode.equals(trip_key)) {
                        buses.add(bus);
                    }

                }


                adapter = new GridAdapter(Busses_In_Trip.this,buses);
                lv_busses.setAdapter(adapter);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }
}