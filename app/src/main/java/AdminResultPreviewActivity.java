package com.example.onlinevotingsystem;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.Request;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

public class AdminResultPreviewActivity
        extends AppCompatActivity {

    RecyclerView recyclerView;

    ArrayList<AdminResultModel> list;

    AdminResultAdapter adapter;

    String URL =
            "http://10.0.2.2/voting_api/get_admin_result.php";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(
                R.layout.activity_admin_result_preview
        );

        recyclerView =
                findViewById(
                        R.id.recyclerResult
                );

        recyclerView.setLayoutManager(
                new LinearLayoutManager(this)
        );

        list = new ArrayList<>();

        adapter =
                new AdminResultAdapter(
                        this,
                        list
                );

        recyclerView.setAdapter(adapter);

        loadData();
    }

    private void loadData(){

        StringRequest request =
                new StringRequest(
                        Request.Method.GET,
                        URL,

                        response -> {

                            try{

                                JSONArray array =
                                        new JSONArray(response);

                                list.clear();

                                for(
                                        int i=0;
                                        i<array.length();
                                        i++
                                ){

                                    JSONObject obj =
                                            array.getJSONObject(i);

                                    list.add(
                                            new AdminResultModel(
                                                    obj.getString("id"),
                                                    obj.getString("candidate_name"),
                                                    obj.getString("total_votes")
                                            )
                                    );
                                }

                                adapter.notifyDataSetChanged();

                            }catch (Exception e){
                                e.printStackTrace();
                            }
                        },

                        error -> error.printStackTrace()
                );

        Volley.newRequestQueue(this)
                .add(request);
    }
}