package com.example.myapplication;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Patterns;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;


public class mail_pass extends AppCompatActivity {

    SharedPreferences sp;
    SharedPreferences.Editor editor;
    Button btn_sign_up;
    CheckBox saveme;
    EditText email, pass;

    FirebaseAuth firebaseAuth;
    ProgressDialog progressDialog;

    int adminMode=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mail_pass);

        firebaseAuth = FirebaseAuth.getInstance();

        popUp_dialog();
        btn_sign_up = findViewById(R.id.btn_signe_up);
        saveme = findViewById(R.id.checkBox);
        email = findViewById(R.id.et_enter_email);
        pass = findViewById(R.id.et_enter_pass);


        sp = mail_pass.this.getSharedPreferences("MyPref", 0);
        editor = sp.edit();


        if (sp.contains("Email")) {
            email.setText(sp.getString("Email", ""));
        }

        if (sp.contains("Password")) {
            pass.setText(sp.getString("Password", ""));
        }

        if (sp.contains("Email") && sp.contains("Password")) {

            Intent intent = new Intent(mail_pass.this,
                    enter_to_trip.class);
            intent.putExtra("email",
                    email.getText().toString());
            intent.putExtra("password",
                    pass.getText().toString());
            startActivity(intent);
        }

        btn_sign_up.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (userLogin()) {

                    Intent intent = new Intent(mail_pass.this,
                            enter_to_trip.class);
                    intent.putExtra("email",
                            email.getText().toString());
                    intent.putExtra("password",
                            pass.getText().toString());
                    startActivity(intent);

                }
            }
        });


    }

    private void popUp_dialog() {
        AlertDialog.Builder alertDialogBuilder =
                new AlertDialog.Builder(mail_pass.this);
        // set title
        alertDialogBuilder.setTitle("Welcome");
        // set dialog message
        alertDialogBuilder.setMessage("if you are all ready registered,\n"+
                "just enter email and password !").setCancelable(false);
        // create alert dialog
        AlertDialog alertDialog = alertDialogBuilder.create();
        // show it
        alertDialog.show();
        // After some action
        alertDialog.dismiss();
    }

    private boolean userLogin() {

        boolean isChecked = saveme.isChecked();
        String mail = email.getText().toString().trim();
        String password = pass.getText().toString().trim();

        if (mail.isEmpty()) {
            email.setError("Email is required!");
            email.requestFocus();
            return false;
        }

        if (!Patterns.EMAIL_ADDRESS.matcher(mail).matches()) {
            email.setError("Please provide valid email!");
            email.requestFocus();
            return false;
        }

        if (password.isEmpty()) {
            pass.setError("Password is required!");
            pass.requestFocus();
            return false;
        }

        if (password.length() < 6) {
            pass.setError("Min password length should be 6 characters!");
            pass.requestFocus();
            return false;
        }

        //если только аторизация прошла, сохранить логин и пароль
//
        if (isChecked) {
            saveUserData();
        }

        return true;
    }

    private void saveUserData() {
        String mail = email.getText().toString();
        String password = pass.getText().toString();

        editor.putString("Email", mail);
        editor.apply();
        editor.putString("Password", password);
        editor.apply();

    }



/*
    public void register()
    {

        firebaseAuth.createUserWithEmailAndPassword(
                etEmail.getText().toString(),
                etPass.getText().toString()).addOnCompleteListener(this,
                new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(Task<AuthResult> task) {
                if (task.isSuccessful()) {
                    Toast.makeText(MainActivity.this,
                            "Successfully registered", Toast.LENGTH_LONG).show();
                    if(adminMode==0){

                    }

                } else {
                    Toast.makeText(MainActivity.this,
                            "Registration Error", Toast.LENGTH_LONG).show();

                }
            }
        });

    }

    public  void login()
    {
        firebaseAuth.signInWithEmailAndPassword(
                etEmail.getText().toString(),
                        etPass.getText().toString())
                .addOnCompleteListener(this,
                        new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(Task<AuthResult> task) {

                        if(task.isSuccessful()) {
                            Toast.makeText(MainActivity.this,
                                    "auth_success",Toast.LENGTH_SHORT).show();
                            btnMainLogin.setText("Logout");

                        }
                        else
                        {
                            Toast.makeText(MainActivity.this,
                                    "auth_failed",Toast.LENGTH_SHORT).show();

                        }
                    }
                });

    }

    */

}