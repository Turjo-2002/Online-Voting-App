package com.example.onlinevotingsystem;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.Request;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

public class AdminActivity extends AppCompatActivity {

        Button btnAdd;
        Button btnUpdate;
        Button btnDelete;
        Button btnViewVoters;
        Button btnPublishResult;
        Button btnPreviewResult;

        String PUBLISH_URL =
                "http://10.0.2.2/voting_api/publish_result.php";

        @Override
        protected void onCreate(Bundle savedInstanceState) {
                super.onCreate(savedInstanceState);
                setContentView(R.layout.activity_admin);

                btnAdd = findViewById(R.id.btnAddCandidate);
                btnUpdate = findViewById(R.id.btnUpdateCandidate);
                btnDelete = findViewById(R.id.btnDeleteCandidate);
                btnViewVoters = findViewById(R.id.btn_view_voters);
                btnPublishResult = findViewById(R.id.btnPublishResult);
                btnPreviewResult = findViewById(R.id.btnPreviewResult);


                btnAdd.setOnClickListener(v -> {
                        startActivity(
                                new Intent(
                                        AdminActivity.this,
                                        AddCandidateActivity.class
                                )
                        );
                });


                btnUpdate.setOnClickListener(v -> {
                        startActivity(
                                new Intent(
                                        AdminActivity.this,
                                        UpdateCandidateActivity.class
                                )
                        );
                });


                btnDelete.setOnClickListener(v -> {
                        startActivity(
                                new Intent(
                                        AdminActivity.this,
                                        DeleteCandidateActivity.class
                                )
                        );
                });


                btnViewVoters.setOnClickListener(v -> {
                        startActivity(
                                new Intent(
                                        AdminActivity.this,
                                        ViewVotersActivity.class
                                )
                        );
                });


                btnPreviewResult.setOnClickListener(v -> {
                        startActivity(
                                new Intent(
                                        AdminActivity.this,
                                        AdminResultPreviewActivity.class
                                )
                        );
                });


                btnPublishResult.setOnClickListener(v -> {

                        StringRequest request =
                                new StringRequest(
                                        Request.Method.GET,
                                        PUBLISH_URL,

                                        response -> {

                                                response = response.trim();

                                                if (response.equals("success")) {

                                                        Toast.makeText(
                                                                AdminActivity.this,
                                                                "Result Published Successfully",
                                                                Toast.LENGTH_LONG
                                                        ).show();

                                                } else {

                                                        Toast.makeText(
                                                                AdminActivity.this,
                                                                "Publish Failed",
                                                                Toast.LENGTH_LONG
                                                        ).show();
                                                }
                                        },

                                        error -> Toast.makeText(
                                                AdminActivity.this,
                                                error.toString(),
                                                Toast.LENGTH_LONG
                                        ).show()
                                );

                        Volley.newRequestQueue(
                                AdminActivity.this
                        ).add(request);
                });
        }
}