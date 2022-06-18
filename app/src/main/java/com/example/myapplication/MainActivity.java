package com.example.myapplication;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity  implements
        AdapterView.OnItemSelectedListener{

    Button btn_send_geopos;
    Button btn_send_message;
    Button btn_check_nohahut;
    Button btn_add_bus;
    Button btn_change_key;
    Intent intent;
    String trip_key="";
    DatabaseReference tripRef;
    ArrayList<Trip>trips;
    ArrayList<Bus>buses;
    int trip_amount =0;
    String[] nums = { "0", "1", "2", "3", "4", "5", "6","7","8","9","10"};
    String busNum="";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        intent = getIntent();
        trip_key = intent.getStringExtra("trip_key");
        Toast.makeText(this, ""+trip_key, Toast.LENGTH_SHORT).show();
        btn_change_key = findViewById(R.id.btn_showTrips);

        //Getting the instance of Spinner and applying OnItemSelectedListener on it
        Spinner spin = (Spinner) findViewById(R.id.spinner);
        spin.setOnItemSelectedListener(this);

        //Creating the ArrayAdapter instance having the country list
        ArrayAdapter aa = new ArrayAdapter(this,android.R.layout.simple_spinner_item,nums);
        aa.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        //Setting the ArrayAdapter data on the Spinner
        spin.setAdapter(aa);


        btn_change_key.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this,
                        All_Trips.class));
            }
        });

        btn_check_nohahut = findViewById(R.id.btn_to_check_attendance);
        btn_check_nohahut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, Students_Bus.class);
                intent.putExtra("trip_key", trip_key);
                intent.putExtra("bus_num", Integer.valueOf(busNum));
                startActivity(intent);
            }
        });
        btn_add_bus = findViewById(R.id.btn_add_bus);

        btn_add_bus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getBusAmount();
            /*    Intent intent = new Intent(MainActivity.this, Add_Bus.class);
                intent.putExtra("trip_key", trip_key);
                startActivity(intent);*/
            }
        });

        btn_send_geopos = findViewById(R.id.btn_send_geopos);
        btn_send_geopos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, GetLocation.class);
                startActivity(intent);
            }
        });
        btn_send_message = findViewById(R.id.btn_to_send_message);
    }

    //Performing action onItemSelected and onNothing selected
    @Override
    public void onItemSelected(AdapterView<?> arg0, View arg1, int position, long id) {
        Toast.makeText(getApplicationContext(),nums[position] , Toast.LENGTH_LONG).show();
        busNum = nums[position];
    }
    @Override
    public void onNothingSelected(AdapterView<?> arg0) {
        // TODO Auto-generated method stub
    }

    public void getBusAmount() {
        tripRef = FirebaseDatabase.getInstance().getReference("Trips/" );
        tripRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                trips = new ArrayList<Trip>();
                for (DataSnapshot data:dataSnapshot.getChildren()) {
                    Trip trip = data.getValue(Trip.class);
                    if(trip.getKey().equals(trip_key)) {
                        trip_amount = trip.getBut_amount();
                        howManyBusesAre(trip_amount);
                    }
                }


            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }
    public void howManyBusesAre(int trip_amount) {
        DatabaseReference busRef = FirebaseDatabase.getInstance().getReference("Buses/" );
        busRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                buses = new ArrayList<Bus>();
                int current_amount=0;
                for (DataSnapshot data:dataSnapshot.getChildren()) {
                    Bus b = data.getValue(Bus.class);
                    if (b.getTrip_code().equals(trip_key)) {
                        current_amount++;
                    }
                }
                if (current_amount>=trip_amount)
                    Toast.makeText(MainActivity.this, "Canr add a new Bus, all are already filled", Toast.LENGTH_SHORT).show();
                else{
                    Intent intent = new Intent(MainActivity.this, Add_Bus.class);
                    intent.putExtra("trip_key", trip_key);
                    startActivity(intent);
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }
}