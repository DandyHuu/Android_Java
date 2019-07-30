package com.t3h.buoi10;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class WedActivity extends AppCompatActivity {
    private WebView webView;
    private ProgressDialog progressDialog;
    private boolean check = false;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news);


        initView();
    }

    private void initView() {
        webView = findViewById(R.id.webView);

        progressDialog = new ProgressDialog(WedActivity.this);
        progressDialog.setMessage("Please wait...");
        progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        progressDialog.show();

        webView.getSettings().getLoadsImagesAutomatically();
        check = webView.getSettings().getJavaScriptEnabled();
        webView.setScrollBarStyle(View.SCROLLBARS_INSIDE_OVERLAY);

        Intent intent = getIntent();
        String url = intent.getStringExtra(Const.EXTRA_URL);
        if (check == true) {
            progressDialog.dismiss();
        }

        webView.loadUrl(url);

    }

}
