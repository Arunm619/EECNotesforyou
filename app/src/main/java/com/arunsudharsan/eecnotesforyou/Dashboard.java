package com.arunsudharsan.eecnotesforyou;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import static android.preference.PreferenceManager.getDefaultSharedPreferences;

public class Dashboard extends AppCompatActivity implements OnItemSelectedListener {
    private String sympname, symcontent, sympwlink;
    DatabaseReference symposiumref;
    Button btn_auhome, btn_auresults, btn_eecweb, btn_mydownloads, btn_dptbtn, moredetails, dismissbtn, savedetails, changehome;
    String mydepartment, mysemester;
    TextView tvSympname, tvsympcontents, yourhometv;
    CardView cv_offlinemsg, cv_choosedept, cv_undoselection;
    private Spinner spinnerfordept, spinnerforsem;
    private static final String[] semesters = {"Sem 1", "Sem 2", "Sem 3", "Sem 4", "Sem 5", "Sem 6", "Sem 7", "Sem 8"};
    private static final String[] departments = {"Automobile", "Civil", "EEE", "ECE", "EIE", "CSE", "IT", "MECHANICAL"};

  public String  hasbeendiscarded="no";
    private static final int PERMS_REQUEST_CODE = 123;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        yourhometv = (TextView) findViewById(R.id.yourhomeistv);
        tvSympname = (TextView) findViewById(R.id.tvsymposiumnameforcard);
        tvsympcontents = (TextView) findViewById(R.id.tvsymposiumcontentsforcard);
        savedetails = (Button) findViewById(R.id.savedsodismisscvofchoosinghome);
        changehome = (Button) findViewById(R.id.changehome);

        //webview btns
        btn_auhome = (Button) findViewById(R.id.btn_auhome);
        btn_auresults = (Button) findViewById(R.id.btn_auresults);
        btn_eecweb = (Button) findViewById(R.id.btn_EEC);

//symposium
        moredetails = (Button) findViewById(R.id.moredetails);
        cv_undoselection = (CardView) findViewById(R.id.cardviewundoseletion);
        cv_choosedept = (CardView) findViewById(R.id.cardviewchoosedept);
        cv_offlinemsg = (CardView) findViewById(R.id.cardviewoffline);
        cv_offlinemsg.setVisibility(View.VISIBLE);
//news
        dismissbtn = (Button) findViewById(R.id.dismisscardviewoffline);
        btn_dptbtn = (Button) findViewById(R.id.btn_dpt);
        btn_mydownloads = (Button) findViewById(R.id.btn_mydownloads);
        //home path


        isOnline();

        //download and home
        // buttons links

        symposiumref = FirebaseDatabase.getInstance().getReferenceFromUrl("https://notes-for-you-6b222.firebaseio.com/Other");

        symposiumref.child("Symposium_name").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                sympname = dataSnapshot.getValue(String.class);

                tvSympname.setText(sympname);

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
        symposiumref.child("Symposium_link").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                sympwlink = dataSnapshot.getValue(String.class);


            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        symposiumref.child("Symposium_content").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                symcontent = dataSnapshot.getValue(String.class);

                tvsympcontents.setText(symcontent);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });


        //depts

        spinnerfordept = (Spinner) findViewById(R.id.spinnerfordept);

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(Dashboard.this,
                android.R.layout.simple_spinner_item, departments);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerfordept.setAdapter(adapter);
        spinnerfordept.setOnItemSelectedListener(this);


        changehome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cv_undoselection.setVisibility(View.GONE);
                cv_choosedept.setVisibility(View.VISIBLE);

            }
        });
        //semesters
        spinnerforsem = (Spinner) findViewById(R.id.spinnerforsem);
        ArrayAdapter<String> adapterforsem = new ArrayAdapter<String>(Dashboard.this,
                android.R.layout.simple_spinner_item, semesters);

        adapterforsem.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerforsem.setAdapter(adapterforsem);
        spinnerforsem.setOnItemSelectedListener(new OnItemSelectedListener() {
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


        btn_dptbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), Department.class));

            }
        });
        btn_mydownloads.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!hasPermissions()) {
                    Snackbar.make(v, "Permission required...", Snackbar.LENGTH_LONG)
                            .setAction("Give permission", new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    requestPerms();
                                }
                            })
                            .show();

                } else
                    startActivity(new Intent(getApplicationContext(), Downloads.class));

            }
        });


        mysemester = null;
        mydepartment = null;
        mydepartment = PreferenceManager.getDefaultSharedPreferences(getApplicationContext()).getString("MYDEPARTMENT", "null");
        mysemester = PreferenceManager.getDefaultSharedPreferences(getBaseContext()).getString("MYSEMESTER", "null");
        if (!mydepartment.equals("Choose") && !(mysemester.equals("Home"))) {
            yourhometv.setText("Home: " + mydepartment + " - " + mysemester);


            cv_choosedept.setVisibility(View.GONE);
            cv_undoselection.setVisibility(View.VISIBLE);


        }
        btn_auhome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getBaseContext(), WebviewActivity.class);
                intent.putExtra("website", "AnnaUniv - HomePage");

                startActivity(intent);

            }
        });
        btn_auresults.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(getBaseContext(), WebviewActivity.class);
                intent.putExtra("website", "AnnaUniv - Results");

                startActivity(intent);

            }
        });
        btn_eecweb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getBaseContext(), WebviewActivity.class);
                intent.putExtra("website", "EEC - HomePage");

                startActivity(intent);

            }
        });


