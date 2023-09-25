package com.example.bukupinjam;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.squareup.picasso.Picasso;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class DetailBuku extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_buku);

        Intent intent = getIntent();
        Buku selectedBuku = (Buku) intent.getSerializableExtra("selectedItem");

        // Mengakses data yang diterima dari buku yang dipilih
        String id = selectedBuku.getId();
        String judul = selectedBuku.getJudul();
        String penulis = selectedBuku.getPenulis();
        String penerbit = selectedBuku.getPenerbit();
        Date tahunTerbit = selectedBuku.getTahun_terbit();
        String deskripsi = selectedBuku.getDeskripsi();
        String cover = selectedBuku.getCover();

        // Gunakan data yang diterima sesuai kebutuhan
        TextView tvJudul = findViewById(R.id.judulBuku);
        TextView tvPenulis = findViewById(R.id.penulisBuku);
        TextView tvPenerbit = findViewById(R.id.penerbitBuku);
        TextView tvTahunTerbit = findViewById(R.id.tahunTerbitBuku);
        TextView tvDeskripsi = findViewById(R.id.DeskripsiBuku);
        ImageView ivCover = findViewById(R.id.coverBuku);

        tvJudul.setText(judul);
        tvPenulis.setText(penulis);
        tvPenerbit.setText(penerbit);

        // Format tanggal tahunTerbit sesuai kebutuhan
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy", Locale.getDefault());
        String tahunTerbitFormatted = dateFormat.format(tahunTerbit);
        tvTahunTerbit.setText(tahunTerbitFormatted);

        tvDeskripsi.setText(deskripsi);

        // Menggunakan Picasso untuk memuat gambar dari URL atau sumber eksternal lainnya
        Picasso.get().load(cover).into(ivCover);
    }
}
