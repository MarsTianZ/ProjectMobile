package com.example.uaspbm;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.jetbrains.annotations.Nullable;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

public class MainView extends AppCompatActivity {

    ListView listView;
    MyAdapter adapter;
    public static ArrayList<Buku> bukuArrayList = new ArrayList<>();
    String URL = "http://192.168.1.7/webdasar/PBM/pinjamBuku2/read.php";

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_view);

        listView = findViewById(R.id.listView);
        adapter = new MyAdapter(this, bukuArrayList);
        listView.setAdapter(adapter);

        retrieveData();
    }

    public void retrieveData() {
        StringRequest stringRequest = new StringRequest(Request.Method.POST, URL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                bukuArrayList.clear();
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    String success = jsonObject.getString("Success");
                    JSONArray jsonArray = jsonObject.getJSONArray("data");

                    if (success.equals("1")) {
                        for (int i = 0; i < jsonArray.length(); i++) {
                            JSONObject object = jsonArray.getJSONObject(i);

                            String id = object.getString("id");
                            String judul = object.getString("judul");
                            String penulis = object.getString("penulis");
                            String penerbit = object.getString("penerbit");

                            // Ubah tipe data tahunTerbit menjadi Date
                            String tahunTerbitString = object.getString("tahunTerbit");
                            Date tahunTerbit = null;
                            try {
                                SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());
                                tahunTerbit = dateFormat.parse(tahunTerbitString);
                            } catch (Exception e) {
                                e.printStackTrace();
                            }

                            String deskripsi = object.getString("deskripsi");
                            String cover = object.getString("cover");

                            // Buat objek buku dan tambahkan ke dalam bukuArrayList
                            Buku buku = new Buku(id, judul, penulis, penerbit, tahunTerbit, deskripsi, cover);
                            bukuArrayList.add(buku);
                        }

                        // Set adapter untuk listView setelah data selesai diambil
                        adapter.notifyDataSetChanged();
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(MainView.this, error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }

    public void executeSearch(View view) {
        EditText editTextSearch = findViewById(R.id.searchBar);
        String searchQuery = editTextSearch.getText().toString().trim();

        String searchUrl = "http://192.168.1.7/webdasar/PBM/pinjamBuku2/search.php";

        StringRequest stringRequest = new StringRequest(Request.Method.POST, searchUrl, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                bukuArrayList.clear();
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    String success = jsonObject.getString("Success");
                    JSONArray jsonArray = jsonObject.getJSONArray("data");

                    if (success.equals("1")) {
                        for (int i = 0; i < jsonArray.length(); i++) {
                            JSONObject object = jsonArray.getJSONObject(i);

                            String id = object.getString("id");
                            String judul = object.getString("judul");
                            String penulis = object.getString("penulis");
                            String penerbit = object.getString("penerbit");
                            String tahunTerbitString = object.getString("tahunTerbit");
                            Date tahunTerbit = null;
                            try {
                                SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());
                                tahunTerbit = dateFormat.parse(tahunTerbitString);
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                            String deskripsi = object.getString("deskripsi");
                            String cover = object.getString("cover");

                            Buku buku = new Buku(id, judul, penulis, penerbit, tahunTerbit, deskripsi, cover);
                            bukuArrayList.add(buku);
                        }

                        adapter.notifyDataSetChanged();
                    } else {
                        Toast.makeText(MainView.this, "Data tidak ditemukan", Toast.LENGTH_SHORT).show();
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(MainView.this, error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        }) {
            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<>();
                params.put("searchQuery", searchQuery);
                return params;
            }
        };

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }
}