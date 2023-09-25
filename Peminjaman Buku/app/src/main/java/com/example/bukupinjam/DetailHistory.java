package com.example.bukupinjam;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class DetailHistory extends AppCompatActivity {

    private TextView historyPeminjaman;
    private TextView historyBuku;
    private TextView historyJudul;
    private TextView historyNamaPeminjam;
    private TextView historyAlamatPeminjam;
    private TextView historyTglPinjam;
    private TextView historyTglKembali;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_history);

        historyPeminjaman = findViewById(R.id.historyPeminjaman);
        historyBuku = findViewById(R.id.historyBuku);
        historyJudul = findViewById(R.id.historyJudul);
        historyNamaPeminjam = findViewById(R.id.historyNamaPeminjam);
        historyAlamatPeminjam = findViewById(R.id.historyAlamatPeminjam);
        historyTglPinjam = findViewById(R.id.historytglPinjam);
        historyTglKembali = findViewById(R.id.historytglKembali);

        // Mendapatkan data dari intent
        Intent intent = getIntent();
        if (intent != null && intent.hasExtra("bookTitle")) {
            String bookTitle = intent.getStringExtra("bookTitle");
            loadDataFromAPI(bookTitle);
        }
    }

    private void loadDataFromAPI(String bookTitle) {
        // Mengirim request ke transaksi.php dengan parameter judulbuku
        // untuk mendapatkan data peminjaman sesuai dengan judul buku
        String URL_API = "http://192.168.1.7/webdasar/PBM/pinjamBuku2/transaksi.php?judulbuku=" + bookTitle;

        FetchDataTask fetchDataTask = new FetchDataTask();
        fetchDataTask.execute(URL_API);
    }

    public void ok(View view) {
        Intent intent = new Intent(this, History.class);
        startActivity(intent);
        finish();
    }

    private class FetchDataTask extends AsyncTask<String, Void, String> {

        @Override
        protected String doInBackground(String... urls) {
            String response = "";
            try {
                URL url = new URL(urls[0]);
                HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                connection.setRequestMethod("GET");

                int responseCode = connection.getResponseCode();
                if (responseCode == HttpURLConnection.HTTP_OK) {
                    BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                    StringBuilder stringBuilder = new StringBuilder();
                    String line;
                    while ((line = reader.readLine()) != null) {
                        stringBuilder.append(line);
                    }
                    response = stringBuilder.toString();
                } else {
                    response = "Error: " + responseCode;
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            return response;
        }

        @Override
        protected void onPostExecute(String response) {
            super.onPostExecute(response);

            try {
                JSONObject jsonResponse = new JSONObject(response);
                JSONArray data = jsonResponse.getJSONArray("data");

                if (data.length() > 0) {
                    JSONObject transaction = data.getJSONObject(0);

                    String idPeminjaman = transaction.getString("idpeminjaman");
                    String idBuku = transaction.getString("idbuku");
                    String judulBuku = transaction.getString("judulbuku");
                    String namaPeminjam = transaction.getString("namapeminjam");
                    String alamatPeminjam = transaction.getString("alamatpeminjam");
                    String tglPinjam = transaction.getString("tglpinjam");
                    String tglKembali = transaction.getString("tglkembali");

                    historyPeminjaman.setText("ID Peminjaman: " + idPeminjaman);
                    historyBuku.setText("ID Buku: " + idBuku);
                    historyJudul.setText("Judul Buku: " + judulBuku);
                    historyNamaPeminjam.setText("Nama Peminjam: " + namaPeminjam);
                    historyAlamatPeminjam.setText("Alamat Peminjam: " + alamatPeminjam);
                    historyTglPinjam.setText("Tanggal Pinjam: " + tglPinjam);
                    historyTglKembali.setText("Tanggal Kembali: " + tglKembali);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}


