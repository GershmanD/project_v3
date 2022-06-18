package com.example.myapplication;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

public class start extends Daniel_Template_Screen {
    Handler handler;
    ImageView img_Logo;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);

        img_Logo = findViewById(R.id.iv_logo);
        handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {

                Intent intent = new Intent(start.this,
                        mail_pass.class);
                startActivity(intent);
                finish();

            }
        },3000);

        //createThe_1st();
        Animation animation = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.fade);
        img_Logo.startAnimation(animation);

    }

    /*
    private void createThe_1st() {
            Person p = new Person("admin","","542256123");
            DatabaseReference adminRef = FirebaseDatabase.getInstance().getReference(Management_DatabseName).push();
            p.setKey(adminRef.getKey());
            adminRef.setValue(p);

    }*/
}