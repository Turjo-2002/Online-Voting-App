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

public class ResultListActivity extends AppCompatActivity {

    RecyclerView recyclerView;

    ArrayList<Candidate> resultList;

    CandidateAdapter adapter;

    String URL =
            "http://10.0.2.2/voting_api/get_result.php";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
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

        resultList =
                new ArrayList<>();

        adapter =
                new CandidateAdapter(
                        this,
                        resultList,
                        candidateId -> {

                        }
                );

        recyclerView.setAdapter(adapter);

        loadResults();
    }

    private void loadResults() {

        StringRequest request =
                new StringRequest(
                        Request.Method.GET,
                        URL,

                        response -> {

                            response =
                                    response.trim();

                            if (
                                    response.equals(
                                            "not_published"
                                    )
                            ) {

                                Toast.makeText(
                                        ResultListActivity.this,
                                        "Result Not Published Yet",
                                        Toast.LENGTH_LONG
                                ).show();

                                return;
                            }

                            try {

                                resultList.clear();

                                JSONArray array =
                                        new JSONArray(
                                                response
                                        );

                                for (
                                        int i = 0;
                                        i < array.length();
                                        i++
                                ) {

                                    JSONObject obj =
                                            array.getJSONObject(
                                                    i
                                            );

                                    resultList.add(
                                            new Candidate(
                                                    "",
                                                    obj.getString(
                                                            "name"
                                                    ),
                                                    obj.getString(
                                                            "symbol"
                                                    ),
                                                    obj.getString(
                                                            "votes"
                                                    )
                                            )
                                    );
                                }

                                adapter.notifyDataSetChanged();

                            } catch (Exception e) {

                                Toast.makeText(
                                        ResultListActivity.this,
                                        e.toString(),
                                        Toast.LENGTH_LONG
                                ).show();
                            }
                        },

                        error -> Toast.makeText(
                                ResultListActivity.this,
                                error.toString(),
                                Toast.LENGTH_LONG
                        ).show()
                );

        Volley.newRequestQueue(
                ResultListActivity.this
        ).add(request);
    }
}