package com.example.myapplication;

import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.Random;

import android.content.Context;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import androidx.constraintlayout.widget.ConstraintLayout;
import pl.droidsonroids.gif.GifImageView;

public class Daniel_Template_Screen extends AppCompatActivity {
    private static final String ALLOWED_CHARACTERS ="0123456789qwertyuiopasdfghjklzxcvbnm";
    protected GifImageView gifImageView;
    protected String Management_DatabseName = "Management";
    protected String Trip_DatabseName = "Trips";
    protected String Busses_DatabseName = "Busses";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_daniel_template_screen);
        gifImageView = findViewById(R.id.gifImageView);
        //        getSupportActionBar().hide(); //<< this

    }

    public static void makeSnakebar(Context context, View v, String message){
        ConstraintLayout layout = (ConstraintLayout) v;
        Snackbar snackbar = Snackbar
                .make(layout, message, Snackbar.LENGTH_LONG);
        snackbar.show();

    }
    public static String getRandomString(final int sizeOfRandomString)
    {
        final Random random=new Random();
        final StringBuilder sb=new StringBuilder(sizeOfRandomString);
        for(int i=0;i<sizeOfRandomString;++i)
            sb.append(ALLOWED_CHARACTERS.charAt(random.nextInt(ALLOWED_CHARACTERS.length())));
        return sb.toString();
    }

    public static void moveImage(Context context, View view){
        ImageView imageView = (pl.droidsonroids.gif.GifImageView) view;
        Animation animation = AnimationUtils.loadAnimation(context, R.anim.move);
        imageView.startAnimation(animation);
    }
}