package com.arunsudharsan.eecnotesforyou;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;

public class AreyouteacherorStudent extends AppCompatActivity {

    //CheckBox sbox, tbox;
    RadioButton sbox, tbox;
    CardView cvStudent, cvTeacher;
    Button btn_Letsgo;
//    SharedPreferences pref = getSharedPreferences("MyPref", Context.MODE_PRIVATE);
    //  SharedPreferences.Editor editor = pref.edit();


    public int a;
//0 for teacher 1 for student

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_areyouteacheror_student);
        sbox = (RadioButton) findViewById(R.id.cb_Student);
        tbox = (RadioButton) findViewById(R.id.cb_Teacher);
        btn_Letsgo = (Button) findViewById(R.id.btn_proceed);
        cvStudent = (CardView) findViewById(R.id.card_view_student);
        cvTeacher = (CardView) findViewById(R.id.card_view_teacher);
        isOnline();


        cvStudent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                sbox.post(new Runnable() {
                    @Override
                    public void run() {
                        if (!sbox.isChecked()) {
                            sbox.setChecked(true);
                            tbox.setChecked(false);
                        }

                    }
                });
            }
        });

        cvTeacher.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tbox.post(new Runnable() {
                    @Override
                    public void run() {
                        if (!tbox.isChecked()) {
                            tbox.setChecked(true);
                            sbox.setChecked(false);


                        }
                    }
                });

            }
        });

        btn_Letsgo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (!tbox.isChecked() && !sbox.isChecked()) {
                    Snackbar snackbar = Snackbar
                            .make(v, "Select any one", Snackbar.LENGTH_LONG);

                    snackbar.show();
                    //    Toast.makeText(AreyouteacherorStudent.this, "Select Any one", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (tbox.isChecked() && sbox.isChecked()) {
                    Snackbar snackbar = Snackbar
                            .make(v, "Select any one", Snackbar.LENGTH_LONG);

                    snackbar.show();

//                    Toast.makeText(AreyouteacherorStudent.this, "Select Any one", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (tbox.isChecked()) {


                    a = 0;


                    gotoTeacherlogin();
                }

                if (sbox.isChecked()) {

                    a = 1;
                    gotoStudlogin();
                }


            }
        });


    }

    private void isOnline() {
        ConnectivityManager conMgr = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo netInfo = conMgr.getActiveNetworkInfo();
        if (netInfo == null) {
            new AlertDialog.Builder(AreyouteacherorStudent.this)
                    .setTitle(getResources().getString(R.string.app_name))
                    .setMessage(getResources().getString(R.string.internet_error)).setCancelable(false)
                    .setNeutralButton("Reload", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {

                            isOnline();
                        }

                    })
                    .show();


        }
    }

   /* private void checkprofile() {
        if (a == 0) {
            finish();
            startActivity(new Intent(getApplicationContext(), TeacherLogin.class));
        } else if (a == 1) {
            finish();
            startActivity(new Intent(getApplicationContext(), LogInactivity.class));

        } else {

            return;
        }
    }
*/

    void gotoTeacherlogin() {
        finish();
        startActivity(new Intent(getApplicationContext(), TeacherLogin.class));
        //overridePendingTransition(R.anim.slide_in, R.anim.slide_out);

    }

    void gotoStudlogin() {
        finish();
        startActivity(new Intent(getApplicationContext(), LogInactivity.class));
//        overridePendingTransition(R.anim.slide_in, R.anim.slide_out);
    }

}
