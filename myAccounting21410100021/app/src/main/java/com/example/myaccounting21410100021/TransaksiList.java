package com.example.myaccounting21410100021;

import android.content.Context;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.RequiresApi;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class TransaksiList extends BaseAdapter {
    Context context;
    ArrayList<Transaksi>listTrx;
    LayoutInflater layoutInflater;

    SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");

    public TransaksiList() {
    }

    public TransaksiList(Context context, ArrayList<Transaksi> listTransaksi) {
        listTrx=listTransaksi;
        layoutInflater = (LayoutInflater.from(context));
    }

    @Override
    public int getCount() {
        return listTrx.size();
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        view = layoutInflater.inflate(R.layout.layout_item_trans, null);
        TextView tglTransaksi = (TextView) view.findViewById(R.id.listItem_tanggal);
        TextView ketTransaksi = (TextView) view.findViewById(R.id.listItem_keterangan);
        TextView hrgTransaksi = (TextView) view.findViewById(R.id.listItem_harga);
        ImageView logoTransaksi = (ImageView) view.findViewById(R.id.listImg_Transaksi);
        Button btnHapus=(Button)view.findViewById(R.id.hapusTransaksi);
        String curDate = sdf.format(listTrx.get(i).getTanggalTransaksi());
        tglTransaksi.setText(curDate);
        ketTransaksi.setText(listTrx.get(i).getUraian());
        logoTransaksi.setImageResource(listTrx.get(i).getLogo());
        if (listTrx.get(i).getDebit() != 0) {
            hrgTransaksi.setText(String.valueOf(listTrx.get(i).getDebit()));
        } else {
            hrgTransaksi.setText(String.valueOf(listTrx.get(i).getKredit()));
        }
        btnHapus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listTrx.remove(i);
                notifyDataSetChanged();
            }
        });

        return view;
    }
}
