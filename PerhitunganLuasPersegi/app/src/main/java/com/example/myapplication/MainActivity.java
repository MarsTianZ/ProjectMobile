package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.ViewAnimator;

public class MainActivity extends AppCompatActivity {

    private EditText txtPanjang;
    private EditText txtLebar;
    private TextView lblHasil;
    private int luas;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        txtPanjang = (EditText) findViewById(R.id.txtPanjang);
        txtLebar = (EditText) findViewById(R.id.txtLebar);
        lblHasil = (TextView) findViewById(R.id.txtHasil);
    }
    public void btnProsesClick(View view){
        luas = Integer.parseInt(txtPanjang.getText().toString()) * Integer.parseInt(txtLebar.getText().toString());
        lblHasil.setText("Luas Persegi = "+luas);
    }
}