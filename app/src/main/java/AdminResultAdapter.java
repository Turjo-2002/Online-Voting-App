package com.example.onlinevotingsystem;

import android.content.Context;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.Request;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class AdminResultAdapter
        extends RecyclerView.Adapter<AdminResultAdapter.ViewHolder>{

    Context context;
    ArrayList<AdminResultModel> list;

    String SAVE_URL =
            "http://10.0.2.2/voting_api/save_final_result.php";

    public AdminResultAdapter(
            Context context,
            ArrayList<AdminResultModel> list
    ){
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(
            @NonNull ViewGroup parent,
            int viewType
    ) {

        View view =
                LayoutInflater.from(context)
                        .inflate(
                                R.layout.item_result,
                                parent,
                                false
                        );

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(
            @NonNull ViewHolder holder,
            int position
    ) {

        AdminResultModel model =
                list.get(position);

        holder.tvName.setText(
                model.getCandidateName()
        );

        holder.tvAutoVote.setText(
                "Auto Vote : " +
                        model.getAutoVote()
        );

        holder.btnSave.setOnClickListener(v -> {

            String finalVote =
                    holder.etFinalVote
                            .getText()
                            .toString()
                            .trim();

            StringRequest request =
                    new StringRequest(
                            Request.Method.POST,
                            SAVE_URL,

                            response -> {

                                if(
                                        response.trim()
                                                .equals("success")
                                ){

                                    Toast.makeText(
                                            context,
                                            "Saved Successfully",
                                            Toast.LENGTH_LONG
                                    ).show();

                                }else{

                                    Toast.makeText(
                                            context,
                                            "Vote count mismatch",
                                            Toast.LENGTH_LONG
                                    ).show();
                                }
                            },

                            error -> Toast.makeText(
                                    context,
                                    error.toString(),
                                    Toast.LENGTH_LONG
                            ).show()
                    ){

                        @Override
                        protected Map<String,String> getParams(){

                            Map<String,String> params =
                                    new HashMap<>();

                            params.put(
                                    "candidate_id",
                                    model.getCandidateId()
                            );

                            params.put(
                                    "final_vote",
                                    finalVote
                            );

                            return params;
                        }
                    };

            Volley.newRequestQueue(context)
                    .add(request);
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    static class ViewHolder
            extends RecyclerView.ViewHolder{

        TextView tvName,tvAutoVote;
        EditText etFinalVote;
        Button btnSave;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            tvName =
                    itemView.findViewById(R.id.tvName);

            tvAutoVote =
                    itemView.findViewById(R.id.tvAutoVote);

            etFinalVote =
                    itemView.findViewById(R.id.etFinalVote);

            btnSave =
                    itemView.findViewById(R.id.btnSave);
        }
    }
}