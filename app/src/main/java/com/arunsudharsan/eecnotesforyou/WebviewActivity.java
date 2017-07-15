package com.arunsudharsan.eecnotesforyou;

import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class WebviewActivity extends AppCompatActivity {
    String website;
    String webpage;
    WebView webView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_webview);

        website = getIntent().getStringExtra("website");


        switch (website) {
            case "AnnaUniv - HomePage":

                webpage = "https://www.annauniv.edu/";
                break;

            case "AnnaUniv - Results":
                webpage = "http://coe1.annauniv.edu/home/";
                break;

            case "EEC - HomePage":
                webpage = "http://srmeaswari.ac.in/";
                break;

            default:
                webpage = Uri.parse(website).toString();
                break;

        }
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle(website);

        webView = (WebView) this.findViewById(R.id.webviewforweb);
        webView.setWebViewClient(new WebViewClient());
        if (!webpage.contains("http")) {
            webpage = "http://" + webpage;
        }
        webView.loadUrl(webpage);
        webView.loadUrl(webpage);
        webView.getSettings().setBuiltInZoomControls(true);
        webView.getSettings().setDisplayZoomControls(false);


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
