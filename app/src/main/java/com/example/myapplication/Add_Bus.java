package com.example.myapplication;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class Add_Bus extends Daniel_Template_Screen {
    Button btn_add_to_trip, btn_clear;
    private TextView tv_key_of_trip;
    private EditText et_add_bus_num, et_add_bus_name, et_add_bus_phone, et_add_bus_capacity;
    private String trip_key = "";
    DatabaseReference busRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_bus_to_trip);

        Intent intent = getIntent();
        trip_key = intent.getExtras().getString("trip_key");
        Toast.makeText(this, ""+trip_key, Toast.LENGTH_SHORT).show();

        tv_key_of_trip = findViewById(R.id.tv_key_of_trip);
        tv_key_of_trip.setText(trip_key);
        et_add_bus_num = findViewById(R.id.et_add_bus_num);
        et_add_bus_name = findViewById(R.id.et_add_bus_name);
        et_add_bus_phone = findViewById(R.id.et_add_bus_phone);
        et_add_bus_capacity = findViewById(R.id.et_add_bus_capacity);

        btn_clear = findViewById(R.id.btn_clear);
        btn_clear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                et_add_bus_num.setText("");
                et_add_bus_name.setText("");
                et_add_bus_phone.setText("");
                et_add_bus_capacity.setText("");
            }
        });
        btn_add_to_trip = findViewById(R.id.btn_add_to_trip);
        btn_add_to_trip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (addBusToFirebase()) {
                    Intent intent = new Intent(Add_Bus.this,
                            MainActivity.class);
                    intent.putExtra("trip_key", trip_key);
                    startActivity(intent);
                } else
                    Toast.makeText(Add_Bus.this, "fill all fields!", Toast.LENGTH_SHORT).show();
            }
        });

    }

    private boolean addBusToFirebase() {
        String name = et_add_bus_name.getText().toString();
        String num = et_add_bus_num.getText().toString();
        String phone = et_add_bus_phone.getText().toString();
        String capacity = et_add_bus_capacity.getText().toString();
        if (et_add_bus_num.getText().toString().trim().length() != 0 &&
                name.trim().length() != 0 &&
                et_add_bus_phone.getText().toString().trim().length() != 0 &&
                et_add_bus_capacity.getText().toString().trim().length() != 0) {
            busRef = FirebaseDatabase.getInstance().getReference(Busses_DatabseName).push();
            Bus b = new Bus(name, phone, trip_key, Integer.valueOf(capacity), Integer.valueOf(num));
            busRef.setValue(b);
            return true;
        }
        return false;
    }
}