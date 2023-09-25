package com.example.kreasis1si;

import android.content.Intent;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.HashMap;
import java.util.Map;

public class SimpanData extends AppCompatActivity {
    //deklarasi variabel
    private EditText code_txtNIM, code_txtNama;
    private Button code_btnSimpan;
    private TextView code_txtStatus;

    //deklarasi variabel tambahan
    private String URL = "http://192.168.1.7/webdasar/kreasi/insert.php";
    //Catatan: URL di atas tolong disesuaikan dengan IP4 kalian, cek di Command Prompt, ketik ipconfig

    //Stringrequest salah satu library volley utk menangkap data
    StringRequest stringRequest;
    RequestQueue requestQueue;

    //deklarasi variabel tambahan
    private String nim, nama; //variabel ini digunakan untuk menampung isi dari "code_txtNIM" dan "code_txtNama"
    private Button button;  // Deklarasi button di luar metode onClick()
    private View view;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_simpan_data);
        button = findViewById(R.id.button);  // Inisialisasi button
        view = findViewById(R.id.view);      // Inisialisasi view
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SimpanData.this, TampilData.class);
                startActivity(intent);
            }
        });

        //konfigurasi
        code_txtNIM = (EditText)findViewById(R.id.txtNIM);
        code_txtNama = (EditText)findViewById(R.id.txtNama);
        code_btnSimpan = (Button)findViewById(R.id.btnSimpan);
        code_txtStatus = (TextView) findViewById(R.id.txtStatus);

        //ketika btnSimpan diklik
        code_btnSimpan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //selalu mulai dengan try...catch...
                try {
                    //untuk memastikan data yang terkirim sudah terisi di EditText, maka EditText tidak boleh kosong
                    //tampung data dari EditText ke variabel
                    nim = code_txtNIM.getText().toString().trim();
                    nama = code_txtNama.getText().toString().trim();

                    //cek terlebih dahulu jika inputan kosong atau tidak
                    if (nim.equals("") || nama.equals("")) //jika inputan kosong
                    {
                        code_txtStatus.setText("Inputan tidak lengkap"); //cara 1 dengan TextView
                        //Toast.makeText(getApplicationContext(), "Inputan tidak lengkap", Toast.LENGTH_LONG).show(); //cara 2 dengan Toast
                    }
                    else
                    {
                        //instance of class
                        stringRequest = new StringRequest(Request.Method.POST, URL, new Response.Listener<String>() {
                            @Override
                            public void onResponse(String response) {
                                //untuk mengecek masuk di method onResponse()
                                //code_txtStatus.setText("masuk di method onResponse()"); //cara 1 dengan TextView
                                //Toast.makeText(getApplicationContext(), "masuk di method onResponse()", Toast.LENGTH_LONG).show(); //cara 2 dengan Toast

                                //cek apakah response = "Tersimpan" atau "Gagal Simpan"
                                //jika response = "Tersimpan", akan muncul Toast Data Berhasil Disimpan
                                if (response.equals("Tersimpan"))
                                    //membuat Toast untuk pesan data tersimpan
                                    Toast.makeText(getApplicationContext(), "Data Berhasil Disimpan", Toast.LENGTH_LONG).show();
                                else
                                    Toast.makeText(getApplicationContext(), "Data Gagal Disimpan", Toast.LENGTH_LONG).show();


                            }
                        }, new Response.ErrorListener() {
                            @Override
                            public void onErrorResponse(VolleyError error) {

                            }
                        })
                        {
                            //anda cukup menuliskan "map", lalu enter. Otomatis method getParams akan terbentuk
                            @Nullable
                            @Override
                            protected Map<String, String> getParams() throws AuthFailureError {
                                //jika script masih ada warning, brarti blom di transfer menggunakan method POST
                                //oleh karena itu, dibutuhkan 3 baris script ini untuk mengirim data ke login.php
                                Map<String, String> data = new HashMap<>();
                                data.put("nim", nim);
                                data.put("nama", nama);
                                return data;
                            }
                        };

                        //request ke Volley
                        requestQueue = Volley.newRequestQueue(getApplicationContext());
                        requestQueue.add(stringRequest);


                    }

                }
                catch (Exception e) {
                    e.printStackTrace();
                }


            }
        });


    }
}