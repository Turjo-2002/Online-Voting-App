package com.example.onlinevotingsystem;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class LoginActivity extends AppCompatActivity {

    Button btnUser, btnAdmin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);



        btnUser = findViewById(R.id.btn_user);
        btnAdmin = findViewById(R.id.btn_admin);


        btnUser.setOnClickListener(v -> {
            Intent intent =
                    new Intent(LoginActivity.this, UserLoginActivity.class);
            startActivity(intent);
        });


        btnAdmin.setOnClickListener(v -> {
            Intent intent =
                    new Intent(LoginActivity.this, AdminLoginActivity.class);
            startActivity(intent);
        });
    }
}