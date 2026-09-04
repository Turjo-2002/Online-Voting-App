package com.example.onlinevotingsystem;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONObject;

import java.util.ArrayList;

public class ViewVotersActivity
        extends AppCompatActivity {

    EditText etSearchVoter;
    Button btnSearch;
    ListView listVoters;

    ArrayList<String> voterList;
    ArrayAdapter<String> adapter;

    String URL =
            "http://10.0.2.2/voting_api/get_voters.php";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(
                R.layout.activity_view_voters
        );

        etSearchVoter =
                findViewById(
                        R.id.etSearchVoter
                );

        btnSearch =
                findViewById(
                        R.id.btnSearch
                );

        listVoters =
                findViewById(
                        R.id.listVoters
                );

        voterList =
                new ArrayList<>();

        adapter =
                new ArrayAdapter<>(
                        this,
                        android.R.layout.simple_list_item_1,
                        voterList
                );

        listVoters.setAdapter(adapter);

        loadVoters();


        btnSearch.setOnClickListener(v -> {

            String voterId =
                    etSearchVoter
                            .getText()
                            .toString()
                            .trim();

            if (!voterId.isEmpty()) {

                Intent intent =
                        new Intent(
                                ViewVotersActivity.this,
                                EditVoterActivity.class
                        );

                intent.putExtra(
                        "voter_id",
                        voterId
                );

                startActivity(intent);
            }
        });


        listVoters.setOnItemClickListener(
                (parent, view, position, id) -> {

                    String selected =
                            voterList.get(position);

                    String voterId =
                            selected.split(" - ")[0];

                    Intent intent =
                            new Intent(
                                    ViewVotersActivity.this,
                                    EditVoterActivity.class
                            );

                    intent.putExtra(
                            "voter_id",
                            voterId
                    );

                    startActivity(intent);
                });
    }

    private void loadVoters() {

        JsonArrayRequest request =
                new JsonArrayRequest(
                        Request.Method.GET,
                        URL,
                        null,

                        response -> {

                            voterList.clear();

                            for (int i = 0;
                                 i < response.length();
                                 i++) {

                                try {

                                    JSONObject object =
                                            response
                                                    .getJSONObject(i);

                                    String voterId =
                                            object.getString(
                                                    "voter_id"
                                            );

                                    String voterName =
                                            object.getString(
                                                    "voter_name"
                                            );

                                    voterList.add(
                                            voterId
                                                    + " - "
                                                    + voterName
                                    );

                                } catch (Exception e) {

                                    Toast.makeText(
                                            this,
                                            e.toString(),
                                            Toast.LENGTH_SHORT
                                    ).show();
                                }
                            }

                            adapter.notifyDataSetChanged();

                        },

                        error -> Toast.makeText(
                                this,
                                error.toString(),
                                Toast.LENGTH_LONG
                        ).show()
                );

        RequestQueue queue =
                Volley.newRequestQueue(this);

        queue.add(request);
    }
}