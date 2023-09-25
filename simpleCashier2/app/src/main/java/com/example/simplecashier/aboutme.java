package com.example.simplecashier;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;

public class aboutme extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.about_me);
    }
    public void email(View view){
        Intent email = new Intent(Intent.ACTION_SEND);
        email.putExtra(Intent.EXTRA_EMAIL, new String[]{ "214101000018@dinamika.ac.id"});
        email.putExtra(Intent.EXTRA_SUBJECT, "Halouu");
        email.putExtra(Intent.EXTRA_TEXT, "Halouuu");
        email.setType("message/rfc822");
        startActivity(Intent.createChooser(email, "Choose an Email client : hohoho"));
    }
    public void notelp (View view){
        startActivity(new Intent(Intent.ACTION_DIAL, Uri.parse("tel:085604330700")));
    }
}