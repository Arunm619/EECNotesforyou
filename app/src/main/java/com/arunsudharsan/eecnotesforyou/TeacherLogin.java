package com.arunsudharsan.eecnotesforyou;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
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

public class TeacherLogin extends AppCompatActivity implements View.OnClickListener {
    private ProgressDialog progressDialog;
    private Button Login;
    private EditText emailid, password;
    private FirebaseAuth firebaseAuth;
    final public String isteacher="yes";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_teacher_login);
        firebaseAuth = FirebaseAuth.getInstance();
        if (firebaseAuth.getCurrentUser() != null) {
            //close this activity
            finish();
            //opening profile activity


            Intent i1 = new Intent(getApplicationContext(), NavDraweforStudent.class);
            i1.putExtra("isteacher", isteacher);
            startActivity(i1);
            overridePendingTransition(R.anim.slide_in, R.anim.slide_out);
        }

        emailid = (EditText) findViewById(R.id.edittext_temailaddress);

        password = (EditText) findViewById(R.id.edittext_tpassword);
        Login = (Button) findViewById(R.id.btn_tsignin);
        Login.setOnClickListener(this);
        progressDialog = new ProgressDialog(this);


    }

    @Override
    public void onClick(View v) {
        if (v == Login) {

            userLogin();

        }


    }





        private void userLogin() {
        String email = emailid.getText().toString().trim();
        String pwd = password.getText().toString().trim();


        //checking if email and passwords are empty
        if (TextUtils.isEmpty(email)) {
            Toast.makeText(this, "Empty email", Toast.LENGTH_SHORT).show();
            return;
        }

        if (TextUtils.isEmpty(pwd)) {
            Toast.makeText(this, "empty password", Toast.LENGTH_SHORT).show();
            return;
        }

        //if the email and password are not empty
        //displaying a progress dialog
        progressDialog.setMessage("Logging you in...");
        progressDialog.show();


        //logging in the user
        firebaseAuth.signInWithEmailAndPassword(email, pwd)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        //if the task is successfull
                        if (task.isSuccessful()) {
                            progressDialog.dismiss();
                            finish();

                            Intent i1 = new Intent(getApplicationContext(), NavDraweforStudent.class);
                            i1.putExtra("isteacher", isteacher);
                            startActivity(i1);
                        } else {

                            Toast.makeText(TeacherLogin.this, "Access Denied", Toast.LENGTH_SHORT).show();
                        }
                    }
                });

    }
}
