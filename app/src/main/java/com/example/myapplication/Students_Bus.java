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
import android.widget.Button;
import android.widget.ListView;
import androidx.appcompat.app.AppCompatActivity;

public class Students_Bus extends AppCompatActivity {
    private ListView lv_studentsInBus;
    private ArrayList<Student_As_Item> students;
    private Checkbox_Adapter_Students checkbox_adapter_students;
    private Button btnselect, btndeselect, btnnext;
    DatabaseReference partaniRef;
    Intent intent;
    int bus_num=0;
    String trip_key="";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_students_bus);
        intent = getIntent();
        bus_num = intent.getIntExtra("bus_num", 0);
        trip_key = intent.getStringExtra("trip_key");
        
        lv_studentsInBus = findViewById(R.id.lv_studentsInBus);
        btnselect = (Button) findViewById(R.id.select);
        btndeselect = (Button) findViewById(R.id.deselect);
        btnnext = (Button) findViewById(R.id.next);

        //students = getModel(false);
        checkbox_adapter_students = new Checkbox_Adapter_Students(this, students);
        lv_studentsInBus.setAdapter(checkbox_adapter_students);

        btnselect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //students = getModel(true);
                checkbox_adapter_students = new Checkbox_Adapter_Students(Students_Bus.this, students);
                lv_studentsInBus.setAdapter(checkbox_adapter_students);
            }
        });
        btndeselect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //students = getModel(false);
                checkbox_adapter_students = new Checkbox_Adapter_Students(Students_Bus.this, students);
                lv_studentsInBus.setAdapter(checkbox_adapter_students);
            }
        });
        btnnext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Students_Bus.this, MainActivity.class);
                startActivity(intent);
            }
        });


    }
/*
    private ArrayList<Student_As_Item> getModel(boolean isSelect) {
        partaniRef = FirebaseDatabase.getInstance().getReference("Partaniyot/");

        partaniRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                partaniyot = new ArrayList<Partni>();
                for (DataSnapshot data : dataSnapshot.getChildren()) {
                    Partni prt = data.getValue(Partni.class);
                    String st_nm = data.child("studentName").getValue().toString();
                    if(st_nm.equals(name)) {
                        partaniyot.add(prt);
                    }

                }


                adapter = new PartniAdapter(studentSchedule.this,0,
                        0,partaniyot);
                lvStudentSchedule.setAdapter(adapter);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
        ArrayList<Student_As_Item> list = new ArrayList<>();
        for (int i = 0; i < 4; i++) {

            Student_As_Item model = new Student_As_Item();
            model.setSelected(isSelect);
            list.add(model);
        }
        return list;
    }
*/
}