package com.arunsudharsan.eecnotesforyou;


import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;


/**
 * A simple {@link Fragment} subclass.
 */
public class upcomingeventsfrag extends Fragment {
    private String sympname, symcontent, sympwlink;
    public TextView tvsympname, tvsympcontents;
    public Button register;

    DatabaseReference symposiumref;

    public upcomingeventsfrag() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
       View v= inflater.inflate(R.layout.fragment_upcomingeventsfrag, container, false);
        tvsympname = (TextView) v.findViewById(R.id.sympnametv);
        tvsympcontents = (TextView) v.findViewById(R.id.symposiumcontentstv);
        register = (Button) v.findViewById(R.id.btn_registersym);


        symposiumref = FirebaseDatabase.getInstance().getReferenceFromUrl("https://notes-for-you-6b222.firebaseio.com/Other");

        symposiumref.child("Symposium_name").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                sympname = dataSnapshot.getValue(String.class);

                tvsympname.setText(sympname);

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



        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (sympwlink != null) {

                    Intent intent = new Intent(getContext(), WebviewActivity.class);
                    intent.putExtra("website", sympwlink);

                    startActivity(intent);


                } else {
                    Snackbar.make(v, "Please  wait...  ", Snackbar.LENGTH_LONG)
                            .setAction("Action", null).show();

                }
            }
        });
        return v;
    }

}
