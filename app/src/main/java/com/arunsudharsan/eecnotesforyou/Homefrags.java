package com.arunsudharsan.eecnotesforyou;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import static android.support.v4.content.PermissionChecker.checkCallingOrSelfPermission;


public class Homefrags extends Fragment {
    private String sympname, symcontent, sympwlink;
    public String isteacher;
    DatabaseReference symposiumref;
    public TextView tvsympname, tvsympcontents;
   // FloatingActionButton fab;
//public String mysem,mydept;
   public Button register;
    private static final int PERMS_REQUEST_CODE = 123;
    public Button btndpt, btndownload, eec, auhome, aures;
    public String value;
    CardView cvauto, cvcivil, cveee, cvece, cvmechanical, cveie, cvcse, cvit;


    public Homefrags() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_homefrags, container, false);

        //cv for depts
        cvauto = (CardView) v.findViewById(R.id.cvauto);
        cvcivil = (CardView) v.findViewById(R.id.cvcivil);
        cveee = (CardView) v.findViewById(R.id.cveee);
        cvece = (CardView) v.findViewById(R.id.cvece);

        cvmechanical = (CardView) v.findViewById(R.id.cvmechanical);
        cveie = (CardView) v.findViewById(R.id.cveie);
        cvcse = (CardView) v.findViewById(R.id.cvcse);
        cvit = (CardView) v.findViewById(R.id.cvit);

        btndownload = (Button) v.findViewById(R.id.btn_mydownloads);
        btndpt = (Button) v.findViewById(R.id.btn_dpt);
        eec = (Button) v.findViewById(R.id.btn_EEC);
        auhome = (Button) v.findViewById(R.id.btn_auhome);
        aures = (Button) v.findViewById(R.id.btn_auresults);

        isteacher = getActivity().getIntent().getStringExtra("isteacher");

/*

*/
        cvauto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                String value = "AUTOMOBILE";


                Intent intent = new Intent(getContext(), SemesterList.class);
                intent.putExtra("EXTRA_SESSION_ID", value);
                intent.putExtra("isteacher", isteacher);
                startActivity(intent);

                // Toast.makeText(getActivity(), "ato", Toast.LENGTH_SHORT).show();
            }
        });

        cvcivil.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //Toast.makeText(getActivity(), "civil", Toast.LENGTH_SHORT).show();
                value = "CIVIL";
                Intent intent = new Intent(getContext(), SemesterList.class);
                intent.putExtra("EXTRA_SESSION_ID", value);
                intent.putExtra("isteacher", isteacher);
                startActivity(intent);

            }
        });

        cveee.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

//                Toast.makeText(getActivity(), "eee", Toast.LENGTH_SHORT).show();

                value = "EEE";

                Intent intent = new Intent(getContext(), SemesterList.class);
                intent.putExtra("EXTRA_SESSION_ID", value);
                intent.putExtra("isteacher", isteacher);
                startActivity(intent);

            }
        });

        cvece.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


//                Toast.makeText(getActivity(), "ece", Toast.LENGTH_SHORT).show();


                value = "ECE";
                Intent intent = new Intent(getContext(), SemesterList.class);
                intent.putExtra("EXTRA_SESSION_ID", value);
                intent.putExtra("isteacher", isteacher);
                startActivity(intent);

            }
        });


        cvmechanical.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


//                Toast.makeText(getActivity(), "mec", Toast.LENGTH_SHORT).show();

                value = "MECHANICAL";
                Intent intent = new Intent(getContext(), SemesterList.class);
                intent.putExtra("EXTRA_SESSION_ID", value);
                intent.putExtra("isteacher", isteacher);
                startActivity(intent);

            }
        });

        cveie.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

//                Toast.makeText(getActivity(), "eie", Toast.LENGTH_SHORT).show();


                value = "EIE";
                Intent intent = new Intent(getContext(), SemesterList.class);
                intent.putExtra("EXTRA_SESSION_ID", value);
                intent.putExtra("isteacher", isteacher);
                startActivity(intent);

            }
        });

        cvcse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

//                Toast.makeText(getActivity(), "cse", Toast.LENGTH_SHORT).show();

                value = "CSE";
                Intent intent = new Intent(getContext(), SemesterList.class);
                intent.putExtra("EXTRA_SESSION_ID", value);
                intent.putExtra("isteacher", isteacher);
                startActivity(intent);

            }
        });

        cvit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

//                Toast.makeText(getActivity(), "IT", Toast.LENGTH_SHORT).show();
                value = "IT";
                Intent intent = new Intent(getContext(), SemesterList.class);
                intent.putExtra("EXTRA_SESSION_ID", value);
                intent.putExtra("isteacher", isteacher);
                startActivity(intent);

            }
        });


        btndpt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(getContext(), Department.class);
                intent.putExtra("isteacher", isteacher);
                startActivity(intent);


            }
        });
        btndownload.setOnClickListener(new View.OnClickListener() {
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
                    startActivity(new Intent(getContext(), Downloads.class));

            }
        });

        auhome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), WebviewActivity.class);
                intent.putExtra("website", "AnnaUniv - HomePage");

                startActivity(intent);

            }
        });
        aures.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(getContext(), WebviewActivity.class);
                intent.putExtra("website", "AnnaUniv - Results");

                startActivity(intent);

            }
        });
        eec.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), WebviewActivity.class);
                intent.putExtra("website", "EEC - HomePage");

                startActivity(intent);

            }
        });


        return v;

    }


    private boolean hasPermissions() {
        int res = 0;
        String[] permissions = new String[]{android.Manifest.permission.WRITE_EXTERNAL_STORAGE};

        for (String perms : permissions) {
            res = checkCallingOrSelfPermission(getActivity(), perms);
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
