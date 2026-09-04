package com.example.onlinevotingsystem;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class ElectionAreaActivity extends AppCompatActivity {

    EditText etArea;
    Button btnNext, btnResult;

    String voterId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.election_area_activity);

        etArea = findViewById(R.id.etArea);
        btnNext = findViewById(R.id.btnNext);
        btnResult = findViewById(R.id.btnResult);

        voterId = getIntent().getStringExtra("voter_id");
        if (voterId == null) voterId = "";

        btnNext.setOnClickListener(v -> {

            String selectedArea = etArea.getText().toString().trim();

            if (selectedArea.isEmpty()) {
                etArea.setError("Enter Area");
                return;
            }

            Intent intent = new Intent(
                    ElectionAreaActivity.this,
                    VoteActivity.class
            );

            intent.putExtra("area", selectedArea);
            intent.putExtra("voter_id", voterId);

            startActivity(intent);
        });

      
        btnResult.setOnClickListener(v -> {
            Intent intent = new Intent(
                    ElectionAreaActivity.this,
                    ResultListActivity.class
            );
            startActivity(intent);
        });
    }
}