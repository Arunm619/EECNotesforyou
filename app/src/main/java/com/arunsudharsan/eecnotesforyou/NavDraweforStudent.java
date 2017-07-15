package com.arunsudharsan.eecnotesforyou;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import outlander.showcaseview.ShowcaseViewBuilder;


public class
NavDraweforStudent extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    public ShowcaseViewBuilder showcaseViewBuilder;
    public String mysem, mydept, isteacher;
    private static final String[] departments = {"Automobile", "Civil", "EEE", "ECE", "EIE", "CSE", "IT", "MECHANICAL"};
    private static final int FRAGMENT_A = 1, FRAGMENT_B = 2, FRAGMENT_C = 3;
    private PrefManager prefManager;

    public FloatingActionButton fab;

    //a-home b-settings c-logout
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nav_drawefor_student);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        isteacher = getIntent().getStringExtra("isteacher");
        fab = (FloatingActionButton) findViewById(R.id.fab);

        isOnline();
        prefManager = new PrefManager(this);




        try {
            fab.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    mydept = PreferenceManager.getDefaultSharedPreferences(getApplicationContext()).getString("MYDEPARTMENT", "1");
                    Log.e("my dept", mydept);
                    mysem = PreferenceManager.getDefaultSharedPreferences(getApplicationContext()).getString("MYSEMESTER", "1");
                    Log.e("My sem", mysem);
                    if (isteacher.equals("yes")) {


                        if (!mydept.equals("1") && !mysem.equals("1")) {
                            Intent intent = new Intent(getBaseContext(), SubjectsList.class);
                            intent.putExtra("semesterno", mysem);
                            intent.putExtra("deptname", mydept);
                            intent.putExtra("isteacher", isteacher);

                            //   Toast.makeText(NavDraweforStudent.this, mysem + mydept, Toast.LENGTH_SHORT).show();
                            startActivity(intent);


                        } else {
                            Snackbar.make(view, "Please select your dept and sem", Snackbar.LENGTH_INDEFINITE)/*.setAction("Select", new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                gotosettings();
                            }
                        })
                         */
                                    .show();


                            new AlertDialog.Builder(NavDraweforStudent.this)
                                    .setTitle("Please Select")
                                    .setMessage("Department and Semester").setCancelable(false)
                                    .setPositiveButton(R.string.select, new DialogInterface.OnClickListener() {
                                        public void onClick(DialogInterface dialog, int which) {
                                            gotosettings();
                                        }
                                    })

                                    .show();

                        }
                    } else if (isteacher.equals("no")) {


                        if (!mydept.equals("1") && !mysem.equals("1")) {

                            Intent intent = new Intent(getBaseContext(), SubjectsList.class);
                            intent.putExtra("semesterno", mysem);
                            intent.putExtra("deptname", mydept);
                            intent.putExtra("isteacher", isteacher);
//                         Toast.makeText(NavDraweforStudent.this, mysem + mydept, Toast.LENGTH_SHORT).show();

                            startActivity(intent);

                        } else {
                            Snackbar.make(view, "Please select your dept and sem", Snackbar.LENGTH_INDEFINITE)/*.setAction("Select", new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                gotosettings();

                            }
                        })
                        */.show();


                            new AlertDialog.Builder(NavDraweforStudent.this)
                                    .setTitle("Please Select")
                                    .setMessage("Department and Semester")
                                    .setPositiveButton(R.string.select, new DialogInterface.OnClickListener() {
                                        public void onClick(DialogInterface dialog, int which) {
                                            gotosettings();

                                        }
                                    })

                                    .show();

                        }

                    } else {
                        Toast.makeText(NavDraweforStudent.this, mysem + mydept, Toast.LENGTH_SHORT).show();

                    }


                }
            });
        } catch (Exception e) {
            Toast.makeText(this, "hey caught :)" +
                    "" + e.getMessage(), Toast.LENGTH_SHORT).show();
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);


    }


    private void isOnline() {
        ConnectivityManager conMgr = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo netInfo = conMgr.getActiveNetworkInfo();
        if (netInfo == null) {
            new AlertDialog.Builder(NavDraweforStudent.this)
                    .setTitle(getResources().getString(R.string.app_name)).setCancelable(false)
                    .setMessage(getResources().getString(R.string.internet_error))
                    .setNeutralButton("Reload", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {

                            isOnline();
                        }

                    })
                    .setPositiveButton("Downloads", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {

                            startActivity(new Intent(NavDraweforStudent.this, Downloads.class));

                        }
                    }).show();


        }
    }

    private void gotosettings() {
        setTitle("Settings");
        Settingsfrag fragment = new Settingsfrag();
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.framelayout, fragment, "Settings").commit();
        Toast toast = Toast.makeText(NavDraweforStudent.this, "Press change home button and select your preferences.", Toast.LENGTH_LONG);
        toast.setGravity(Gravity.CENTER, 0, 0);
        toast.show();

    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle item selection

        switch (item.getItemId()) {
            case R.id.searchview:


            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_feedback) {
            Intent i = new Intent(Intent.ACTION_SEND);
            i.setData(Uri.parse("email"));
            String[] s = {"arun.m.sudharsan@gmail.com"};
            i.putExtra(Intent.EXTRA_EMAIL, s);
            i.putExtra(Intent.EXTRA_SUBJECT, R.string.app_name + "- Feedback / Bug Report");
            i.putExtra(Intent.EXTRA_TEXT, "Enter your description here... :)");
            i.setType("message/rfc822");
            Intent chooser = Intent.createChooser(i, "Launch Email");
            startActivity(chooser);


        }
        if (id == R.id.nav_home) {
            setTitle("Home");
            Homefrags fragment = new Homefrags();
            FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
            fragmentTransaction.replace(R.id.framelayout, fragment, "Home").commit();

        } else if (id == R.id.nav_settings) {
            setTitle("Settings");
            Settingsfrag fragment = new Settingsfrag();
            FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
            fragmentTransaction.replace(R.id.framelayout, fragment, "Settings").commit();

        } else if (id == R.id.nav_logout) {

            setTitle("My Account");
            Accountfrag fragment = new Accountfrag();
            FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
            fragmentTransaction.replace(R.id.framelayout, fragment, "My Account").commit();

        }
        else if (id == R.id.navEvents) {

            setTitle("Events");
            upcomingeventsfrag upcomingeventsfrag = new upcomingeventsfrag();
            FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
            fragmentTransaction.replace(R.id.framelayout, upcomingeventsfrag, "Events").commit();

        }
        else if (id == R.id.nav_share) {

            Intent share = new Intent(Intent.ACTION_SEND);
            share.setType("text/plain");
            share.putExtra(Intent.EXTRA_TEXT, "The Engineering Notes App. The only app you will need throughout your Engineering life." +
                    "");
            startActivity(Intent.createChooser(share, "Share using"));
        }
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

}
