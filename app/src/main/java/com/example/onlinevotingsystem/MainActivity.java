package com.example.onlinevotingsystem;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    EditText etVoterId, etPassword;
    Button btnLogin;
    TextView tvCreateAccount;

    // Laragon use korle localhost er bodole 10.0.2.2 use korte hoy
    String LOGIN_URL = "http://10.0.2.2/voting_api/login.php";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // XML theke ID gulo find kora
        etVoterId = findViewById(R.id.regVoterId);
        etPassword = findViewById(R.id.regPassword);
        btnLogin = findViewById(R.id.loginBtn);
        tvCreateAccount = findViewById(R.id.tvRegisterLink);

        // Login Button Click Listener
        btnLogin.setOnClickListener(v -> {
            loginUser();
        });

        // Register Page-e jaoar link
        tvCreateAccount.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, RegisterActivity.class);
            startActivity(intent);
        });
    }

    private void loginUser() {
        final String voterId = etVoterId.getText().toString().trim();
        final String password = etPassword.getText().toString().trim();

        if (voterId.isEmpty() || password.isEmpty()) {
            Toast.makeText(this, "Please fill all fields", Toast.LENGTH_SHORT).show();
            return;
        }

        StringRequest stringRequest = new StringRequest(Request.Method.POST, LOGIN_URL,
                response -> {
                    // Response theke space muche check kora
                    if (response.trim().equals("success")) {
                        Toast.makeText(MainActivity.this, "Login Successful!", Toast.LENGTH_SHORT).show();

                        // --- EIKHANE CHANGE KORA HOYECHE ---
                        // ProfileActivity-te jaoar logic
                        Intent intent = new Intent(MainActivity.this, ProfileActivity.class);
                        // Voter ID pathiye deya jate Profile page-e update kora jay
                        intent.putExtra("voter_id", voterId);
                        startActivity(intent);
                        finish(); // Jate back chaple abar login-e na ashe

                    } else {
                        Toast.makeText(MainActivity.this, "Invalid ID or Password", Toast.LENGTH_SHORT).show();
                    }
                },
                error -> {
                    Toast.makeText(MainActivity.this, "Error: Check Server Connection", Toast.LENGTH_LONG).show();
                }) {
            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<>();
                params.put("voter_id", voterId);
                params.put("password", password);
                return params;
            }
        };

        RequestQueue queue = Volley.newRequestQueue(this);
        queue.add(stringRequest);
    }
}