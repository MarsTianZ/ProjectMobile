package com.example.lvnegara;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ListView;

public class MainActivity extends AppCompatActivity {
    ListView listView;
    String namaNegara[] = {"Indonesia", "jepang", "Malaysia", "Mezir", "Palestina", "Sudan", "Taiwan", "Turkie", "Vietnam"};
    int benderaNegara [] = {R.drawable.indonesia, R.drawable.jepang, R.drawable.malaysia, R.drawable.mesir, R.drawable.palestina, R.drawable.singapura, R.drawable.sudan, R.drawable.taiwan, R.drawable.turkey, R.drawable.vietnam};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        listView = (ListView) findViewById(R.id.lvtgs);
        NegaraAdapater Na = new NegaraAdapater(getApplicationContext(),namaNegara, benderaNegara);
        listView.setAdapter(Na);
    }
}