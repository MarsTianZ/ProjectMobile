package com.example.testweb;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class MainActivity extends AppCompatActivity {
    private WebView mwebView;

    @Override
    protected void onPostCreate(@Nullable Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mwebView = (WebView) findViewById(R.id.webView);
        mwebView.getSettings().setJavaScriptEnabled(true);
        mwebView.setWebViewClient(new WebViewClient(){
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                view.loadUrl(url);
                return true;
            }
        });
        mwebView.loadUrl("file:///android_asset/index.html");
        //mwebView.getSettings().setJavaScriptEnabled(true);
        //mwebView.getSettings().setBuiltInZoomControls(true);
        //String data = "<html>" + "<body style='font-size:16pt;'>" + "<h1>Halaman Web 000280</h1><p>Selamat datang di WebView page</p>" + "</body>" + "</html>" ;

    }
}