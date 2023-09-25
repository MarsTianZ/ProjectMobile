package com.example.sqlitte;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    EditText editText_name,editText_noHP;
    Button button_add,button_view;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editText_name = findViewById(R.id.edittext_name);
        editText_noHP = findViewById(R.id.edittext_noHP);
        button_add = findViewById(R.id.button_add);
        button_view = findViewById(R.id.button_view);


        button_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String stringName = editText_name.getText().toString();
                String stringnoHP = editText_noHP.getText().toString();

                if (stringName.length() <=0 || stringnoHP.length() <=0){
                    Toast.makeText(MainActivity.this, "Enter All Data", Toast.LENGTH_SHORT).show();
                }else {
                    Database database = new Database(MainActivity.this);
                    ModelContacts modelContacts = new ModelContacts(stringName,stringnoHP);
                    database.addcontacts(modelContacts);
                    Toast.makeText(MainActivity.this, "Add contacts Successfully", Toast.LENGTH_SHORT).show();
                    finish();
                    startActivity(getIntent());
                }
            }
        });


        button_view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,ViewContacts.class);
                startActivity(intent);
            }
        });


    }
}