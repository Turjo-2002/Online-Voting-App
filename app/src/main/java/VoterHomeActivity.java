package com.example.onlinevotingsystem;

import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class VoterHomeActivity
        extends AppCompatActivity {

    RecyclerView recyclerView;

    ArrayList<Candidate> list;
    CandidateAdapter adapter;

    @Override
    protected void onCreate(
            Bundle savedInstanceState
    ) {
        super.onCreate(savedInstanceState);

        setContentView(
                R.layout.activity_vote
        );

        recyclerView =
                findViewById(
                        R.id.recyclerCandidates
                );

        recyclerView.setLayoutManager(
                new LinearLayoutManager(this)
        );

        list =
                new ArrayList<>();


        adapter =
                new CandidateAdapter(
                        this,
                        list,
                        candidateId -> {

                            Toast.makeText(
                                    this,
                                    "Selected Candidate ID: "
                                            + candidateId,
                                    Toast.LENGTH_SHORT
                            ).show();
                        }
                );

        recyclerView.setAdapter(
                adapter
        );


        list.add(
                new Candidate(
                        "1",
                        "Candidate 1",
                        "Party 1",
                        "Dhaka"
                )
        );

        list.add(
                new Candidate(
                        "2",
                        "Candidate 2",
                        "Party 2",
                        "Dhaka"
                )
        );

        list.add(
                new Candidate(
                        "3",
                        "Candidate 3",
                        "Party 3",
                        "Dhaka"
                )
        );

        adapter.notifyDataSetChanged();
    }
}