package com.example.bukupinjam;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

public class HistoryList extends ArrayAdapter<String> {

    private Context context;
    private List<String> judulBukuList;

    public HistoryList(Context context, List<String> judulBukuList) {
        super(context, 0, judulBukuList);
        this.context = context;
        this.judulBukuList = judulBukuList;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View listItemView = convertView;
        if (listItemView == null) {
            listItemView = LayoutInflater.from(context).inflate(R.layout.activity_history_list, parent, false);
        }

        String judulBuku = judulBukuList.get(position);

        TextView judulBukuTextView = listItemView.findViewById(R.id.text_judul_buku);
        judulBukuTextView.setText(judulBuku);

        return listItemView;
    }
}