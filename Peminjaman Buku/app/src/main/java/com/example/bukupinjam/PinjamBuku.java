package com.example.bukupinjam;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

public class PinjamBuku extends AppCompatActivity {

    private EditText editTextIdBuku, editTextJudulBuku, namaPeminjam, alamatPeminjam, tglPinjam, tglKembali;
    private Button btnPinjam;
    private DatePickerDialog datePickerDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pinjam_buku);

        editTextIdBuku = findViewById(R.id.idBuku);
        editTextJudulBuku = findViewById(R.id.judulBuku);
        namaPeminjam = findViewById(R.id.namaPeminjam);
        alamatPeminjam = findViewById(R.id.alamatPeminjam);
        tglPinjam = findViewById(R.id.tglPinjam);
        tglKembali = findViewById(R.id.tglKembali);
        btnPinjam = findViewById(R.id.btnPinjam);

        tglPinjam.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDatePickerDialog(tglPinjam);
            }
        });

        tglKembali.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDatePickerDialog(tglKembali);
            }
        });

        btnPinjam.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pinjamBuku();
            }
        });

        // Mendapatkan data dari Intent
        Intent intent = getIntent();
        String idBuku = intent.getStringExtra("idBuku");
        String judulBuku = intent.getStringExtra("judulBuku");

        // Menetapkan nilai pada EditText
        editTextIdBuku.setText(idBuku);
        editTextJudulBuku.setText(judulBuku);
    }

    private void showDatePickerDialog(final EditText editText) {
        Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int dayOfMonth = calendar.get(Calendar.DAY_OF_MONTH);

        datePickerDialog = new DatePickerDialog(PinjamBuku.this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                String selectedDate = year + "-" + (month + 1) + "-" + dayOfMonth;
                editText.setText(selectedDate);
            }
        }, year, month, dayOfMonth);
        datePickerDialog.show();
    }

    private String generatePeminjamanId() {
        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMddHHmm");
        return dateFormat.format(calendar.getTime());
    }

    private void pinjamBuku() {
        String url = "http://192.168.1.7/webdasar/PBM/pinjamBuku2/pinjam.php"; // Ganti dengan URL API Anda

        StringRequest stringRequest = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        // Berpindah ke halaman Struk setelah berhasil melakukan peminjaman
                        Intent intent = new Intent(PinjamBuku.this, Struk.class);
                        intent.putExtra("idPeminjaman", generatePeminjamanId());
                        intent.putExtra("idBuku", editTextIdBuku.getText().toString());
                        intent.putExtra("judulBuku", editTextJudulBuku.getText().toString());
                        intent.putExtra("namaPeminjam", namaPeminjam.getText().toString());
                        intent.putExtra("alamatPeminjam", alamatPeminjam.getText().toString());
                        intent.putExtra("tglPinjam", tglPinjam.getText().toString());
                        intent.putExtra("tglKembali", tglKembali.getText().toString());
                        startActivity(intent);
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(getApplicationContext(), "Error: " + error.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                }) {
            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<>();
                params.put("idPeminjaman", generatePeminjamanId());
                params.put("idBuku", editTextIdBuku.getText().toString());
                params.put("judulBuku", editTextJudulBuku.getText().toString());
                params.put("namaPeminjam", namaPeminjam.getText().toString());
                params.put("alamatPeminjam", alamatPeminjam.getText().toString());
                params.put("tglPinjam", tglPinjam.getText().toString());
                params.put("tglKembali", tglKembali.getText().toString());
                return params;
            }
        };

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }
}
