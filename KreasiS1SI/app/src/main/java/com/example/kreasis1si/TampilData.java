package com.example.kreasis1si;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONObject;

public class TampilData extends AppCompatActivity {

    //deklarasi variabel
    private TextView code_txtStatus;
    private ListView code_lvMahasiswa;

    //deklarasi variabel tambahan
    private String URL = "http://192.168.1.7/webdasar/kreasi/select.php";
    //Catatan: URL di atas tolong disesuaikan dengan IP4 kalian, cek di Command Prompt, ketik ipconfig

    //Stringrequest salah satu library volley utk menangkap data
    StringRequest stringRequest;
    RequestQueue requestQueue;

    //deklarasi variable untuk Json
    private JSONObject jsonObj; //digunakan untuk proses pengambilan data JSon
    private JSONArray jsonMahasiswa;
    private JSONObject jsonData;

    //arrayadapter digunakan untuk menampung data dalam array
    private ArrayAdapter adapterMahasiswa;

    //variabel temporary
    String nim, nama; //variabel ini digunakan untuk menampung isi dari nim dan nama

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tampil_data);

        //konfigurasi
        code_txtStatus = (TextView) findViewById(R.id.txtStatus);
        code_lvMahasiswa = (ListView)findViewById(R.id.lvMahasiswa);

        //membuat constructor ArrayAdapter (dengan 2 parameter)
        //android.R.layout.simple_list_item_1 adalah layout bawaan dari Android sendiri
        adapterMahasiswa = new ArrayAdapter(getApplicationContext(), android.R.layout.simple_list_item_1);

        //instance of class
        stringRequest = new StringRequest(Request.Method.POST, URL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                code_txtStatus.setText("masuk di method onResponse()");

                //selalu mulai dengan try...catch...
                try {
                    //instance of class JSONObj
                    jsonObj = new JSONObject(response);
                    //instance of class JSONObj. Isi parameter berdasarkan dari nama array di JSON
                    jsonMahasiswa = jsonObj.getJSONArray("mahasiswa");
                    //hitung jumlah baris data
                    for (int i = 0; i < jsonMahasiswa.length(); i++)
                    {
                        jsonData = jsonMahasiswa.getJSONObject(i);
                        //tampung data ke dalam variabel
                        nim = jsonData.getString("nim");
                        nama = jsonData.getString("nama");

                        //membuat data adapter menggunakan method add()
                        adapterMahasiswa.add("NIM = " + nim + "\n" + "Nama = " + nama);
                    }
                    //mengirim data adapter utk di tempatkan ke dalam List view menggunakan method setAdapter()
                    code_lvMahasiswa.setAdapter(adapterMahasiswa);
                }
                catch (Exception e) {
                    e.printStackTrace();
                }


            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                //code_txtStatus.setText("masuk di method onError()");
                code_txtStatus.setText(error.toString());

            }
        });

        //request ke Volley
        requestQueue = Volley.newRequestQueue(getApplicationContext());
        requestQueue.add(stringRequest);

    }
}