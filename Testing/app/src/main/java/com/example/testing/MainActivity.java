package com.example.testing;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ListView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class MainActivity extends AppCompatActivity  {
    Context context;
    ArrayList<Transaksi>listTrx;
    LayoutInflater layoutInflater;

    SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
    EditText idtrans, keter,deb, ker;
    String tanggal;
    String debs;
    String k;
    int debit;
    int kredit;
    ListView lvb;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.helo);
        lvb = (ListView) findViewById(R.id.lvtgs);
        idtrans = (EditText) findViewById(R.id.edit_trans);

        keter = (EditText) findViewById(R.id.keterangan);
        deb = (EditText) findViewById(R.id.eddebet);
        ker = (EditText) findViewById(R.id.edkredit);
        debs = deb.getText().toString();
        debit = Integer.parseInt(debs);
        k = ker.getText().toString();
        kredit = Integer.parseInt(k);

    }

    public void btlaporan (View v ){
        Intent intents = new Intent();
        intents.setClass( MainActivity.this,total.class);
        MainActivity.this.startActivity(intents);
    }
    public void bttambah (){
        Adapter Na = new Adapter(getApplicationContext(),idtrans.getText().toString(), debit, kredit,keter.getText().toString());
        lvb.setAdapter(Na);
    }
}