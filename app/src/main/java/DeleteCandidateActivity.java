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

public class DeleteCandidateActivity
        extends AppCompatActivity {

    EditText etId,
            etName,
            etSymbol,
            etArea;

    Button btnLoad,
            btnDelete;

    String GET_URL =
            "http://10.0.2.2/voting_api/get_candidate.php";

    String DELETE_URL =
            "http://10.0.2.2/voting_api/delete_candidate.php";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(
                R.layout.activity_delete_candidate
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

        btnDelete =
                findViewById(R.id.btnDelete);


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


        btnDelete.setOnClickListener(v -> {

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

            deleteCandidate(id);
        });
    }


    private void loadCandidate(
            String id
    ){

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

                            } catch (Exception e){

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
                    getParams(){

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


    private void deleteCandidate(
            String id
    ){

        StringRequest request =
                new StringRequest(
                        Request.Method.POST,
                        DELETE_URL,

                        response -> {

                            Toast.makeText(
                                    this,
                                    response,
                                    Toast.LENGTH_SHORT
                            ).show();


                            etId.setText("");
                            etName.setText("");
                            etSymbol.setText("");
                            etArea.setText("");
                        },

                        error -> Toast.makeText(
                                this,
                                error.toString(),
                                Toast.LENGTH_LONG
                        ).show()

                ){

                    @Override
                    protected Map<String, String>
                    getParams(){

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
}

