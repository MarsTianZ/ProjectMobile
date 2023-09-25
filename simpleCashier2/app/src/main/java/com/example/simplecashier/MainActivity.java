package com.example.simplecashier;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private TextView hasilView;

    private Button getesteh, getesjeruk, getnasipecel, getnasirawon, getbayar, getbatal, aboutme;

    private int total = 0, estehvalue = 3000, esjerukvalue = 4000, nasipecelvalue = 10000, nasirawonvalue = 12000;

    private Integer jmlhesteh = 0, jmlhesjeruk = 0, jmlhPecel = 0, jmlhRawon = 0;

    private View mainActivity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getbayar = (Button) findViewById(R.id.btn_bayar);
        getbayar.setOnClickListener(this);
        getbatal = (Button) findViewById(R.id.btn_batal);
        getbatal.setOnClickListener(this);
        getesteh = (Button) findViewById(R.id.btn_esteh);
        getesteh.setOnClickListener(this);
        getesjeruk = (Button) findViewById(R.id.btn_esjeruk);
        getesjeruk.setOnClickListener(this);
        getnasipecel = (Button) findViewById(R.id.btn_nasiPecel);
        getnasipecel.setOnClickListener(this);
        getnasirawon = (Button) findViewById(R.id.btn_nasiRawon);
        getnasirawon.setOnClickListener(this);
        hasilView = (TextView) findViewById(R.id.Tview);
        hasilView.setEnabled(false);
        mainActivity = (View) findViewById(R.id.Tview);
        aboutme = (Button) findViewById(R.id.btn_aboutme);
        aboutme.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_bayar:
                String[] datas = new String[13];
                datas[0] = jmlhesteh.toString();
                datas[1] = String.valueOf(estehvalue);
                datas[2] = String.valueOf(estehvalue * jmlhesteh);
                datas[3] = jmlhesjeruk.toString();
                datas[4] = String.valueOf(esjerukvalue);
                datas[5] = String.valueOf(esjerukvalue * jmlhesjeruk);
                datas[6] = jmlhPecel.toString();
                datas[7] = String.valueOf(nasipecelvalue);
                datas[8] = String.valueOf(nasipecelvalue * jmlhPecel);
                datas[9] = jmlhRawon.toString();
                datas[10] = String.valueOf(nasirawonvalue);
                datas[11] = String.valueOf(nasirawonvalue * jmlhRawon);
                datas[12] = String.valueOf(total);

                jmlhRawon = 0;
                jmlhPecel = 0;
                jmlhesjeruk = 0;
                jmlhesteh = 0;
                Intent Nota = new Intent(MainActivity.this, nota.class);
                Nota.putExtra("nota", datas);
                startActivity(Nota);
                break;

            case R.id.btn_aboutme:
                Intent aboutme = new Intent(MainActivity.this, com.example.simplecashier.aboutme.class);
                startActivity(aboutme);
                break;

            case R.id.btn_batal:
                String text;
                if (total == 0) {
                } else {
                    total = 0;
                    jmlhesteh = 0;
                    jmlhesjeruk = 0;
                    jmlhPecel = 0;
                    jmlhRawon = 0;
                    getesteh.setText("Es Teh");
                    getesjeruk.setText("Es Jeruk");
                    getnasipecel.setText("Nasi Pecel");
                    getnasirawon.setText("Nasi Rawon");
                }
                hasilView.setText(String.valueOf(total));
                break;

            case R.id.btn_esteh:
                total = total + estehvalue;
                jmlhesteh++;
                hasilView.setText(String.valueOf(total));
                getesteh.setText("Es Teh (" + jmlhesteh + ")");
                break;

            case R.id.btn_esjeruk:
                total = total + esjerukvalue;
                jmlhesjeruk++;
                hasilView.setText(String.valueOf(total));
                getesjeruk.setText("Es Jeruk (" + jmlhesjeruk + ")");
                break;

            case R.id.btn_nasiPecel:
                total = total + nasipecelvalue;
                jmlhPecel++;
                hasilView.setText(String.valueOf(total));
                getnasipecel.setText("Nasi Pecel (" + jmlhPecel + ")");
                break;

            case R.id.btn_nasiRawon:
                total = total + nasirawonvalue;
                jmlhRawon++;
                hasilView.setText(String.valueOf(total));
                getnasirawon.setText("Nasi Rawon (" + jmlhRawon + ")");
                break;

        }
    }
}