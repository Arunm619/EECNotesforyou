package com.arunsudharsan.eecnotesforyou;

import android.content.Intent;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class TeacherchooseSD extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    private static final String[] semesters = {"Sem 1", "Sem 2", "Sem 3", "Sem 4", "Sem 5", "Sem 6", "Sem 7", "Sem 8"};
    private static final String[] departments = {"Automobile", "Civil", "EEE", "ECE", "EIE", "CSE", "IT", "MECHANICAL"};
Button goselectSD;
    String mysemester,mydepartment;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_teacherchoose_sd);
goselectSD=(Button)findViewById(R.id.btn_selectSD);
        goselectSD.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getBaseContext(), SubjectsList.class);
                intent.putExtra("semesterno", mysemester);
                intent.putExtra("deptname", mydepartment);
                intent.putExtra("isteacher","yes");
                startActivity(intent);

                //Toast.makeText(TeacherchooseSD.this, mydepartment+mysemester, Toast.LENGTH_SHORT).show();
            }
        });
        Spinner spinnerfortdept = (Spinner) findViewById(R.id.spinnerfortdpet);
        spinnerfortdept.setOnItemSelectedListener(this);



        Spinner spinnerfortsem = (Spinner) findViewById(R.id.spinnerfortsem);
        ArrayAdapter<String> adapterforsem = new ArrayAdapter<String>(TeacherchooseSD.this,
                android.R.layout.simple_spinner_item, semesters);

        adapterforsem.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerfortsem.setAdapter(adapterforsem);
        spinnerfortsem.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                switch (position) {
                    default:
                        mysemester=null;
                        break;
                    case 0:


                        mysemester = "SEMESTER 1";

                        break;
                    case 1:
                        mysemester = "SEMESTER 2";
                        Snackbar.make(view, "Semester 2", Snackbar.LENGTH_LONG)
                                .setAction("Action", null).show();
                        break;
                    case 2:
                        mysemester = "SEMESTER 3";
                        Snackbar.make(view, "Semester 3", Snackbar.LENGTH_LONG)
                                .setAction("Action", null).show();
                        break;

                    case 3:
                        mysemester = "SEMESTER 4";
                        Snackbar.make(view, "Semester 4", Snackbar.LENGTH_LONG)
                                .setAction("Action", null).show();
                        break;
                    case 4:
                        mysemester = "SEMESTER 5";
                        Snackbar.make(view, "Semester 5", Snackbar.LENGTH_LONG)
                                .setAction("Action", null).show();
                        break;
                    case 5:
                        mysemester = "SEMESTER 6";
                        Snackbar.make(view, "Semester 6", Snackbar.LENGTH_LONG)
                                .setAction("Action", null).show();
                        break;
                    case 6:
                        mysemester = "SEMESTER 7";
                        Snackbar.make(view, "Semester 7", Snackbar.LENGTH_LONG)
                                .setAction("Action", null).show();
                        break;
                    case 7:
                        mysemester = "SEMESTER 8";
                        Snackbar.make(view, "Semester 8", Snackbar.LENGTH_LONG)
                                .setAction("Action", null).show();
                        break;


                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


        // Creating adapter for spinner
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item,departments);

        // Drop down layout style - list view with radio button
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        // attaching data adapter to spinner
        spinnerfortdept.setAdapter(dataAdapter);

    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        String item = parent.getItemAtPosition(position).toString();
        switch (position) {
            default:
                mydepartment=null;
                break;
            case 0:
                mydepartment = "AUTOMOBILE";
                break;
            case 1:
                mydepartment = "CIVIL";
                Snackbar.make(view, "civil", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
                break;
            case 2:
                mydepartment = "EEE";
                Snackbar.make(view, "eee", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
                break;

            case 3:
                mydepartment = "ECE";
                Snackbar.make(view, "ece", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
                break;
            case 4:
                mydepartment = "EIE";
                Snackbar.make(view, "eie", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
                break;
            case 5:
                mydepartment = "CSE";
                Snackbar.make(view, "cse", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
                break;
            case 6:
                mydepartment = "IT";
                Snackbar.make(view, "it", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
                break;
            case 7:
                mydepartment = "MECHANICAL";
                Snackbar.make(view, "mech", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
                break;

        }

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}
