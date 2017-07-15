package com.arunsudharsan.eecnotesforyou;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class SubjectsList extends AppCompatActivity {
    DatabaseReference dbref_cse;//dbref_auto, dbref_civil, dbref_eee, dbref_ece, dbref_mech, dbref_eie, dbref_it;

    ProgressDialog mProgress;
    ListView listView;
    ArrayList<String> subArrayList = new ArrayList<>();
    ArrayList<String> subcodeArrayList = new ArrayList<>();
    ArrayAdapter<String> arrayAdapter;
    String departmentname, semesterno, isteacher;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_subjects_list);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        semesterno = getIntent().getStringExtra("semesterno");
        departmentname = getIntent().getStringExtra("deptname");

        isteacher=getIntent().getStringExtra("isteacher");
        mProgress = new ProgressDialog(this);


        mProgress.setMessage("Loading Subjects...");
        mProgress.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        mProgress.setIndeterminate(true);
        mProgress.setProgress(0);
        mProgress.setCancelable(false);
        mProgress.show();
        arrayAdapter = new ArrayAdapter<String>(this, R.layout.mytextviewlist, subArrayList);
        listView = (ListView) findViewById(R.id.Listviewforsubjects);
        listView.setAdapter(arrayAdapter);
//COMPUTER SCIENCE DEPARTMENT
        if (departmentname.equals("CSE")) {

            switch (semesterno) {
                case "SEMESTER 1":
                    dbref_cse = FirebaseDatabase.getInstance().getReferenceFromUrl("https://notes-for-you-6b222.firebaseio.com/dept_CSE/Sem1");
                    break;
                case "SEMESTER 2":
                    dbref_cse = FirebaseDatabase.getInstance().getReferenceFromUrl("https://notes-for-you-6b222.firebaseio.com/dept_CSE/Sem2");

                    break;
                case "SEMESTER 3":
                    dbref_cse = FirebaseDatabase.getInstance().getReferenceFromUrl("https://notes-for-you-6b222.firebaseio.com/dept_CSE/Sem3");

                    break;
                case "SEMESTER 4":
                    dbref_cse = FirebaseDatabase.getInstance().getReferenceFromUrl("https://notes-for-you-6b222.firebaseio.com/dept_CSE/Sem4");

                    break;
                case "SEMESTER 5":
                    dbref_cse = FirebaseDatabase.getInstance().getReferenceFromUrl("https://notes-for-you-6b222.firebaseio.com/dept_CSE/Sem5");

                    break;
                case "SEMESTER 6":
                    dbref_cse = FirebaseDatabase.getInstance().getReferenceFromUrl("https://notes-for-you-6b222.firebaseio.com/dept_CSE/Sem6");

                    break;
                case "SEMESTER 7":
                    dbref_cse = FirebaseDatabase.getInstance().getReferenceFromUrl("https://notes-for-you-6b222.firebaseio.com/dept_CSE/Sem7");

                    break;
                case "SEMESTER 8":
                    dbref_cse = FirebaseDatabase.getInstance().getReferenceFromUrl("https://notes-for-you-6b222.firebaseio.com/dept_CSE/Sem8");

                    break;

            }


        }


        dbref_cse.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                for (DataSnapshot dsp : dataSnapshot.getChildren()) {
                    Modelnotes modelnotes = dsp.getValue(Modelnotes.class);
                    subArrayList.add(String.valueOf(modelnotes.getSubname()));
                    subcodeArrayList.add(String.valueOf(modelnotes.getSubcode()));
                    arrayAdapter.notifyDataSetChanged();
                    mProgress.dismiss();


                }


            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                //View parentLayout = findViewById(R.id.activity_subjects_list);
                //Snackbar.make(parentLayout, ""+subArrayList.get(i), Snackbar.LENGTH_LONG).show();

                Toast.makeText(SubjectsList.this,subArrayList.get(i), Toast.LENGTH_LONG).show();
                Intent intent = new Intent(getBaseContext(), SubjectViewActivity.class);
                intent.putExtra("semesterno", semesterno);
                intent.putExtra("deptname", departmentname);
                intent.putExtra("Subjectname", subArrayList.get(i));
                intent.putExtra("Subjectcode", subcodeArrayList.get(i));

                intent.putExtra("isteacher",isteacher);
                startActivity(intent);


            }
        });


        View parentLayout = findViewById(R.id.activity_subjects_list);
        Snackbar.make(parentLayout, "You are from " + departmentname + "\t" + " in " + semesterno, Snackbar.LENGTH_LONG).show();


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
}
