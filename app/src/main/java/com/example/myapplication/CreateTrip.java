package com.example.myapplication;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Calendar;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import androidx.constraintlayout.widget.ConstraintLayout;
import pl.droidsonroids.gif.GifImageView;

public class CreateTrip extends Daniel_Template_Screen implements View.OnClickListener {
    pl.droidsonroids.gif.GifImageView pic;
    Button btn_generate, btn_date, btn_create, btn_cancel;
    TextView tv_generate, tv_date;
    int month=0, year=0, day=0;
    String key_trip="";
    ConstraintLayout create_trip_layout;
    DatabaseReference trip_ref;
    EditText et_busses, et_busses_title, et_busses_place;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_trip);

        pic = findViewById(R.id.picc);
        moveImage(CreateTrip.this, pic);
        initElements();
    }

    private void initElements() {
        create_trip_layout = findViewById(R.id.create_trip_layout);
        tv_generate = findViewById(R.id.tv_generate);
        tv_date = findViewById(R.id.tv_date);
        et_busses = findViewById(R.id.et_busses);
        et_busses_place = findViewById(R.id.et_busses_place);
        et_busses_title = findViewById(R.id.et_busses_title);
        btn_generate = findViewById(R.id.btn_generate);
        btn_date = findViewById(R.id.btn_date);
        btn_create = findViewById(R.id.btn_create);
        btn_cancel = findViewById(R.id.btn_cancel);

        btn_generate.setOnClickListener(this);
        btn_date.setOnClickListener(this);
        btn_create.setOnClickListener(this);
        btn_cancel.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btn_cancel:
                startActivity(new Intent(getApplicationContext(), enter_to_trip.class));
                break;

            case R.id.btn_create:
                if(key_trip.trim().equals("") || year==0)
                    makeSnakebar(this, create_trip_layout,
                            "fill all data to continue!");
                else
                    addTrip_ToDatbase();
                break;

            case R.id.btn_date:
                openDate_dialog();
                break;

            case R.id.btn_generate:
                key_trip = getRandomString(6);
                tv_generate.setText(key_trip);
                break;
                
        }
    }

    private void addTrip_ToDatbase() {
        String date = tv_date.getText().toString();
        int amount = Integer.valueOf(et_busses.getText().toString());
        Trip t = new Trip(amount, et_busses_title.getText().toString(),
                key_trip,date, et_busses_place.getText().toString());

        trip_ref = FirebaseDatabase.getInstance().getReference(Trip_DatabseName).push();
        trip_ref.setValue(t);
        Toast.makeText(this, "trip created succefully!", Toast.LENGTH_SHORT).show();
    }

    private void openDate_dialog() {

        // Get Current Date
        final Calendar c = Calendar.getInstance();
        year = c.get(Calendar.YEAR);
        month = c.get(Calendar.MONTH);
        day = c.get(Calendar.DAY_OF_MONTH);


        DatePickerDialog datePickerDialog = new DatePickerDialog(this,
                new DatePickerDialog.OnDateSetListener() {

                    @Override
                    public void onDateSet(DatePicker view, int year,
                                          int monthOfYear, int dayOfMonth) {

                        tv_date.setText(dayOfMonth + "-" + (monthOfYear + 1) + "-" + year);

                    }
                }, year, month, day);
        datePickerDialog.show();
    }
}