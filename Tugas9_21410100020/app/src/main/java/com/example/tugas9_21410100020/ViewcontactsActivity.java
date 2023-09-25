package com.example.tugas9_21410100020;

import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class ViewcontactsActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_contacts);

        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setHasFixedSize(true);

        DatabaseHelperClass databaseHelperClass = new DatabaseHelperClass(this);
        List<contactsModelClass> contactsModelClasses = databaseHelperClass.getcontactsList();

        if (contactsModelClasses.size() > 0){
            contactsAdapterClass employeadapterclass = new contactsAdapterClass(contactsModelClasses,ViewcontactsActivity.this);
            recyclerView.setAdapter(employeadapterclass);
        }else {
            Toast.makeText(this, "There is no contacts in the database", Toast.LENGTH_SHORT).show();
        }




    }
}
