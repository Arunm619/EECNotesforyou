package com.arunsudharsan.eecnotesforyou;

import android.app.Dialog;
import android.app.DownloadManager;
import android.app.ProgressDialog;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.support.annotation.NonNull;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import java.net.URLConnection;
import java.util.List;

import static android.os.Environment.getExternalStorageDirectory;


public class SubjectViewActivity extends AppCompatActivity implements View.OnClickListener {
    static final String csedb = "https://notes-for-you-6b222.firebaseio.com/dept_CSE/";
    private static final int PICK_IMAGE_REQUEST = 234;

    public String fileloc;
    //uri to store file
    private Uri filePath;
    private ProgressDialog pDialog;
    public static final int progress_bar_type = 0;
    //firebase objects
    private StorageReference storageReference;
    private DatabaseReference mDatabase;

    public String sem_no, dpt_name, sub_name, sub_code;
    public String isteacher;
    private static final int PERMS_REQUEST_CODE = 123;
    DatabaseReference dbref;
    TextView tvsubjectname, tvsubjectcode;
    public int uploadnumber = 0;
    Button dpdf1, dpdf2, dpdf3, dpdf4, dpdf5, dpdfImpq, dpdfS;
    Button vpdf1, vpdf2, vpdf3, vpdf4, vpdf5, vpdfImpq, vpdfS;
    Button Updf1, Updf2, Updf3, Updf4, Updf5, UpdfImpq, UpdfS;
    String dlinkforunit1, dlinkforunit2, dlinkforunit3, dlinkforunit4, dlinkforunit5, dlinkforImpq, dlinkforS;

    LinearLayout llforu1, llforu2, llforu3, llforu4, llforu5, llforImpq, llforSylabus;
    LinearLayout llforu1T, llforu2T, llforu3T, llforu4T, llforu5T, llforImpqT, llforSylabusT;

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_subject_view);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

//Data Passing
        isteacher = getIntent().getStringExtra("isteacher");
        sub_name = getIntent().getStringExtra("Subjectname");
        sem_no = getIntent().getStringExtra("semesterno");
        dpt_name = getIntent().getStringExtra("deptname");
        sub_code = getIntent().getStringExtra("Subjectcode");

        //Heading
        getSupportActionBar().setTitle(sub_code);
        tvsubjectname = (TextView) findViewById(R.id.tvSubjectName);
        tvsubjectcode = (TextView) findViewById(R.id.tvSubjectCode);
        tvsubjectcode.setText(sub_code);
        tvsubjectname.setText(sub_name);


