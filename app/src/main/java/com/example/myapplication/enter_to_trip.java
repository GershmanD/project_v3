package com.example.myapplication;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import android.app.Dialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.ContextMenu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;

public class enter_to_trip extends Daniel_Template_Screen
        implements View.OnClickListener {
    private FloatingActionButton fb_enter_key;
    private EditText et_key_of_trip;
    private EditText etKey, etEmail, etPass;    //dialogs
    private String key = "";
    Button btn_LogOut, btn_LogIn, btnSubmit_Admin;  //dialogs
    pl.droidsonroids.gif.GifImageView img_bus;
    ImageButton btn_admin, btn_teacher, btn_contact, btn_relax;
    ConstraintLayout enter_screen_layout;
    Dialog d;
    int count = 0;
    DatabaseReference managers_ref;
    DatabaseReference tripRef;
     String tr_key="";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_enter_to_trip);

        referencingAll();


    }

    private void referencingAll() {
        d = new Dialog(this);
        et_key_of_trip = findViewById(R.id.et_key_of_trip);
        enter_screen_layout = findViewById(R.id.enter_screen_layout);
        img_bus = findViewById(R.id.img_bus);
        moveImage(getApplicationContext(), img_bus);
        fb_enter_key = findViewById(R.id.fb_enter_key);
        fb_enter_key.setOnClickListener(this);
        btn_admin = findViewById(R.id.btn_admin);
        btn_teacher = findViewById(R.id.btn_teacher);
        btn_admin.setOnClickListener(this);
        btn_teacher.setOnClickListener(this);
        btn_relax = findViewById(R.id.btn_relax);
        btn_relax.setOnClickListener(this);
        registerForContextMenu(btn_teacher);
        registerForContextMenu(btn_admin);
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        MenuInflater menuiflatr = getMenuInflater();
        if (v.getId() == R.id.btn_admin) {
            menuiflatr.inflate(R.menu.menu_admin, menu);
            menu.setHeaderTitle("Admin OPtions");
      /*      // add menu items
            menu.add(0, v.getId(), 0, "Enter to trip");
            menu.add(0, v.getId(), 0, "Logout");
            menu.add(0, v.getId(), 0, "relaxation");*/
        } else if (v.getId() == R.id.btn_teacher) {
            //inflate another menu
            menuiflatr.inflate(R.menu.menu_teacher, menu);
            menu.setHeaderTitle("Teacher OPtions");
            /*menu.setHeaderIcon(R.drawable.ic_launcher);
            MenuItem item_On = menu.findItem(R.id.on);
            MenuItem item_Off = menu.findItem(R.id.off);*/
        }
    }
    // menu item select listener
    @Override
    public boolean onContextItemSelected(MenuItem item) {
        switch(item.getItemId()){
            case R.id.create_trip:
                //is admin?
                startActivity(new Intent(enter_to_trip.this,
                        CreateTrip.class));
                break;
            case R.id.add_manager:
                //if admin do something
                break;
            case R.id.add_bus:
                //check if the given trip key by teacher is ok
                startActivity(new Intent(enter_to_trip.this,
                        Add_Bus.class));
                break;
            //below menu of teacher
            case R.id.enetr_trip:
                enterToTrip();
                break;
            case R.id.all_students:
                //
                break;

        }


        return true;
    }

    private void enterToTrip() {
        fb_enter_key.setVisibility(View.VISIBLE);
        et_key_of_trip.setVisibility(View.VISIBLE);

    }
    public void isCorrectKey(){
        String input_key = et_key_of_trip.getText().toString();
        //Log.d("tag", "input:"+input_key);
        tripRef =
                FirebaseDatabase.getInstance().getReference(Trip_DatabseName+"/");
        tripRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot data : snapshot.getChildren()) {
                    int tr_but_amount = Integer.valueOf(data.child("but_amount").getValue().toString());
                    tr_key = data.child("key").getValue().toString();
                    String tr_place = data.child("place").getValue().toString();
                    String tr_title = data.child("title").getValue().toString();
                    String tr_date = data.child("date").getValue().toString();
                    Log.d("x", "input:"+input_key+" and key:"+tr_key);
                    if (tr_key.equals(input_key)) {
                        Log.d("x","input:"+input_key+" and key:"+tr_key);
                        Intent intent = new Intent(enter_to_trip.this,
                                MainActivity.class);
                        intent.putExtra("trip_key", tr_key);
                        startActivity(intent);
                    }
                }
                Log.d("x","input:"+input_key+" and key:"+tr_key);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_LogIn:
                if (etKey.getText().toString().equals(key)) {
                    Intent intent = new Intent(com.example.myapplication.enter_to_trip.this,
                            MainActivity.class);
                    intent.putExtra("trip_key", key);
                    startActivity(intent);

                } else {
                    makeSnakebar(this, enter_screen_layout, "wrong key!");
                }
                break;

            case R.id.btn_admin:
                howTo_dialog();
                break;


            case R.id.btn_teacher:
                howTo_dialog();
                break;
            case R.id.btn_relax:
                Intent intent = new Intent(Intent.ACTION_VIEW).setData(Uri.parse(
                        "https://www.youtube.com/watch?v=z6EchXyieos&t=9s&ab_channel=FunniestAnimalsEver"));
                startActivity(intent);
                break;

            case R.id.fb_enter_key:
                isCorrectKey();
                break;
        }
    }

    private void checkAdmin() {
        String name = etEmail.getText().toString();
        String phone = etPass.getText().toString();
        managers_ref = FirebaseDatabase.getInstance().
                getReference(Management_DatabseName + "/");
        managers_ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot data : snapshot.getChildren()) {
                    String adminName = data.child("name").getValue().toString();
                    String adminPhone = data.child("phone").getValue().toString();
                    if (adminName.equals(name) && adminPhone.equals(phone)){
                        Intent intent = new Intent(enter_to_trip.this, MainActivity.class);
                        intent.putExtra("trip_key", key);
                        startActivity(intent);
                    }
                        startActivity(new Intent(getApplicationContext(),
                                MainActivity.class));
                }


            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    public void createTeacher_Dialog() {
        d.setContentView(R.layout.teacher_dialog);
        d.setTitle("Select an option");
        d.setCancelable(true);
        etKey = d.findViewById(R.id.etKey);
        btn_LogIn = d.findViewById(R.id.btn_LogIn);
        btn_LogOut = d.findViewById(R.id.btn_LogOut);
        btn_LogOut.setOnClickListener(this);
        btn_LogIn.setOnClickListener(this);
        d.show();

    }

    public void howTo_dialog() {
        d = new Dialog(this);
        d.setContentView(R.layout.admin_dialog);
        d.setTitle("Login");
        d.setCancelable(true);
        btnSubmit_Admin = (Button) d.findViewById(R.id.btnSubmit_Admin);
        btnSubmit_Admin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                d.dismiss();
            }
        });
        d.show();

    }
}