package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
private int counter;
private TextView lblCounter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        counter=0;
        lblCounter = (TextView) findViewById(R.id.txtCounterValue);
    }
    public void tambahClick(View view){
        //method tambah counter
        counter = counter + 1;
        lblCounter.setText(String.valueOf(counter));
    }
    public void resetClick(View view){
        //method reset counter
        counter = 0;
        lblCounter.setText(String.valueOf(counter));
    }
}