//uploading pdf
        storageReference = FirebaseStorage.getInstance().getReference();
        mDatabase = FirebaseDatabase.getInstance().getReference(Constants.DATABASE_PATH_UPLOADS);


        //Linearlayouts

        llforu1 = (LinearLayout) findViewById(R.id.llforu1);
        llforu2 = (LinearLayout) findViewById(R.id.llforu2);
        llforu3 = (LinearLayout) findViewById(R.id.llforu3);
        llforu4 = (LinearLayout) findViewById(R.id.llforu4);
        llforu5 = (LinearLayout) findViewById(R.id.llforu5);
        llforImpq = (LinearLayout) findViewById(R.id.llforimpq);
        llforSylabus = (LinearLayout) findViewById(R.id.llforSyllabus);

        llforu1T = (LinearLayout) findViewById(R.id.llforu1T);
        llforu2T = (LinearLayout) findViewById(R.id.llforu2T);
        llforu3T = (LinearLayout) findViewById(R.id.llforu3T);
        llforu4T = (LinearLayout) findViewById(R.id.llforu4T);
        llforu5T = (LinearLayout) findViewById(R.id.llforu5T);
        llforImpqT = (LinearLayout) findViewById(R.id.llforimpqT);
        llforSylabusT = (LinearLayout) findViewById(R.id.llforSyllabusT);

        if (isteacher.equals("yes")) {

//Upload buttons
            Updf1 = (Button) findViewById(R.id.btn_Uploadpdf1);
            Updf1.setOnClickListener(this);

            Updf2 = (Button) findViewById(R.id.btn_Uploadpdf2);
            Updf2.setOnClickListener(this);

            Updf3 = (Button) findViewById(R.id.btn_Uploadpdf3);
            Updf3.setOnClickListener(this);

            Updf4 = (Button) findViewById(R.id.btn_UploadPdf4);
            Updf4.setOnClickListener(this);

            Updf5 = (Button) findViewById(R.id.btn_UploadPdf5);
            Updf5.setOnClickListener(this);

            UpdfS = (Button) findViewById(R.id.btn_UploadpdfSyllabus);
            UpdfS.setOnClickListener(this);

            UpdfImpq = (Button) findViewById(R.id.btn_UploadPdfImpq);
            UpdfImpq.setOnClickListener(this);

            llforu1.setVisibility(View.GONE);
            llforu2.setVisibility(View.GONE);
            llforu3.setVisibility(View.GONE);
            llforu4.setVisibility(View.GONE);
            llforu5.setVisibility(View.GONE);
            llforImpq.setVisibility(View.GONE);
            llforSylabus.setVisibility(View.GONE);


        } else {

            llforu1T.setVisibility(View.GONE);
            llforu2T.setVisibility(View.GONE);
            llforu3T.setVisibility(View.GONE);
            llforu4T.setVisibility(View.GONE);
            llforu5T.setVisibility(View.GONE);
            llforImpqT.setVisibility(View.GONE);
            llforSylabusT.setVisibility(View.GONE);
        }
        if (dpt_name.equals("CSE")) {
            switch (sem_no) {
                case "SEMESTER 1":
                    dbref = FirebaseDatabase.getInstance().getReferenceFromUrl(csedb + "Sem1");
                    break;
                case "SEMESTER 2":
                    dbref = FirebaseDatabase.getInstance().getReferenceFromUrl(csedb + "Sem2");
                    break;
                case "SEMESTER 3":
                    dbref = FirebaseDatabase.getInstance().getReferenceFromUrl(csedb + "Sem3");
                    break;
                case "SEMESTER 4":
                    dbref = FirebaseDatabase.getInstance().getReferenceFromUrl(csedb + "Sem4");
                    break;
                case "SEMESTER 5":
                    dbref = FirebaseDatabase.getInstance().getReferenceFromUrl(csedb + "Sem5");
                    break;
                case "SEMESTER 6":
                    dbref = FirebaseDatabase.getInstance().getReferenceFromUrl(csedb + "Sem6");
                    break;
                case "SEMESTER 7":
                    dbref = FirebaseDatabase.getInstance().getReferenceFromUrl(csedb + "Sem7");
                    break;
                case "SEMESTER 8":
                    dbref = FirebaseDatabase.getInstance().getReferenceFromUrl(csedb + "Sem8");
                    break;
            }


            dbref.child(sub_code).addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    Modelnotes modelnotes = dataSnapshot.getValue(Modelnotes.class);
                    dlinkforunit1 = modelnotes.getUnit1();
                    dlinkforunit2 = modelnotes.getUnit2();
                    dlinkforunit3 = modelnotes.getUnit3();
                    dlinkforunit4 = modelnotes.getUnit4();
                    dlinkforunit5 = modelnotes.getUnit5();
                    dlinkforImpq = modelnotes.getImpq();
                    dlinkforS = modelnotes.getSyllabus();


                }

                @Override
                public void onCancelled(DatabaseError databaseError) {

                }
            });


        }

