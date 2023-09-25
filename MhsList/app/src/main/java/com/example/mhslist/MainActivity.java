package com.example.mhslist;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

public class MainActivity extends AppCompatActivity {
    Button btn;
    String arrMhs[];
    ListView lsMhs;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        arrMhs=getResources().getStringArray(R.array.arr_mhs);
        ArrayAdapter<String> adapter=new ArrayAdapter<String>(this,R.layout.item_lv,R.id.txtItem,arrMhs);
        btn=(Button)findViewById(R.id.btnTampil);
        lsMhs=(ListView) findViewById(R.id.lstMhs);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                lsMhs.setAdapter(adapter);
            }
        });

    }
}