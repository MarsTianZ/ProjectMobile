package com.example.lvnegara;

import android.content.Context;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class NegaraAdapater extends BaseAdapter {
  Context context;
  String negaraList[];
  int bendaraList[];
  LayoutInflater inflater;
  public NegaraAdapater (Context context, String[] negaraList, int[] bendaraList){
      this.bendaraList=bendaraList;
      this.negaraList=negaraList;
      this.context = context;
      inflater = (LayoutInflater.from(context));
  }

    @Override
    public int getCount() {
        return negaraList.length;
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
    public View getView(int i, View convertView, ViewGroup viewGroup) {
      convertView= inflater.inflate(R.layout.layout_item,null);
        TextView txtnegara = (TextView) convertView.findViewById(R.id.txtnegara);
        ImageView icon = (ImageView) convertView.findViewById(R.id.imgNegara);
        txtnegara.setText(negaraList[i]);
        icon.setImageResource(bendaraList[i]);
        return convertView;
    }
}
