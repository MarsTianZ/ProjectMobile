package com.example.sqlitte;

import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class ViewContacts extends AppCompatActivity {
    RecyclerView recyclerView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.view_contacts);

        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setHasFixedSize(true);

        Database database = new Database(this);
        List<ModelContacts> ModelContacts = database.getcontactsList();

        if (ModelContacts.size() > 0){
            AdapterContacts employeadapterclass = new AdapterContacts(ModelContacts,ViewContacts.this);
            recyclerView.setAdapter(employeadapterclass);
        }else {
            Toast.makeText(this, "There is no contacts in the database", Toast.LENGTH_SHORT).show();
        }
    }
}
