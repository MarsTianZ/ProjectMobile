package com.example.marstianzbrowser;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;

public class MainActivity extends AppCompatActivity {
    WebView webView;
    ImageButton button;
    EditText inputText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        button = findViewById(R.id.imgButtonSearch);
        inputText = findViewById(R.id.inputSearch);

        webView = findViewById(R.id.webView);
        webView.getSettings().setJavaScriptEnabled(true);

        webView.setWebViewClient(new WebViewClient(){
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url){
                view.loadUrl(url);
                return true;
            }
        });
//        String test = inputText.getText();
        Log.d("Testing", String.valueOf(inputText.getText()));
        webView.loadUrl("https://www.google.com" + inputText.getText());
        search();
    }

    private void search() {
        button.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                Log.d("test", String.valueOf(inputText.getText().toString()));
                webView.loadUrl("https://" + inputText.getText().toString());
            }
        });
    }
}