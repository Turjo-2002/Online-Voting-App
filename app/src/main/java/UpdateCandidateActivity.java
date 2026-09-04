
        package com.example.onlinevotingsystem;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.Request;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class UpdateCandidateActivity
        extends AppCompatActivity {

    EditText etId,
            etName,
            etSymbol,
            etArea;

    Button btnLoad,
            btnUpdate;

    String GET_URL =
            "http://10.0.2.2/voting_api/get_candidate.php";

    String UPDATE_URL =
            "http://10.0.2.2/voting_api/update_candidate.php";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(
                R.layout.activity_update_candidate
        );

        etId =
                findViewById(R.id.etId);

        etName =
                findViewById(R.id.etName);

        etSymbol =
                findViewById(R.id.etSymbol);

        etArea =
                findViewById(R.id.etArea);

        btnLoad =
                findViewById(R.id.btnLoad);

        btnUpdate =
                findViewById(R.id.btnUpdate);


        btnLoad.setOnClickListener(v -> {

            String id =
                    etId.getText()
                            .toString()
                            .trim();

            if(id.isEmpty()){

                etId.setError(
                        "Enter Candidate ID"
                );

                return;
            }

            loadCandidate(id);
        });


        btnUpdate.setOnClickListener(v -> {

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

            if(
                    id.isEmpty() ||
                            name.isEmpty() ||
                            symbol.isEmpty() ||
                            area.isEmpty()
            ){

                Toast.makeText(
                        this,
                        "Fill all fields",
                        Toast.LENGTH_SHORT
                ).show();

                return;
            }

            updateCandidate(
                    id,
                    name,
                    symbol,
                    area
            );
        });
    }


    private void loadCandidate(
            String id
    ) {

        StringRequest request =
                new StringRequest(
                        Request.Method.POST,
                        GET_URL,

                        response -> {

                            try {

                                response =
                                        response.trim();

                                if(response.equals(
                                        "not_found"
                                )){

                                    Toast.makeText(
                                            this,
                                            "Candidate Not Found",
                                            Toast.LENGTH_SHORT
                                    ).show();

                                    return;
                                }

                                JSONObject object =
                                        new JSONObject(
                                                response
                                        );

                                etName.setText(
                                        object.getString(
                                                "candidate_name"
                                        )
                                );

                                // party load
                                etSymbol.setText(
                                        object.getString(
                                                "party"
                                        )
                                );

                                etArea.setText(
                                        object.getString(
                                                "election_area"
                                        )
                                );

                                Toast.makeText(
                                        this,
                                        "Candidate Loaded",
                                        Toast.LENGTH_SHORT
                                ).show();

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

                ){

                    @Override
                    protected Map<String, String>
                    getParams() {

                        Map<String, String>
                                params =
                                new HashMap<>();

                        params.put(
                                "id",
                                id
                        );

                        return params;
                    }
                };

        Volley.newRequestQueue(this)
                .add(request);
    }


    private void updateCandidate(
            String id,
            String name,
            String symbol,
            String area
    ) {

        StringRequest request =
                new StringRequest(
                        Request.Method.POST,
                        UPDATE_URL,

                        response -> {

                            Toast.makeText(
                                    this,
                                    response,
                                    Toast.LENGTH_SHORT
                            ).show();
                        },

                        error -> Toast.makeText(
                                this,
                                error.toString(),
                                Toast.LENGTH_LONG
                        ).show()

                ){

                    @Override
                    protected Map<String, String>
                    getParams() {

                        Map<String, String>
                                params =
                                new HashMap<>();

                        params.put(
                                "id",
                                id
                        );

                        params.put(
                                "name",
                                name
                        );


                        params.put(
                                "symbol",
                                symbol
                        );

                        params.put(
                                "area",
                                area
                        );

                        return params;
                    }
                };

        Volley.newRequestQueue(this)
                .add(request);
    }
}

