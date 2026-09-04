package com.example.onlinevotingsystem;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.HashMap;
import java.util.Map;

public class RegisterActivity
        extends AppCompatActivity {

    EditText etName,
            etPhone,
            etPassword,
            etNid,
            etArea;

    Button btnRegister;

    String URL =
            "http://10.0.2.2/voting_api/register.php";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_register);

        etName =
                findViewById(R.id.etName);

        etPhone =
                findViewById(R.id.etPhone);

        etPassword =
                findViewById(R.id.etPassword);

        etNid =
                findViewById(R.id.etNid);

        etArea =
                findViewById(R.id.etArea);

        btnRegister =
                findViewById(R.id.btnRegister);

        btnRegister.setOnClickListener(v -> {

            String voterName =
                    etName.getText()
                            .toString()
                            .trim();

            String phone =
                    etPhone.getText()
                            .toString()
                            .trim();

            String password =
                    etPassword.getText()
                            .toString()
                            .trim();

            String nid =
                    etNid.getText()
                            .toString()
                            .trim();

            String area =
                    etArea.getText()
                            .toString()
                            .trim();


            if (
                    voterName.isEmpty() ||
                            phone.isEmpty() ||
                            password.isEmpty() ||
                            nid.isEmpty() ||
                            area.isEmpty()
            ) {

                Toast.makeText(
                        RegisterActivity.this,
                        "Please fill all fields",
                        Toast.LENGTH_SHORT
                ).show();

                return;
            }

            Toast.makeText(
                    this,
                    "Button Clicked",
                    Toast.LENGTH_SHORT
            ).show();

            Log.d("REGISTER", voterName);
            Log.d("REGISTER", phone);
            Log.d("REGISTER", password);
            Log.d("REGISTER", nid);
            Log.d("REGISTER", area);

            StringRequest request =
                    new StringRequest(
                            Request.Method.POST,
                            URL,

                            response -> {

                                String serverResponse =
                                        response.trim();

                                Log.d(
                                        "SERVER_RESPONSE",
                                        serverResponse
                                );

                                Toast.makeText(
                                        RegisterActivity.this,
                                        serverResponse,
                                        Toast.LENGTH_LONG
                                ).show();


                                if (
                                        serverResponse.equals(
                                                "success"
                                        )
                                ) {

                                    Toast.makeText(
                                            RegisterActivity.this,
                                            "Registration Successful",
                                            Toast.LENGTH_SHORT
                                    ).show();

                                    startActivity(
                                            new Intent(
                                                    RegisterActivity.this,
                                                    UserLoginActivity.class
                                            )
                                    );

                                    finish();
                                }

                                // Already exists
                                else if (
                                        serverResponse.equals(
                                                "already_exists"
                                        )
                                ) {

                                    Toast.makeText(
                                            RegisterActivity.this,
                                            "Phone or NID already exists",
                                            Toast.LENGTH_LONG
                                    ).show();
                                }


                                else if (
                                        serverResponse.equals(
                                                "empty_fields"
                                        )
                                ) {

                                    Toast.makeText(
                                            RegisterActivity.this,
                                            "Please fill all fields",
                                            Toast.LENGTH_LONG
                                    ).show();
                                }
                            },

                            error -> {

                                Toast.makeText(
                                        RegisterActivity.this,
                                        error.toString(),
                                        Toast.LENGTH_LONG
                                ).show();

                                Log.e(
                                        "VOLLEY_ERROR",
                                        error.toString()
                                );
                            }
                    ) {

                        @Override
                        protected Map<String, String>
                        getParams() {

                            Map<String, String>
                                    params =
                                    new HashMap<>();

                            params.put(
                                    "voter_name",
                                    voterName
                            );

                            params.put(
                                    "phone",
                                    phone
                            );

                            params.put(
                                    "password",
                                    password
                            );

                            params.put(
                                    "nid_number",
                                    nid
                            );

                            params.put(
                                    "election_area",
                                    area
                            );

                            return params;
                        }
                    };

            RequestQueue queue =
                    Volley.newRequestQueue(
                            RegisterActivity.this
                    );

            queue.add(request);
        });
    }
}