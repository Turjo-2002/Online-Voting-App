package com.example.onlinevotingsystem;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.Request;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.HashMap;
import java.util.Map;

public class UserLoginActivity
        extends AppCompatActivity {

    EditText etNid,
            etPassword;

    Button btnLogin,
            btnRegister;

    String URL =
            "http://10.0.2.2/voting_api/user_login.php";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(
                R.layout.activity_user_login
        );

        etNid =
                findViewById(R.id.etNid);

        etPassword =
                findViewById(R.id.etPassword);

        btnLogin =
                findViewById(R.id.btnLogin);

        btnRegister =
                findViewById(R.id.btnRegister);

        btnLogin.setOnClickListener(v -> {

            String nid =
                    etNid.getText()
                            .toString()
                            .trim();

            String password =
                    etPassword.getText()
                            .toString()
                            .trim();

            if(nid.isEmpty() ||
                    password.isEmpty()){

                Toast.makeText(
                        UserLoginActivity.this,
                        "Fill all fields",
                        Toast.LENGTH_SHORT
                ).show();

                return;
            }

            StringRequest request =
                    new StringRequest(
                            Request.Method.POST,
                            URL,

                            response -> {

                                Log.d(
                                        "LOGIN_RESPONSE",
                                        response
                                );

                                response =
                                        response.trim();

                                if(response.startsWith("success|")) {

                                    String voterId =
                                            response.split("\\|")[1];

                                    Toast.makeText(
                                            UserLoginActivity.this,
                                            "Login Successful",
                                            Toast.LENGTH_SHORT
                                    ).show();

                                    Intent intent =
                                            new Intent(
                                                    UserLoginActivity.this,
                                                    ElectionAreaActivity.class
                                            );

                                    intent.putExtra(
                                            "voter_id",
                                            voterId
                                    );

                                    startActivity(intent);

                                    finish();

                                } else {

                                    Toast.makeText(
                                            UserLoginActivity.this,
                                            "Invalid Login",
                                            Toast.LENGTH_LONG
                                    ).show();
                                }
                            },

                            error -> {

                                Log.e(
                                        "LOGIN_ERROR",
                                        error.toString()
                                );

                                Toast.makeText(
                                        UserLoginActivity.this,
                                        "Error: "
                                                + error.toString(),
                                        Toast.LENGTH_LONG
                                ).show();
                            }
                    ) {

                        @Override
                        protected Map<String, String>
                        getParams() {

                            Map<String, String>
                                    params =
                                    new HashMap<>();

                            params.put(
                                    "nid_number",
                                    nid
                            );

                            params.put(
                                    "password",
                                    password
                            );

                            return params;
                        }
                    };

            Volley.newRequestQueue(
                    UserLoginActivity.this
            ).add(request);
        });

        btnRegister.setOnClickListener(v -> {

            startActivity(
                    new Intent(
                            UserLoginActivity.this,
                            RegisterActivity.class
                    )
            );
        });
    }
}