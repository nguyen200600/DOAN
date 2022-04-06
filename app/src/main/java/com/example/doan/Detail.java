package com.example.doan;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class Detail extends AppCompatActivity {
    private WebView webView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        webView=findViewById(R.id.web_detail);
        Intent intent = getIntent();
        String linkWebView = intent.getStringExtra("link");
        webView.loadUrl(linkWebView);
        webView.setWebViewClient(new WebViewClient());
    }
}