package com.example.onlinevotingsystem;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class AdminRegisterActivity extends AppCompatActivity {

    EditText etPhone, etUsername, etPassword;
    Button btnCreate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_register);

        etPhone = findViewById(R.id.etPhone);
        etUsername = findViewById(R.id.etUsername);
        etPassword = findViewById(R.id.etPassword);
        btnCreate = findViewById(R.id.btnCreate);

        btnCreate.setOnClickListener(v -> {

            String phone =
                    etPhone.getText().toString().trim();

            String username =
                    etUsername.getText().toString().trim();

            String password =
                    etPassword.getText().toString().trim();


            if (phone.isEmpty()
                    || username.isEmpty()
                    || password.isEmpty()) {

                Toast.makeText(
                        this,
                        "Please fill all fields",
                        Toast.LENGTH_SHORT
                ).show();

                return;
            }


            SharedPreferences sharedPreferences =
                    getSharedPreferences(
                            "AdminData",
                            MODE_PRIVATE
                    );

            SharedPreferences.Editor editor =
                    sharedPreferences.edit();

            editor.putString("phone", phone);
            editor.putString("username", username);
            editor.putString("password", password);

            editor.apply();

            Toast.makeText(
                    this,
                    "Admin Account Created Successfully",
                    Toast.LENGTH_SHORT
            ).show();

            finish();
        });
    }
}