//        Toast.makeText(this, "hi im a"+isteacher+"teacher", Toast.LENGTH_SHORT).show();
//Buttons Declaration
        dpdf1 = (Button) findViewById(R.id.btn_DownloadPdf1);
        dpdf1.setOnClickListener(this);
        dpdf2 = (Button) findViewById(R.id.btn_DownloadPdf2);
        dpdf2.setOnClickListener(this);
        dpdf3 = (Button) findViewById(R.id.btn_DownloadPdf3);
        dpdf3.setOnClickListener(this);
        dpdf4 = (Button) findViewById(R.id.btn_DownloadPdf4);
        dpdf4.setOnClickListener(this);
        dpdf5 = (Button) findViewById(R.id.btn_DownloadPdf5);
        dpdf5.setOnClickListener(this);
        dpdfImpq = (Button) findViewById(R.id.btn_DownloadPdfImpq);
        dpdfImpq.setOnClickListener(this);
        dpdfS = (Button) findViewById(R.id.btn_DownloadPdfS);
        dpdfS.setOnClickListener(this);

        vpdf1 = (Button) findViewById(R.id.btn_ViewPdf1);
        vpdf1.setOnClickListener(this);
        vpdf2 = (Button) findViewById(R.id.btn_ViewPdf2);
        vpdf2.setOnClickListener(this);
        vpdf3 = (Button) findViewById(R.id.btn_ViewPdf3);
        vpdf3.setOnClickListener(this);
        vpdf4 = (Button) findViewById(R.id.btn_ViewPdf4);
        vpdf4.setOnClickListener(this);
        vpdf5 = (Button) findViewById(R.id.btn_ViewPdf5);
        vpdf5.setOnClickListener(this);
        vpdfImpq = (Button) findViewById(R.id.btn_ViewPdfImpq);
        vpdfImpq.setOnClickListener(this);
        vpdfS = (Button) findViewById(R.id.btn_ViewPdfS);
        vpdfS.setOnClickListener(this);


    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, final Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK && data != null && data.getData() != null) {


            filePath = data.getData();
            try {
                UploadFILE();
            } catch (Exception e) {
                Toast.makeText(this, e.getMessage(), Toast.LENGTH_SHORT).show();
            }


        }
    }

    private void UploadFILE() {

        if (filePath != null) {
            //displaying progress dialog while image is uploading
            final ProgressDialog progressDialog = new ProgressDialog(this);
            progressDialog.setTitle("Uploading");
            progressDialog.show();

            //getting the storage reference
            final StorageReference sRef = storageReference.child(Constants.STORAGE_PATH_UPLOADS + dpt_name + Constants.DATABASE_SEP + sem_no + Constants.DATABASE_SEP + sub_name + Constants.DATABASE_SEP + sub_name + " " + uploadnumber + Constants.DATABASE_SEP);

            //adding the file to reference
            sRef.putFile(filePath)
                    .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                            //dismissing the progress dialog
                            progressDialog.dismiss();

                            //displaying success toast
                            Toast.makeText(getApplicationContext(), "File Uploaded ", Toast.LENGTH_LONG).show();


                            //final StorageReference sRef = storageReference.child(Constants.STORAGE_PATH_UPLOADS + Constants.DATABASE_SEP + dpt_name + Constants.DATABASE_SEP + sem_no + Constants.DATABASE_SEP + sub_name + Constants.DATABASE_SEP + sub_name + " " + uploadnumber +Constants.DATABASE_SEP);

                            final StorageReference sRef = storageReference.child(Constants.STORAGE_PATH_UPLOADS + dpt_name + Constants.DATABASE_SEP + sem_no + Constants.DATABASE_SEP + sub_name + Constants.DATABASE_SEP + sub_name + " " + uploadnumber + Constants.DATABASE_SEP);


                            sRef.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>()

                            {
                                @Override
                                public void onSuccess(Uri uri) {

                                    switch (sem_no) {
                                        case "SEMESTER 1":

                                            sem_no = "Sem1";
                                            break;
                                        case "SEMESTER 2":
                                            sem_no = "Sem2";
                                            break;
                                        case "SEMESTER 3":
                                            sem_no = "Sem3";
                                            break;
                                        case "SEMESTER 4":
                                            sem_no = "Sem4";
                                            break;
                                        case "SEMESTER 5":
                                            sem_no = "Sem5";
                                            break;
                                        case "SEMESTER 6":
                                            sem_no = "Sem6";
                                            break;
                                        case "SEMESTER 7":
                                            sem_no = "Sem7";
                                            break;
                                        case "SEMESTER 8":
                                            sem_no = "Sem8";
                                            break;


                                    }

                                    DatabaseReference db;

                                    db = FirebaseDatabase.getInstance().getReferenceFromUrl("https://notes-for-you-6b222.firebaseio.com/dept_CSE/" + sem_no + Constants.DATABASE_SEP + sub_code + Constants.DATABASE_SEP + fileloc);

                                    //Toast.makeText(SubjectViewActivity.this, "https://notes-for-you-6b222.firebaseio.com/dept_CSE/" + sem_no + Constants.DATABASE_SEP + sub_code + Constants.DATABASE_SEP + fileloc, Toast.LENGTH_SHORT).show();

//                                    Toast.makeText(SubjectViewActivity.this, uri.toString(), Toast.LENGTH_SHORT).show();

                                    db.setValue(uri.toString()).addOnFailureListener(new OnFailureListener() {
                                        @Override
                                        public void onFailure(@NonNull Exception e) {
                                            Toast.makeText(getApplicationContext(), "Failure", Toast.LENGTH_LONG).show();

                                        }
                                    }).addOnSuccessListener(new OnSuccessListener<Void>() {
                                        @Override
                                        public void onSuccess(Void aVoid) {
                                            Toast.makeText(getApplicationContext(), "Succcesful", Toast.LENGTH_LONG).show();

                                        }
                                    });
                                }
                            });
                        }
                    })
                    .addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception exception) {
                            progressDialog.dismiss();
                            Toast.makeText(getApplicationContext(), exception.getMessage(), Toast.LENGTH_LONG).show();
                        }
                    })
                    .addOnProgressListener(new OnProgressListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onProgress(UploadTask.TaskSnapshot taskSnapshot) {
                            //displaying the upload progress

                            progressDialog.setMessage("Uploading...");
                        }
                    });
        } else {
            //display an error if no file is selected
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();

                startActivity(new Intent(this, NavDraweforStudent.class));
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    class DownloadFileFromURL extends AsyncTask<String, String, String> {

        /**
         * Before starting background thread
         * Show Progress Bar Dialog
         */
        @Override
        protected void onPreExecute() {
            super.onPreExecute();

            pDialog = new ProgressDialog(SubjectViewActivity.this);
            pDialog.setMessage("Opening File...");
            pDialog.setIndeterminate(false);
            pDialog.setMax(100);
            pDialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
            pDialog.setCancelable(true);
            pDialog.show();
        }

        /**
         * Downloading file in background thread
         */
        @Override
        protected String doInBackground(String... f_url) {
            int count;
            try {
                URL url = new URL(f_url[0]);
                URLConnection conection = url.openConnection();
                conection.connect();
                // getting file length
                int lenghtOfFile = conection.getContentLength();

                // input stream to read file - with 8k buffer
                InputStream input = new BufferedInputStream(url.openStream(), 8192);

                // Output stream to write file
                OutputStream output = new FileOutputStream(Environment.getExternalStorageDirectory() + "/temp.pdf");

                byte data[] = new byte[1024];

                long total = 0;

                while ((count = input.read(data)) != -1) {
                    total += count;
                    // publishing the progress....
                    // After this onProgressUpdate will be called
                    publishProgress("" + (int) ((total * 100) / lenghtOfFile));

                    // writing data to file
                    output.write(data, 0, count);
                }

                // flushing output
                output.flush();

                // closing streams
                output.close();
                input.close();

            } catch (Exception e) {
                //     Toast.makeText(SubjectViewActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                Log.e("Error: ", e.getMessage());
            }

            return null;
        }

        /**
         * Updating progress bar
         */
        protected void onProgressUpdate(String... progress) {
            // setting progress percentage
            pDialog.setProgress(Integer.parseInt(progress[0]));
        }

        /**
         * After completing background task
         * Dismiss the progress dialog
         **/
        @Override
        protected void onPostExecute(String file_url) {
            // dismiss the dialog after the file was downloaded
            pDialog.dismiss();
            // Displaying pdf
            // Reading path from sdcard
            try {
                String pdfpath = Environment.getExternalStorageDirectory() + "/temp.pdf";
                Toast.makeText(SubjectViewActivity.this, pdfpath, Toast.LENGTH_SHORT).show();
                File file = new File(pdfpath);
                Intent intent = new Intent(Intent.ACTION_VIEW);
                intent.setDataAndType(Uri.fromFile(file), "application/pdf");
                intent.setFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);

                startActivity(intent);
            } catch (ActivityNotFoundException e) {
                View parentLayout = findViewById(R.id.activity_subject_view);

                Snackbar bar = Snackbar.make(parentLayout, "PDF viewer not found!", Snackbar.LENGTH_INDEFINITE)
                        .setAction("Help me!", new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {

                                Intent i = new Intent(android.content.Intent.ACTION_VIEW);
                                i.setData(Uri.parse("https://play.google.com/store/apps/details?id=com.google.android.apps.pdfviewer&hl=en"));
                                startActivity(i);
                                // Handle user action
                            }
                        });

            }
            }

        }

        @Override
        public void onClick(View view) {

            //DOWNLOAD PDF
            if (view == dpdf1) {
                if (hasPermissions()) {
                    // our app has permissions.
                    DownloadPdf(dlinkforunit1);
                } else {
                    //our app doesn't have permissions, So i m requesting permissions.
                    requestPerms();
                }
            }
            if (view == dpdf2) {

                if (hasPermissions()) {
                    // our app has permissions.
                    DownloadPdf(dlinkforunit2);
                } else {
                    //our app doesn't have permissions, So i m requesting permissions.
                    requestPerms();
                }
            }
            if (view == dpdf3) {

                if (hasPermissions()) {
                    // our app has permissions.
                    DownloadPdf(dlinkforunit3);
                } else {
                    //our app doesn't have permissions, So i m requesting permissions.
                    requestPerms();
                }
            }
            if (view == dpdf4) {

                if (hasPermissions()) {
                    // our app has permissions.
                    DownloadPdf(dlinkforunit4);
                } else {
                    //our app doesn't have permissions, So i m requesting permissions.
                    requestPerms();
                }
            }
            if (view == dpdf5) {

                if (hasPermissions()) {
                    // our app has permissions.
                    DownloadPdf(dlinkforunit5);
                } else {
                    //our app doesn't have permissions, So i m requesting permissions.
                    requestPerms();
                }
            }
            if (view == dpdfImpq) {

                if (hasPermissions()) {
                    // our app has permissions.
                    DownloadPdf(dlinkforImpq);
                } else {
                    //our app doesn't have permissions, So i m requesting permissions.
                    requestPerms();
                }
            }
            if (view == dpdfS) {

                if (hasPermissions()) {
                    // our app has permissions.
                    DownloadPdf(dlinkforS);
                } else {
                    //our app doesn't have permissions, So i m requesting permissions.
                    requestPerms();
                }
            }


            //View PDF FILES
            if (view == vpdf1) {
                if (hasPermissions()) {
                    // our app has permissions.
                    ViewPdf(dlinkforunit1);
                } else {
                    //our app doesn't have permissions, So i m requesting permissions.
                    requestPerms();
                }

            }
            if (view == vpdf2) {
                if (hasPermissions()) {
                    // our app has permissions.
                    ViewPdf(dlinkforunit2);
                } else {
                    //our app doesn't have permissions, So i m requesting permissions.
                    requestPerms();
                }
            }
            if (view == vpdf3) {
                if (hasPermissions()) {
                    // our app has permissions.
                    ViewPdf(dlinkforunit3);
                } else {
                    //our app doesn't have permissions, So i m requesting permissions.
                    requestPerms();
                }
            }
            if (view == vpdf4) {
                if (hasPermissions()) {
                    // our app has permissions.
                    ViewPdf(dlinkforunit4);
                } else {
                    //our app doesn't have permissions, So i m requesting permissions.
                    requestPerms();
                }
            }
            if (view == vpdf5) {
                if (hasPermissions()) {
                    // our app has permissions.
                    ViewPdf(dlinkforunit5);
                } else {
                    //our app doesn't have permissions, So i m requesting permissions.
                    requestPerms();
                }
            }
            if (view == vpdfImpq) {
                if (hasPermissions()) {
                    // our app has permissions.
                    ViewPdf(dlinkforImpq);
                } else {
                    //our app doesn't have permissions, So i m requesting permissions.
                    requestPerms();
                }
            }
            if (view == vpdfS) {
                if (hasPermissions()) {
                    // our app has permissions.
                    ViewPdf(dlinkforS);
                } else {
                    //our app doesn't have permissions, So i m requesting permissions.
                    requestPerms();
                }
            }

            if (view == Updf1) {


                Upload(1);
            }

            if (view == Updf2) {
                Upload(2);

            }

            if (view == Updf3) {
                Upload(3);
            }

            if (view == Updf4) {
                Upload(4);
            }
            if (view == Updf5) {
                Upload(5);
            }


            if (view == UpdfImpq) {
                Upload(6);
            }


            if (view == UpdfS) {
                Upload(7);
            }
        }

        private void Upload(int a) {

            showFileChooser(a);

        }

        private void showFileChooser(int b) {
            Intent intent = new Intent();
            intent.setType("application/pdf");
            intent.setAction(Intent.ACTION_GET_CONTENT);
            switch (b) {
                case 1:
                    uploadnumber = 1;
                    fileloc = "Unit1";
                    startActivityForResult(Intent.createChooser(intent, "Select PDF"), PICK_IMAGE_REQUEST);
                    break;
                case 2:
                    fileloc = "Unit2";
                    uploadnumber = 2;
                    startActivityForResult(Intent.createChooser(intent, "Select PDF"), PICK_IMAGE_REQUEST);
                    break;
                case 3:
                    fileloc = "Unit3";
                    uploadnumber = 3;
                    startActivityForResult(Intent.createChooser(intent, "Select PDF"), PICK_IMAGE_REQUEST);
                    break;
                case 4:
                    fileloc = "Unit4";
                    uploadnumber = 4;
                    startActivityForResult(Intent.createChooser(intent, "Select PDF"), PICK_IMAGE_REQUEST);
                    break;
                case 5:
                    fileloc = "Unit5";
                    uploadnumber = 5;
                    startActivityForResult(Intent.createChooser(intent, "Select PDF"), PICK_IMAGE_REQUEST);
                    break;
                case 6:
                    uploadnumber = 6;
                    fileloc = "Impq";
                    startActivityForResult(Intent.createChooser(intent, "Select PDF"), PICK_IMAGE_REQUEST);
                    break;
                case 7:
                    fileloc = "Syllabus";
                    uploadnumber = 7;
                    startActivityForResult(Intent.createChooser(intent, "Select PDF"), PICK_IMAGE_REQUEST);
                    break;
            }
        }


        public void ViewPdf(String pdfurl) {


            new DownloadFileFromURL().execute(pdfurl);

        }


        private boolean hasPermissions() {
            int res = 0;
            //string array of permissions,
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

        @Override
        public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
            boolean allowed = true;

            switch (requestCode) {
                case PERMS_REQUEST_CODE:

                    for (int res : grantResults) {
                        // if user granted all permissions.
                        allowed = allowed && (res == PackageManager.PERMISSION_GRANTED);
                    }

                    break;
                default:
                    // if user not granted permissions.
                    allowed = false;
                    break;
            }

            if (allowed) {
                //user granted all permissions we can perform our task.

                View parentLayout = findViewById(R.id.activity_subject_view);
                Snackbar.make(parentLayout, "Permission Granted...", Snackbar.LENGTH_LONG).show();

            } else {
                // we will give warning to user that they haven't granted permissions.
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                    if (shouldShowRequestPermissionRationale(android.Manifest.permission.WRITE_EXTERNAL_STORAGE)) {
                        Toast.makeText(this, "Storage Permissions denied.", Toast.LENGTH_SHORT).show();
                    }
                }
            }

        }

        public void DownloadPdf(String pdfurl) {

            // pdfurl = pdfurl.replace(" ", "%20");
            Uri myUri = Uri.parse(pdfurl);


            DownloadManager downloadManager = (DownloadManager) (getApplicationContext()).getSystemService(Context.DOWNLOAD_SERVICE);
            DownloadManager.Request request = new DownloadManager.Request(myUri);
            request.setAllowedNetworkTypes(DownloadManager.Request.NETWORK_WIFI | DownloadManager.Request.NETWORK_MOBILE)
                    .setAllowedOverRoaming(false)
                    .setTitle("Downloading " + sub_name)
                    .setDescription("The notes for " + sub_name + " -" + sub_code + "  is being Downloaded.\n Please wait...")
                    .setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED)
                    .setDestinationInExternalPublicDir(getExternalStorageDirectory() + "/EEC NOTES/" + dpt_name + "/" + sem_no + "/", sub_name + sub_code + ".pdf");
            downloadManager.enqueue(request);


        }

    }

