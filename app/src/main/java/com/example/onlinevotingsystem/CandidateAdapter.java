package com.example.onlinevotingsystem;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class CandidateAdapter
        extends RecyclerView.Adapter<
        CandidateAdapter.ViewHolder> {


    Context context;
    ArrayList<Candidate> list;


    OnVoteClickListener listener;

    public interface OnVoteClickListener {
        void onVoteClick(
                String candidateId
        );
    }

    public CandidateAdapter(
            Context context,
            ArrayList<Candidate> list,
            OnVoteClickListener listener
    ) {

        this.context = context;
        this.list = list;
        this.listener = listener;
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
                                R.layout.item_candidate,
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

        Candidate candidate =
                list.get(position);

        holder.tvName.setText(
                candidate.getName()
        );

        holder.tvParty.setText(
                candidate.getParty()
        );

        holder.tvArea.setText(
                candidate.getArea()
        );


        holder.itemView.setOnClickListener(v -> {

            try {

                // REAL database id
                String candidateId =
                        candidate.getId();

                listener.onVoteClick(
                        candidateId
                );

            } catch (Exception e){

                Toast.makeText(
                        context,
                        e.toString(),
                        Toast.LENGTH_LONG
                ).show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public static class ViewHolder
            extends RecyclerView.ViewHolder {

        TextView tvName,
                tvParty,
                tvArea;

        public ViewHolder(
                @NonNull View itemView
        ) {
            super(itemView);

            tvName =
                    itemView.findViewById(
                            R.id.tvName
                    );

            tvParty =
                    itemView.findViewById(
                            R.id.tvParty
                    );

            tvArea =
                    itemView.findViewById(
                            R.id.tvArea
                    );
        }
    }

}
