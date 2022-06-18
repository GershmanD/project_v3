package com.example.myapplication;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

import android.app.Dialog;
import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

public class Students_In_Bus extends AppCompatActivity implements View.OnClickListener {
    FloatingActionButton fb_add_student;
    ListView lv_students_inBus;
    DatabaseReference busRef, studentsRef;
    String trip_key="";
    int bus_num=0;
    ArrayList<Bus>buses;
    ArrayList<Student>students;
    int studentsAmount=0;
    Dialog d;
    EditText et_dialog_phone, et_dialog_class, et_dialog_name;
    Button btn_dialog_add;
    StudentAdpater studentAdpater;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_students_in_bus);
        lv_students_inBus = findViewById(R.id.lv_students_inBus);
        Intent intent = getIntent();
        trip_key = intent.getExtras().getString("trip_key");
        bus_num = intent.getIntExtra("bus_num", 0);
        Toast.makeText(this, "TRIP: "+trip_key+" bus:"+bus_num, Toast.LENGTH_SHORT).show();
        fb_add_student = findViewById(R.id.fb_add_student);
        fb_add_student.setOnClickListener(this);
        retriveData();
    }
    public void getStudentsAmount() {
        busRef = FirebaseDatabase.getInstance().getReference("Busses/" );
        busRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                buses = new ArrayList<Bus>();
                for (DataSnapshot data:dataSnapshot.getChildren()) {
                    Bus b = data.getValue(Bus.class);
                    if(b.getTrip_code().equals(trip_key) && b.getBus_number()==bus_num) {
                        studentsAmount = b.getCapacity_of_bus();
                        howManyStudentsAre(studentsAmount);
                    }
                }


            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }
    public void howManyStudentsAre(int amount) {
        studentsRef = FirebaseDatabase.getInstance().getReference("Students/" );
        studentsRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                students = new ArrayList<Student>();
                int current_amount=0;
                for (DataSnapshot data:dataSnapshot.getChildren()) {
                    Student s = data.getValue(Student.class);
                    if (s.getTrip_key().equals(trip_key) && s.getBus_num()==bus_num) {
                        current_amount++;
                    }
                }
                if (current_amount>=amount)
                    Toast.makeText(Students_In_Bus.this, "Canr add a new Student," +
                            " bus is full", Toast.LENGTH_SHORT).show();
                else{
                    addStudrnt_Dialog();
                    /*Intent intent = new Intent(Students_In_Bus.this, Students_Bus.class);
                    intent.putExtra("trip_key", trip_key);
                    intent.putExtra("bus_num", bus_num);
                    startActivity(intent);*/
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

    public void addStudrnt_Dialog()
    {
        d= new Dialog(this);
        d.setContentView(R.layout.add_student_dialog);
        d.setTitle("Add Student");
        d.setCancelable(true);
        et_dialog_class = d.findViewById(R.id.et_dialog_class);
        et_dialog_name =  d.findViewById(R.id.et_dialog_name);
        et_dialog_phone =  d.findViewById(R.id.et_dialog_phone);
        btn_dialog_add = d.findViewById(R.id.btn_dialog_add);
        btn_dialog_add.setOnClickListener(this);
        d.show();

    }

    @Override
    public void onClick(View view) {
        if(view ==fb_add_student){
            getStudentsAmount();
        }
        if(view==btn_dialog_add){
            Student st = new Student(et_dialog_name.getText().toString(),
                    et_dialog_class.getText().toString(),
                    et_dialog_phone.getText().toString(),trip_key, bus_num);
            studentsRef = FirebaseDatabase.getInstance().getReference("Students").push();
            studentsRef.setValue(st);
        }
    }

    public void retriveData() {
        studentsRef = FirebaseDatabase.getInstance().getReference("Students/");
        studentsRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                students = new ArrayList<Student>();
                for (DataSnapshot data : dataSnapshot.getChildren()) {
                    Student st = data.getValue(Student.class);
                    int bs_nm = Integer.valueOf(data.child("bus_num").getValue().toString());
                    String tr_cd = data.child("trip_key").getValue().toString();
                    if(bs_nm == bus_num && tr_cd.equals(trip_key)) {
                        students.add(st);
                    }

                }
                studentAdpater = new StudentAdpater(Students_In_Bus.this,0,
                        0,students);
                lv_students_inBus.setAdapter(studentAdpater);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

    }
}