package com.arunsudharsan.eecnotesforyou;

import android.content.Intent;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;


public class Department extends AppCompatActivity implements View.OnClickListener {
    private FirebaseAuth firebaseAuth;
    private CardView cvAuto, cvcivil, cveee, cvece, cveie, cvcse, cvit, cvmech;
    TextView usernameedit;

    public String isteacher;
    String value;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_department);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        firebaseAuth = FirebaseAuth.getInstance();


        isteacher=getIntent().getStringExtra("isteacher");


        FirebaseUser user = firebaseAuth.getCurrentUser();

        cvAuto = (CardView) findViewById(R.id.card_view_automobile);
        cvAuto.setOnClickListener(this);
        cvcivil = (CardView) findViewById(R.id.card_view_civil);
        cvcivil.setOnClickListener(this);
        cveee = (CardView) findViewById(R.id.card_view_EEE);
        cveee.setOnClickListener(this);
        cvece = (CardView) findViewById(R.id.card_view_ECE);
        cvece.setOnClickListener(this);

        cveie = (CardView) findViewById(R.id.card_view_EandI);
        cveie.setOnClickListener(this);
        cvcse = (CardView) findViewById(R.id.card_view_Cse);
        cvcse.setOnClickListener(this);
        cvit = (CardView) findViewById(R.id.card_view_it);
        cvit.setOnClickListener(this);
        cvmech = (CardView) findViewById(R.id.card_view_mechanical);
        cvmech.setOnClickListener(this);


        View parentLayout = findViewById(R.id.activity_department);
        Snackbar.make(parentLayout, "Welcome", Snackbar.LENGTH_LONG).show();
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();

                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onClick(View view) {


        if (view == cvAuto) {
            value = "AUTOMOBILE";


            Intent intent = new Intent(getBaseContext(), SemesterList.class);
            intent.putExtra("EXTRA_SESSION_ID", value);
            intent.putExtra("isteacher", isteacher);

            startActivity(intent);
        }
        if (view == cvcivil) {

            value = "CIVIL";
            Intent intent = new Intent(getBaseContext(), SemesterList.class);
            intent.putExtra("EXTRA_SESSION_ID", value);
            intent.putExtra("isteacher", isteacher);

            startActivity(intent);
        }
        if (view == cveee) {

            value = "EEE";
            Intent intent = new Intent(getBaseContext(), SemesterList.class);
            intent.putExtra("EXTRA_SESSION_ID", value);
            intent.putExtra("isteacher", isteacher);

            startActivity(intent);
        }

        if (view == cvece) {

            value = "ECE";
            Intent intent = new Intent(getBaseContext(), SemesterList.class);
            intent.putExtra("isteacher", isteacher);

            intent.putExtra("EXTRA_SESSION_ID", value);
            startActivity(intent);
        }

        if (view == cveie) {

            value = "EIE";
            Intent intent = new Intent(getBaseContext(), SemesterList.class);
            intent.putExtra("isteacher", isteacher);

            intent.putExtra("EXTRA_SESSION_ID", value);
            startActivity(intent);
        }
        if (view == cvcse) {

            value = "CSE";
            Intent intent = new Intent(getBaseContext(), SemesterList.class);
            intent.putExtra("EXTRA_SESSION_ID", value);
            intent.putExtra("isteacher", isteacher);

            startActivity(intent);
        }
        if (view == cvit) {

            value = "IT";
            Intent intent = new Intent(getBaseContext(), SemesterList.class);
            intent.putExtra("EXTRA_SESSION_ID", value);
            intent.putExtra("isteacher", isteacher);

            startActivity(intent);
        }
        if (view == cvmech) {

            value = "MECHANICAL";
            Intent intent = new Intent(getBaseContext(), SemesterList.class);
            intent.putExtra("EXTRA_SESSION_ID", value);
            intent.putExtra("isteacher", isteacher);

            startActivity(intent);
        }


    }

}
