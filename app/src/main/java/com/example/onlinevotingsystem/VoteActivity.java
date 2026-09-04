
 package com.example.onlinevotingsystem;

import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.Request;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;


public class VoteActivity extends AppCompatActivity {

    RecyclerView recyclerView;

    CandidateAdapter adapter;
    ArrayList<Candidate> candidateList;

    String area;
    String voterId;

    String URL =
            "http://10.0.2.2/voting_api/get_candidate_by_area.php";

    String VOTE_URL =
            "http://10.0.2.2/voting_api/vote_candidate.php";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vote);

        recyclerView =
                findViewById(R.id.recyclerCandidates);

        recyclerView.setLayoutManager(
                new LinearLayoutManager(this)
        );

        candidateList =
                new ArrayList<>();

        adapter =
                new CandidateAdapter(
                        this,
                        candidateList,
                        candidateId ->
                                voteCandidate(candidateId)
                );

        recyclerView.setAdapter(adapter);

        area =
                getIntent()
                        .getStringExtra("area");

        voterId =
                getIntent()
                        .getStringExtra("voter_id");

        if(area == null){
            area = "";
        }

        if(voterId == null){
            voterId = "";
        }

        loadCandidates();
    }

    private void loadCandidates() {

        StringRequest request =
                new StringRequest(
                        Request.Method.POST,
                        URL,

                        response -> {

                            try {

                                candidateList.clear();

                                JSONArray array =
                                        new JSONArray(response);

                                for (
                                        int i = 0;
                                        i < array.length();
                                        i++
                                ) {

                                    JSONObject object =
                                            array.getJSONObject(i);

                                    candidateList.add(
                                            new Candidate(
                                                    object.getString("id"),
                                                    object.getString("candidate_name"),
                                                    object.getString("party"),
                                                    object.getString("election_area")
                                            )
                                    );
                                }

                                adapter.notifyDataSetChanged();

                            } catch (Exception e) {

                                Toast.makeText(
                                        this,
                                        e.toString(),
                                        Toast.LENGTH_LONG
                                ).show();
                            }
                        },

                        error -> Toast.makeText(
                                this,
                                error.toString(),
                                Toast.LENGTH_LONG
                        ).show()
                ) {

                    @Override
                    protected Map<String, String>
                    getParams() {

                        Map<String, String>
                                params =
                                new HashMap<>();

                        params.put(
                                "election_area",
                                area
                        );

                        return params;
                    }
                };

        Volley.newRequestQueue(this)
                .add(request);
    }

    private void voteCandidate(
            String candidateId
    ) {

        StringRequest request =
                new StringRequest(
                        Request.Method.POST,
                        VOTE_URL,

                        response -> {

                            response =
                                    response.trim();

                            Toast.makeText(
                                    this,
                                    response,
                                    Toast.LENGTH_LONG
                            ).show();
                        },

                        error -> Toast.makeText(
                                this,
                                error.toString(),
                                Toast.LENGTH_LONG
                        ).show()
                ) {

                    @Override
                    protected Map<String, String>
                    getParams() {

                        Map<String, String>
                                params =
                                new HashMap<>();

                        params.put(
                                "voter_id",
                                voterId
                        );

                        params.put(
                                "candidate_id",
                                candidateId
                        );

                        return params;
                    }
                };

        Volley.newRequestQueue(this)
                .add(request);
    }
}

