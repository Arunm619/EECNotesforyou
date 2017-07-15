package com.arunsudharsan.eecnotesforyou;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TextInputEditText;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class LogInactivity extends AppCompatActivity implements View.OnClickListener {
    private ProgressDialog progressDialog;
    private Button Login;
    private EditText emailid, password;
    private TextView gotoSignup;
    public String isteacher = "no";
    private FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_inactivity);
        firebaseAuth = FirebaseAuth.getInstance();

        //if the objects getcurrentuser method is not null
        //means user is already logged in
        if (firebaseAuth.getCurrentUser() != null) {
            //close this activity
            //opening profile activity
            Intent i1 = new Intent(getApplicationContext(), NavDraweforStudent.class);
            i1.putExtra("isteacher", isteacher);
            finish();

            startActivity(i1);
            overridePendingTransition(R.anim.slide_in, R.anim.slide_out);
        }


        emailid = (EditText) findViewById(R.id.edittext_emailaddress);
        password = (EditText) findViewById(R.id.edittext_password);
        Login = (Button) findViewById(R.id.btn_signin);
        gotoSignup = (TextView) findViewById(R.id.gotosignup);
        Login.setOnClickListener(this);
        gotoSignup.setOnClickListener(this);
        progressDialog = new ProgressDialog(this);

    }


    @Override
    public void onClick(View view) {
        if (view == Login) {

            userLogin();

        }
        if (view == gotoSignup) {
            finish();

            Intent i1 = new Intent(getApplicationContext(), SignUpActivity.class);

            startActivity(i1);

        }
    }

    private void userLogin() {
        String email = emailid.getText().toString().trim();
        String pwd = password.getText().toString().trim();


        //checking if email and passwords are empty
        if (TextUtils.isEmpty(email)) {

            View parentLayout = findViewById(R.id.activity_log_inactivity);
            Snackbar.make(parentLayout, "Email ID Required", Snackbar.LENGTH_LONG).show();
            return;
        }

        if (TextUtils.isEmpty(pwd)) {

            View parentLayout = findViewById(R.id.activity_log_inactivity);
            Snackbar.make(parentLayout, "Password Required", Snackbar.LENGTH_LONG).show();
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

                            View parentLayout = findViewById(R.id.activity_log_inactivity);
                            Snackbar.make(parentLayout, "Email-ID or Password is Incorrect", Snackbar.LENGTH_LONG).show();


                        }
                    }
                });

    }
}
