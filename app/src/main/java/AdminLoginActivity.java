package com.example.onlinevotingsystem;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class AdminLoginActivity extends AppCompatActivity {

    EditText etPhone, etUsername, etPassword;
    Button btnAdminLogin;
    TextView txtCreateAdmin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_login);

        // Initialize Views
        etPhone = findViewById(R.id.etPhone);
        etUsername = findViewById(R.id.etUsername);
        etPassword = findViewById(R.id.etPassword);
        btnAdminLogin = findViewById(R.id.btnAdminLogin);
        txtCreateAdmin = findViewById(R.id.txtCreateAdmin);

        // Create Admin Account Page
        txtCreateAdmin.setOnClickListener(v -> {

            Intent intent = new Intent(
                    AdminLoginActivity.this,
                    AdminRegisterActivity.class
            );

            startActivity(intent);
        });

        // Admin Login Button
        btnAdminLogin.setOnClickListener(v -> {

            String phone =
                    etPhone.getText().toString().trim();

            String username =
                    etUsername.getText().toString().trim();

            String password =
                    etPassword.getText().toString().trim();

            // Empty Field Check
            if (phone.isEmpty()
                    || username.isEmpty()
                    || password.isEmpty()) {

                Toast.makeText(
                        AdminLoginActivity.this,
                        "Please fill all fields",
                        Toast.LENGTH_SHORT
                ).show();

                return;
            }

            // Get Saved Admin Data
            SharedPreferences sharedPreferences =
                    getSharedPreferences(
                            "AdminData",
                            MODE_PRIVATE
                    );

            String savedPhone =
                    sharedPreferences.getString(
                            "phone",
                            ""
                    );

            String savedUsername =
                    sharedPreferences.getString(
                            "username",
                            ""
                    );

            String savedPassword =
                    sharedPreferences.getString(
                            "password",
                            ""
                    );

            // Login Check
            if (phone.equals(savedPhone)
                    && username.equals(savedUsername)
                    && password.equals(savedPassword)) {

                Toast.makeText(
                        AdminLoginActivity.this,
                        "Login Successful",
                        Toast.LENGTH_SHORT
                ).show();

                // Open Admin Dashboard
                Intent intent = new Intent(
                        AdminLoginActivity.this,
                        AdminActivity.class
                );

                startActivity(intent);
                finish();

            } else {

                Toast.makeText(
                        AdminLoginActivity.this,
                        "Invalid Login Information",
                        Toast.LENGTH_SHORT
                ).show();
            }
        });
    }
}