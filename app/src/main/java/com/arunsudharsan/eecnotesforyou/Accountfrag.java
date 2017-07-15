package com.arunsudharsan.eecnotesforyou;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.DrawerLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;


public class Accountfrag extends Fragment {
    public Button logout;
    public TextView emailoftheuser;
    public FirebaseAuth firebaseAuth;
    public Accountfrag() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_accountfrag, container, false);
        logout = (Button) v.findViewById(R.id.btn_logout);
emailoftheuser=(TextView)v.findViewById(R.id.emailoftheuser);
        firebaseAuth = FirebaseAuth.getInstance();
        FirebaseUser user = firebaseAuth.getCurrentUser();
        emailoftheuser.setText(user.getEmail());
        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                firebaseAuth.signOut();
                getActivity().finish();
                //starting login activity
                startActivity(new Intent(getContext(), AreyouteacherorStudent.class));
                Toast.makeText(getContext(), "Sign in!", Toast.LENGTH_SHORT).show();
            }
        });
        return v;
    }
}
