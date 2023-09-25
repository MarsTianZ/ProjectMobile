package com.example.viewbarang;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    static ListView mListView;
    static Context mContext;
    static TextView mTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mListView = (ListView) findViewById(R.id.listview);
//        mTextView = (TextView) findViewById(R.id.ini);
        mContext = getApplicationContext();

        data getData = new data();
        getData.execute();
    }
}