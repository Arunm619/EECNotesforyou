package com.arunsudharsan.eecnotesforyou;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.annotation.NonNull;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class SignUpActivity extends AppCompatActivity {

    SharedPreferences sharedpreferences;
    public static final String mypreference = "mypref";
    public static final String Name = "nameKey";
    public static final String Email = "emailKey";

    private ProgressDialog progressDialog;

    private Button btn_signup;
    private EditText emailid;
    private EditText password;
    private EditText username;


    private FirebaseAuth firebaseAuth;
    private TextView gotologin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);


        firebaseAuth = FirebaseAuth.getInstance();
        if (firebaseAuth.getCurrentUser() != null) {
            //that means user is already logged in
            //so close this activity
            finish();
            startActivity(new Intent(getApplicationContext(), AreyouteacherorStudent.class));
        }
        isOnline();

        username = (EditText) findViewById(R.id.edittext_username);
        emailid = (EditText) findViewById(R.id.edittext_emailaddress);
        password = (EditText) findViewById(R.id.edittext_password);
        btn_signup = (Button) findViewById(R.id.btn_signup);
        gotologin = (TextView) findViewById(R.id.gotologin);
        progressDialog = new ProgressDialog(this);
        gotologin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
                startActivity(new Intent(getApplicationContext(), LogInactivity.class));
            }
        });
        btn_signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                registerUser();
            }
        });

    }

    private void registerUser() {
        String email = emailid.getText().toString();
        final String name = username.getText().toString();
        String pwd = password.getText().toString();
        View parentLayout = findViewById(R.id.activity_sign_up);

        if (TextUtils.isEmpty(email)) {
            Snackbar.make(parentLayout, "Enter Email ID...", Snackbar.LENGTH_LONG).show();
            return;
        }

        if (TextUtils.isEmpty(pwd)) {
            Snackbar.make(parentLayout, "Enter Password...", Snackbar.LENGTH_LONG).show();

            return;
        }


        if (pwd.length() > 12) {

            Snackbar.make(parentLayout, "Password too long", Snackbar.LENGTH_LONG).show();

            return;
        }


        if (pwd.length() < 5) {

            Snackbar.make(parentLayout, "Password too short. MIN[6 Characters]", Snackbar.LENGTH_LONG).show();

            return;

        }


        progressDialog.setMessage("Creating Account...");
        progressDialog.show();
        firebaseAuth.createUserWithEmailAndPassword(email, pwd)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            View parentLayout = findViewById(R.id.activity_sign_up);
                            Snackbar.make(parentLayout, "Successfully Registered...", Snackbar.LENGTH_LONG).show();


                            finish();

                            startActivity(new Intent(getApplicationContext(), AreyouteacherorStudent.class));

                            progressDialog.dismiss();
                        } else {
                            View parentLayout = findViewById(R.id.activity_sign_up);
                            Snackbar.make(parentLayout, "Couldn't register... please try again", Snackbar.LENGTH_LONG).show();
                            progressDialog.dismiss();


                        }

                    }
                });
    }

    private void isOnline() {
        ConnectivityManager conMgr = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo netInfo = conMgr.getActiveNetworkInfo();
        if (netInfo == null) {
            new AlertDialog.Builder(SignUpActivity.this)
                    .setTitle(getResources().getString(R.string.app_name))
                    .setCancelable(false)
                    .setMessage(getResources().getString(R.string.internet_error))
                    .setNeutralButton("Reload", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {

                            isOnline();
                        }

                    }).setNegativeButton("Quit", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    finish();
                }
            }).show();

        }
    }

}

