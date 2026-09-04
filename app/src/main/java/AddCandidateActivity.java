package com.example.onlinevotingsystem;

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

public class AddCandidateActivity extends AppCompatActivity {

    EditText etId, etName, etSymbol, etArea;
    Button btnAddCandidate;

    String ADD_URL =
            "http://10.0.2.2/voting_api/add_candidate.php";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_candidate);

        etId = findViewById(R.id.etCandidateId);
        etName = findViewById(R.id.etCandidateName);
        etSymbol = findViewById(R.id.etCandidateSymbol);
        etArea = findViewById(R.id.etCandidateArea);

        btnAddCandidate =
                findViewById(R.id.btnAddCandidate);

        btnAddCandidate.setOnClickListener(v -> {

            String id =
                    etId.getText()
                            .toString()
                            .trim();

            String name =
                    etName.getText()
                            .toString()
                            .trim();

            String symbol =
                    etSymbol.getText()
                            .toString()
                            .trim();

            String area =
                    etArea.getText()
                            .toString()
                            .trim();

            addCandidate(
                    id,
                    name,
                    symbol,
                    area
            );
        });
    }

    private void addCandidate(
            String id,
            String name,
            String symbol,
            String area
    ) {

        StringRequest request =
                new StringRequest(
                        Request.Method.POST,
                        ADD_URL,

                        response -> {

                            Toast.makeText(
                                    AddCandidateActivity.this,
                                    response,
                                    Toast.LENGTH_LONG
                            ).show();

                            Log.d(
                                    "SERVER_RESPONSE",
                                    response
                            );
                        },

                        error -> {

                            Toast.makeText(
                                    AddCandidateActivity.this,
                                    error.toString(),
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

                        params.put("id", id);
                        params.put("name", name);
                        params.put("symbol", symbol);
                        params.put("area", area);

                        return params;
                    }
                };

        RequestQueue queue =
                Volley.newRequestQueue(this);

        queue.add(request);
    }
}