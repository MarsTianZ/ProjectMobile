package com.example.uaspbm;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.squareup.picasso.Picasso;

import java.util.List;

public class MyAdapter extends ArrayAdapter<Buku> {

    Context context;
    List<Buku> arraylistBuku;

    public MyAdapter(@NonNull Context context, List<Buku> arraylistBuku) {
        super(context, R.layout.custom_list_item, arraylistBuku);

        this.context = context;
        this.arraylistBuku = arraylistBuku;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.custom_list_item, null, true);

        ImageView ivCover = view.findViewById(R.id.cover);
        TextView tvJudul = view.findViewById(R.id.judul);
        TextView tvPenulis = view.findViewById(R.id.penulis);

        // Menggunakan Picasso untuk memuat gambar dari URL atau sumber eksternal lainnya
        Picasso.get().load(arraylistBuku.get(position).getCover()).into(ivCover);

        tvJudul.setText(arraylistBuku.get(position).getJudul());
        tvPenulis.setText(arraylistBuku.get(position).getPenulis());

        return view;
    }
}
