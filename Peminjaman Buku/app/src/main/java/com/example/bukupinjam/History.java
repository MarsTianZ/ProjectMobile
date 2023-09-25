package com.example.bukupinjam;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class History extends AppCompatActivity {

    private ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history2);

        listView = findViewById(R.id.listhistory);

        // Memanggil AsyncTask untuk mengambil data dari PHP
        FetchDataTask fetchDataTask = new FetchDataTask();
        fetchDataTask.execute();

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                String bookTitle = adapterView.getItemAtPosition(position).toString();
                Intent intent = new Intent(History.this, DetailHistory.class);
                intent.putExtra("bookTitle", bookTitle);
                startActivity(intent);
            }
        });
    }

    public void kembali(View view) {
        Intent intent = new Intent(this, MainView.class);
        startActivity(intent);
        finish();
    }

    private class FetchDataTask extends AsyncTask<Void, Void, List<String>> {

        @Override
        protected List<String> doInBackground(Void... voids) {
            List<String> bookTitles = new ArrayList<>();

            try {
                // Ubah URL_API sesuai dengan URL file transaksi.php yang sesuai
                String URL_API = "http://192.168.1.7/webdasar/PBM/pinjamBuku2/transaksi.php";
                URL url = new URL(URL_API);
                HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                connection.setRequestMethod("GET");

                int responseCode = connection.getResponseCode();
                if (responseCode == HttpURLConnection.HTTP_OK) {
                    BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                    StringBuilder response = new StringBuilder();
                    String line;

                    while ((line = reader.readLine()) != null) {
                        response.append(line);
                    }

                    // Parsing JSON response
                    JSONObject jsonResponse = new JSONObject(response.toString());
                    JSONArray data = jsonResponse.getJSONArray("data");

                    for (int i = 0; i < data.length(); i++) {
                        JSONObject transaction = data.getJSONObject(i);
                        String bookTitle = transaction.getString("judulbuku");
                        bookTitles.add(bookTitle);
                    }
                } else {
                    Log.e("FetchDataTask", "Error: " + responseCode);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }

            return bookTitles;
        }

        @Override
        protected void onPostExecute(List<String> bookTitles) {
            super.onPostExecute(bookTitles);

            // Mengisi data ke dalam ListView
            ArrayAdapter<String> adapter = new ArrayAdapter<>(History.this,
                    android.R.layout.simple_list_item_1, bookTitles);
            listView.setAdapter(adapter);
        }
    }
}