//symposium link
        moredetails.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (sympwlink != null) {

                    Intent intent = new Intent(getBaseContext(), WebviewActivity.class);
                    intent.putExtra("website", sympwlink);

                    startActivity(intent);

                 //   Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(sympwlink));
                   // startActivity(browserIntent);

                } else
                {
                    Snackbar.make(v, "Please  wait...  ", Snackbar.LENGTH_LONG)
                            .setAction("Action", null).show();

                }
    /*     Snackbar.make(v, "Opening " + sympwlink + "  In chrome...", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();*/

            }
        });



        hasbeendiscarded = PreferenceManager.getDefaultSharedPreferences(getApplicationContext()).getString("discard", "defaultStringIfNothingFound");

        if(hasbeendiscarded.equals("no"))
        {
            cv_offlinemsg.setVisibility(View.VISIBLE);

        }
        //dismiss offline cv
        dismissbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getDefaultSharedPreferences(getApplicationContext()).edit().putString("discard", "yes").commit();

                cv_offlinemsg.setVisibility(View.GONE);

                Snackbar.make(v, "Card dismissed!", Snackbar.LENGTH_LONG)
                        .setAction("Undo", new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                cv_offlinemsg.setVisibility(View.VISIBLE);
                            }
                        }).show();

            }
        });

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setImageResource(R.drawable.ic_whitehome);
        fab.setOnClickListener(new View.OnClickListener() {
                                   @Override
                                   public void onClick(View view) {


                                       if (mydepartment.equals("Choose") && (mysemester.equals("Home"))) {

                                           Snackbar.make(view, "Select Home Path first...", Snackbar.LENGTH_LONG)
                                                   .setAction("Action", null).show();


                                       } else {

                                           mydepartment = PreferenceManager.getDefaultSharedPreferences(getApplicationContext()).getString("MYDEPARTMENT", "defaultStringIfNothingFound");
                                           mysemester = PreferenceManager.getDefaultSharedPreferences(getBaseContext()).getString("MYSEMESTER", "defaultStringIfNothingFound");


                                           Intent intent = new Intent(getBaseContext(), SubjectsList.class);
                                           intent.putExtra("semesterno", mysemester);
                                           intent.putExtra("deptname", mydepartment);
                                           intent.putExtra("isteacher", "no");

                                           startActivity(intent);

                                       }

                                   }
                               }
        );


        savedetails.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                getDefaultSharedPreferences(getApplicationContext()).edit().putString("MYDEPARTMENT", mydepartment).commit();

                getDefaultSharedPreferences(getApplicationContext()).edit().putString("MYSEMESTER", mysemester).commit();
                mydepartment = PreferenceManager.getDefaultSharedPreferences(getApplicationContext()).getString("MYDEPARTMENT", "Choose");
                mysemester = PreferenceManager.getDefaultSharedPreferences(getBaseContext()).getString("MYSEMESTER", "Home");
                cv_choosedept.setVisibility(View.GONE);
                yourhometv.setText("Your Home: " + mydepartment + " Sem: " + mysemester);

                cv_undoselection.setVisibility(View.VISIBLE);

                Snackbar.make(v, "Successfully Saved!", Snackbar.LENGTH_LONG)
                        .setAction("Undo", new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                cv_undoselection.setVisibility(View.GONE);
                                cv_choosedept.setVisibility(View.VISIBLE);

                            }
                        }).show();


            }
        });
    }


    private void isOnline() {
        ConnectivityManager conMgr = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo netInfo = conMgr.getActiveNetworkInfo();
        if (netInfo == null) {
            new AlertDialog.Builder(Dashboard.this)
                    .setTitle(getResources().getString(R.string.app_name))
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

                            startActivity(new Intent(Dashboard.this, Downloads.class));

                        }
                    }).show();


        }
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        //{"AUTOMOBILE", "CIVIL", "EEE", "ECE", "EIE", "CSE", "IT", "MECHANICAL"};
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

    private void needperms() {
        boolean i = hasPermissions();
        if (i) {

        } else {
            requestPerms();
            hasPermissions();
        }

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


    private boolean hasPermissions() {
        int res = 0;
        String[] permissions = new String[]{android.Manifest.permission.WRITE_EXTERNAL_STORAGE};

        for (String perms : permissions) {
            res = checkCallingOrSelfPermission(perms);
            if (!(res == PackageManager.PERMISSION_GRANTED)) {
                return false;
            }
        }
        return true;
    }

    private void requestPerms() {
        String[] permissions = new String[]{android.Manifest.permission.WRITE_EXTERNAL_STORAGE};
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            requestPermissions(permissions, PERMS_REQUEST_CODE);
        }
    }

}
