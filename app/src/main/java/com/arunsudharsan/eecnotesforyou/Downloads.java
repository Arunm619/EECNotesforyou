package com.arunsudharsan.eecnotesforyou;

import android.app.ListActivity;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.CardView;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Downloads extends ListActivity {

    private String path;

    private Button btn_gotodashfromoff, continuereading;
    CardView cv_nosave, cv_list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_downloads);
        path = Environment.getExternalStorageDirectory().getAbsolutePath() + "/storage/emulated/0/EEC NOTES/";
        cv_nosave = (CardView) findViewById(R.id.cardnosaved);
        cv_list = (CardView) findViewById(R.id.cardviewforlist);

        cv_nosave.setVisibility(View.GONE);
        btn_gotodashfromoff = (Button) findViewById(R.id.backtodashfromoff);
        btn_gotodashfromoff.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                finish();
            }
        });
        continuereading = (Button) findViewById(R.id.continuereading);
        continuereading.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();

            }
        });

        if (getIntent().hasExtra("path")) {
            path = getIntent().getStringExtra("path");
        }

        //setTitle("Saved");

        // Read all files sorted into the values-array
        List values = new ArrayList();
        File dir = new File(path);
        if (!dir.canRead()) {
            continuereading.setVisibility(View.VISIBLE);
            btn_gotodashfromoff.setVisibility(View.GONE);
            cv_nosave.setVisibility(View.VISIBLE);
            cv_list.setVisibility(View.GONE);
        }
        String[] list = dir.list();
        if (list != null) {
            for (String file : list) {
                if (!file.startsWith(".")) {
                    values.add(file);
                }
            }
        }
        Collections.sort(values);

        // Put the data into the list
        ArrayAdapter adapter = new ArrayAdapter(this, R.layout.mytextviewlist, android.R.id.text1, values);
        setListAdapter(adapter);
    }

    @Override
    protected void onListItemClick(ListView l, View v, int position, long id) {
        String filename = (String) getListAdapter().getItem(position);
        if (path.endsWith(File.separator)) {
            filename = path + filename;
        } else {
            filename = path + File.separator + filename;
        }
        if (new File(filename).isDirectory()) {
            Intent intent = new Intent(this, Downloads.class);
            intent.putExtra("path", filename);
            startActivity(intent);
        } else {
            try {
                File file = new File(filename);
                Intent intent = new Intent(Intent.ACTION_VIEW);
                intent.setDataAndType(Uri.fromFile(file), "application/pdf");
                intent.setFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);
                startActivity(intent);
            } catch (ActivityNotFoundException e) {
                Snackbar bar = Snackbar.make(v, "PDF viewer not found!", Snackbar.LENGTH_INDEFINITE)
                        .setAction("Help me!", new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {

                                Intent i = new Intent(android.content.Intent.ACTION_VIEW);
                                i.setData(Uri.parse("https://play.google.com/store/apps/details?id=com.google.android.apps.pdfviewer&hl=en"));
                                startActivity(i);
                                // Handle user action
                            }
                        });

                bar.show();
                // Toast.makeText(this, "No Viewer Application Found", Toast.LENGTH_SHORT).show();
            } catch (Exception e) {
                e.printStackTrace();
            }

            //  Toast.makeText(this, filename + " is not a directory", Toast.LENGTH_LONG).show();
        }
    }

}

