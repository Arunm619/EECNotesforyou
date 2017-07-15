package com.arunsudharsan.eecnotesforyou;

import android.content.Intent;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class SemesterList extends AppCompatActivity {
    TextView tvdeptname;
    public String isteacher;

    public static String deptname;
    ImageView deptimage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_semester_list);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        deptimage = (ImageView) findViewById(R.id.imageviewidfordept);
isteacher=getIntent().getStringExtra("isteacher");
        deptname = getIntent().getStringExtra("EXTRA_SESSION_ID");
//settingimage
        switch (deptname) {
            case "AUTOMOBILE":
                deptimage.setBackgroundResource(R.drawable.ic_directions_car_black_24dp);
                break;
            case "CIVIL":
                deptimage.setBackgroundResource(R.drawable.ic_business_black_24dp);
                break;
            case "EEE":
                deptimage.setBackgroundResource(R.drawable.ic_lightbulb_outline_black_24dp);
                break;
            case "ECE":
                deptimage.setBackgroundResource(R.drawable.ic_import_export_black_24dp);
                break;
            case "CSE":
                deptimage.setBackgroundResource(R.drawable.ic_computer_black_24dp);
                break;
            case "IT":
                deptimage.setBackgroundResource(R.drawable.ic_laptop_chromebook_black_24dp);
                break;
            case "EIE":
                deptimage.setBackgroundResource(R.drawable.ic_settings_remote_black_24dp);
                break;
            case "MECHANICAL":
                deptimage.setBackgroundResource(R.drawable.ic_settings_black_24dp);
                break;
        }

        tvdeptname = (TextView) findViewById(R.id.textviewDeptname);
        tvdeptname.setText(deptname);
        View parentLayout = findViewById(R.id.activity_semester_list);
        Snackbar.make(parentLayout, "Your choice is " + deptname, Snackbar.LENGTH_LONG).show();

        final ArrayList<String> semester = new ArrayList<String>();
        semester.add("SEMESTER 1");
        semester.add("SEMESTER 2");
        semester.add("SEMESTER 3");
        semester.add("SEMESTER 4");
        semester.add("SEMESTER 5");
        semester.add("SEMESTER 6");
        semester.add("SEMESTER 7");
        semester.add("SEMESTER 8");

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, R.layout.mytextviewlist, semester);
        final ListView listView = (ListView) findViewById(R.id.lisView);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {


                Intent intent = new Intent(getBaseContext(), SubjectsList.class);
                intent.putExtra("semesterno", semester.get(i));
                intent.putExtra("deptname", deptname);
                intent.putExtra("isteacher",isteacher);

                startActivity(intent);

                //    Toast.makeText(SemesterList.this, semester.get(i), Toast.LENGTH_SHORT).show();
            }
        });
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

