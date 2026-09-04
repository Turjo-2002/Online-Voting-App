package com.example.onlinevotingsystem;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class EditVoterActivity
        extends AppCompatActivity {

    EditText etVoterId,
            etVoterName,
            etPhone,
            etPassword,
            etNid,
            etArea;

    Button btnSave;

    String GET_URL =
            "http://10.0.2.2/voting_api/get_single_voter.php";

    String UPDATE_URL =
            "http://10.0.2.2/voting_api/update_voter.php";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(
                R.layout.activity_edit_voter
        );

        etVoterId =
                findViewById(R.id.etVoterId);

        etVoterName =
                findViewById(R.id.etVoterName);

        etPhone =
                findViewById(R.id.etPhone);

        etPassword =
                findViewById(R.id.etPassword);

        etNid =
                findViewById(R.id.etNid);

        etArea =
                findViewById(R.id.etArea);

        btnSave =
                findViewById(R.id.btnSave);

        String voterId =
                getIntent().getStringExtra(
                        "voter_id"
                );

        loadVoter(voterId);

        btnSave.setOnClickListener(v ->
                updateVoter());
    }

    private void loadVoter(
            String voterId
    ) {

        StringRequest request =
                new StringRequest(
                        Request.Method.POST,
                        GET_URL,

                        response -> {

                            try {

                                JSONObject obj =
                                        new JSONObject(response);

                                etVoterId.setText(
                                        obj.getString(
                                                "voter_id"
                                        ));

                                etVoterName.setText(
                                        obj.getString(
                                                "voter_name"
                                        ));

                                etPhone.setText(
                                        obj.getString(
                                                "phone"
                                        ));

                                etPassword.setText(
                                        obj.getString(
                                                "password"
                                        ));

                                etNid.setText(
                                        obj.getString(
                                                "nid_number"
                                        ));

                                etArea.setText(
                                        obj.getString(
                                                "election_area"
                                        ));

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
                                "voter_id",
                                voterId
                        );

                        return params;
                    }
                };

        Volley.newRequestQueue(this)
                .add(request);
    }

    private void updateVoter() {

        StringRequest request =
                new StringRequest(
                        Request.Method.POST,
                        UPDATE_URL,

                        response -> Toast.makeText(
                                this,
                                response,
                                Toast.LENGTH_LONG
                        ).show(),

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
                                etVoterId.getText()
                                        .toString()
                        );

                        params.put(
                                "voter_name",
                                etVoterName.getText()
                                        .toString()
                        );

                        params.put(
                                "phone",
                                etPhone.getText()
                                        .toString()
                        );

                        params.put(
                                "password",
                                etPassword.getText()
                                        .toString()
                        );

                        params.put(
                                "nid_number",
                                etNid.getText()
                                        .toString()
                        );

                        params.put(
                                "election_area",
                                etArea.getText()
                                        .toString()
                        );

                        return params;
                    }
                };

        RequestQueue queue =
                Volley.newRequestQueue(this);

        queue.add(request);
    }
}