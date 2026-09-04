package com.example.onlinevotingsystem;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class AdminDashboardActivity
        extends AppCompatActivity {

    Button btnAdd,
            btnUpdate,
            btnDelete,
            btnViewVoters;

    @Override
    protected void onCreate(
            Bundle savedInstanceState
    ) {
        super.onCreate(savedInstanceState);

        setContentView(
                R.layout.activity_admin_dashboard
        );


        btnAdd =
                findViewById(
                        R.id.btnAddCandidate
                );

        btnUpdate =
                findViewById(
                        R.id.btnUpdateCandidate
                );

        btnDelete =
                findViewById(
                        R.id.btnDeleteCandidate
                );


        btnViewVoters =
                findViewById(
                        R.id.btn_view_voters
                );


        btnAdd.setOnClickListener(v -> {

            Toast.makeText(
                    this,
                    "Opening Add Candidate",
                    Toast.LENGTH_SHORT
            ).show();

            Intent intent =
                    new Intent(
                            AdminDashboardActivity.this,
                            AddCandidateActivity.class
                    );

            startActivity(intent);
        });


        btnUpdate.setOnClickListener(v -> {

            Intent intent =
                    new Intent(
                            AdminDashboardActivity.this,
                            UpdateCandidateActivity.class
                    );

            startActivity(intent);
        });


        btnDelete.setOnClickListener(v -> {

            Intent intent =
                    new Intent(
                            AdminDashboardActivity.this,
                            DeleteCandidateActivity.class
                    );

            startActivity(intent);
        });


        btnViewVoters.setOnClickListener(v -> {

            Intent intent =
                    new Intent(
                            AdminDashboardActivity.this,
                            ViewVotersActivity.class
                    );

            startActivity(intent);
        });
    }
}