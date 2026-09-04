package com.example.onlinevotingsystem;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import com.android.volley.Request;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import java.util.HashMap;
import java.util.Map;

public class ProfileActivity extends AppCompatActivity {

    EditText etNid, etArea;
    Button btnUpdate;
    String voterId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        etNid = findViewById(R.id.etNid);
        etArea = findViewById(R.id.etArea);
        btnUpdate = findViewById(R.id.btnUpdate);

        voterId = getIntent().getStringExtra("voter_id");

        btnUpdate.setOnClickListener(v -> {

            String nid = etNid.getText().toString().trim();
            String selectedArea = etArea.getText().toString().trim();

            if(nid.isEmpty() || selectedArea.isEmpty()){
                Toast.makeText(this,"সব তথ্য দিন",Toast.LENGTH_SHORT).show();
                return;
            }

            updateProfile(nid, selectedArea);
        });
    }

    private void updateProfile(String nid, String selectedArea){

        String url = "http://10.0.2.2/voting_api/update_profile.php";

        StringRequest request = new StringRequest(Request.Method.POST, url,
                response -> {

                    if(response.trim().equals("success")){
                        Toast.makeText(this,"Profile Updated",Toast.LENGTH_SHORT).show();

                        // ⭐ Area VoteActivity তে পাঠানো হচ্ছে
                        Intent i = new Intent(ProfileActivity.this, VoteActivity.class);
                        i.putExtra("area", selectedArea);
                        startActivity(i);
                        finish();

                    }else{
                        Toast.makeText(this,"Update Failed",Toast.LENGTH_SHORT).show();
                    }

                },
                error -> Toast.makeText(this,"Server Error",Toast.LENGTH_SHORT).show()
        ){
            @Override
            protected Map<String,String> getParams(){
                Map<String,String> params = new HashMap<>();
                params.put("voter_id", voterId);
                params.put("nid_number", nid);
                params.put("election_area", selectedArea);
                return params;
            }
        };

        Volley.newRequestQueue(this).add(request);
    }
}