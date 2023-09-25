package com.example.formlogin;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private Button btnLogin;
    private EditText txtUname, txtpass;

    public int maksLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        txtUname=findViewById(R.id.txtname);
        txtpass=findViewById(R.id.txtpass);
        btnLogin=findViewById(R.id.btn_login);
        maksLogin=0;

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String Uname = txtUname.getText().toString();
                String pass = txtpass.getText().toString();

                if(Uname.equals("admin") && pass.equals("1234")) {
                    Toast.makeText(MainActivity.this, "Login Sukses", Toast.LENGTH_SHORT).show();
                } else {
                    maksLogin++;
                    Toast.makeText(MainActivity.this, "Login Gagal", Toast.LENGTH_SHORT).show();
                }

                if(maksLogin ==3) {
                    txtUname.setEnabled(false);
                    txtpass.setEnabled(false);
                    Toast.makeText(MainActivity.this, "Login telah mencapai batas", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}