package com.example.bukupinjam;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class Struk extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_struk);

        // Mendapatkan data dari Intent
        String idPeminjaman = getIntent().getStringExtra("idPeminjaman");
        String idBuku = getIntent().getStringExtra("idBuku");
        String judulBuku = getIntent().getStringExtra("judulBuku");
        String namaPeminjam = getIntent().getStringExtra("namaPeminjam");
        String alamatPeminjam = getIntent().getStringExtra("alamatPeminjam");
        String tglPinjam = getIntent().getStringExtra("tglPinjam");
        String tglKembali = getIntent().getStringExtra("tglKembali");

        // Menampilkan data pada TextView di halaman Struk
        TextView tvIdPeminjaman = findViewById(R.id.idPeminjaman);
        TextView tvIdBuku = findViewById(R.id.idBuku);
        TextView tvJudulBuku = findViewById(R.id.judulBuku);
        TextView tvNamaPeminjam = findViewById(R.id.namaPeminjam);
        TextView tvAlamatPeminjam = findViewById(R.id.alamatPeminjam);
        TextView tvTglPinjam = findViewById(R.id.tglPinjam);
        TextView tvTglKembali = findViewById(R.id.tglKembali);

        tvIdPeminjaman.setText(idPeminjaman);
        tvIdBuku.setText(idBuku);
        tvJudulBuku.setText(judulBuku);
        tvNamaPeminjam.setText(namaPeminjam);
        tvAlamatPeminjam.setText(alamatPeminjam);
        tvTglPinjam.setText(tglPinjam);
        tvTglKembali.setText(tglKembali);
    }

    public void konfirmasi (View view){
        Intent intent = new Intent(this, MainView.class);
        startActivity(intent);
        finish();
    }
}

