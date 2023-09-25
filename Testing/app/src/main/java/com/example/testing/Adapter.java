package com.example.testing;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class Adapter extends BaseAdapter {
    Context context;
    ArrayList<String> idtransaksi;
    ArrayList<String> keterangan;
    ArrayList<Integer> debit;
    ArrayList<Integer> kredit;
    LayoutInflater inflater;
    public Adapter (Context context, String idtrasaksi, int debit,  int kredit, String  keterangan){
        this.idtransaksi.add(idtrasaksi);
        this.keterangan.add(keterangan);
        this.debit.add(debit);
        this.kredit.add(kredit);
        this.context = context;
        inflater = (LayoutInflater.from(context));
    }

    @Override
    public int getCount() {
        return idtransaksi.size();
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
        view= inflater.inflate(R.layout.newlay, null);
        TextView txtnegara = (TextView) view.findViewById(R.id.tvketerangan);
        TextView icon = (TextView) view.findViewById(R.id.tvdebet);
        txtnegara.setText(keterangan.get(i));
        icon.setText(debit.get(i)+kredit.get(i));
        return view;
    }
}
