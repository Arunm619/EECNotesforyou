package com.arunsudharsan.eecnotesforyou;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

import static android.preference.PreferenceManager.getDefaultSharedPreferences;


public class Settingsfrag extends Fragment {
    String mydepartment, mysemester;

    public TextView homepathtv;
    public Button savehome, btnchangehome;
    CardView cvChangehome, cvChoosedept;
    private Spinner spinnerfordept = null, spinnerforsem = null;
    private static final String[] semesters = {"Sem 1", "Sem 2", "Sem 3", "Sem 4", "Sem 5", "Sem 6", "Sem 7", "Sem 8"};
    private static final String[] departments = {"Automobile", "Civil", "EEE", "ECE", "EIE", "CSE", "IT", "MECHANICAL"};

    //public String mydept,mysem;
    public Settingsfrag() {
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        View v = inflater.inflate(R.layout.fragment_settingsfrag, container, false);
        btnchangehome = (Button) v.findViewById(R.id.btn_changehome);
        mydepartment = PreferenceManager.getDefaultSharedPreferences(getContext()).getString("MYDEPARTMENT", "Choose");
        mysemester = PreferenceManager.getDefaultSharedPreferences(getContext()).getString("MYSEMESTER", "Home");

//        mydept = PreferenceManager.getDefaultSharedPreferences(getContext()).getString("MYDEPARTMENT", "1");
        //      mysem = PreferenceManager.getDefaultSharedPreferences(getContext()).getString("MYSEMESTER", "1");

        homepathtv = (TextView) v.findViewById(R.id.homepathtv);
        savehome = (Button) v.findViewById(R.id.savedeptandsempermanently);

        Spinner spinnerforsem = (Spinner) v.findViewById(R.id.spinnerforsem);


        Spinner spinnerfordept = (Spinner) v.findViewById(R.id.spinnerfordept);

        cvChoosedept = (CardView) v.findViewById(R.id.cvchoosedept);
        cvChangehome = (CardView) v.findViewById(R.id.cvchangehome);

        if ((mysemester == null) && (mydepartment == null)) {
            cvChoosedept.setVisibility(View.VISIBLE);
            cvChangehome.setVisibility(View.GONE);
        } else {

            cvChoosedept.setVisibility(View.GONE);
            homepathtv.setText("Home: " + mydepartment + " - " + mysemester);

            cvChangehome.setVisibility(View.VISIBLE);


        }
        btnchangehome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cvChoosedept.setVisibility(View.VISIBLE);
                cvChangehome.setVisibility(View.GONE);

            }
        });
        savehome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                getDefaultSharedPreferences(getContext()).edit().putString("MYDEPARTMENT", mydepartment).apply();

                getDefaultSharedPreferences(getContext()).edit().putString("MYSEMESTER", mysemester).apply();
                mydepartment = PreferenceManager.getDefaultSharedPreferences(getContext()).getString("MYDEPARTMENT", "null");
                mysemester = PreferenceManager.getDefaultSharedPreferences(getContext()).getString("MYSEMESTER", "null");

                cvChoosedept.setVisibility(View.GONE);

                homepathtv.setText("Home: " + mydepartment + " - " + mysemester);

                cvChangehome.setVisibility(View.VISIBLE);

                Snackbar.make(v, "Successfully Saved!", Snackbar.LENGTH_LONG)
                        .setAction("Undo", new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                cvChangehome.setVisibility(View.GONE);
                                cvChoosedept.setVisibility(View.VISIBLE);

                            }
                        }).show();


            }
        });
        spinnerfordept.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String item = parent.getItemAtPosition(position).toString();
                switch (position) {
                    default:
                        mydepartment = null;
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
        });
        // Creating adapter for spinner
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(getContext(), android.R.layout.simple_spinner_item, departments);

        // Drop down layout style - list view with radio button
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        // attaching data adapter to spinner
        spinnerfordept.setAdapter(dataAdapter);


        ArrayAdapter<String> adapterforsem = new ArrayAdapter<String>(getContext(),
                android.R.layout.simple_spinner_item, semesters);

        adapterforsem.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerforsem.setAdapter(adapterforsem);
        spinnerforsem.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                switch (position) {
                    default:
                        mysemester = null;
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


        return v;
    }

}
