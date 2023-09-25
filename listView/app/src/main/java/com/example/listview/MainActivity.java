package com.example.listview;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class MainActivity extends AppCompatActivity {
    private ListView myListView;
    private static final String DAFTAR_OS[] = {"Windows 10", "Ubuntu", "Android", "Mac", "iOS", "Windows Mobile", "Windows 7"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        myListView = (ListView) findViewById(R.id.listView);
        ArrayAdapter<String> myAdapter = new ArrayAdapter<String>(this, R.layout.listview_item, R.id.textview, DAFTAR_OS);
        myListView.setAdapter(myAdapter);
    }
}