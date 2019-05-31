package com.softwaregiants.careertinder.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.softwaregiants.careertinder.R;
import com.softwaregiants.careertinder.models.JobOpeningModel;

import java.util.List;

public class JobOpeningsAdapter extends RecyclerView.Adapter<JobOpeningsAdapter.JobOpeningsViewHolder> {

    private List<JobOpeningModel> jobOpenings;

    public JobOpeningsAdapter(List<JobOpeningModel> jobOpeningModelList){
        this.jobOpenings = jobOpeningModelList;
    }

    @NonNull
    @Override
    public JobOpeningsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.job_opening_card, parent, false);
        JobOpeningsViewHolder jobOpeningsViewHolder = new JobOpeningsViewHolder(itemView);
        return jobOpeningsViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull JobOpeningsViewHolder holder, int position) {
        JobOpeningModel jobOpeningModel = jobOpenings.get(position);
        holder.jobTitle.setText(jobOpeningModel.getJobTitle());
    }

    @Override
    public int getItemCount() {
        return jobOpenings.size();
    }

    public static class JobOpeningsViewHolder extends RecyclerView.ViewHolder {
        // each data item is just a string in this case

        public TextView jobTitle;

        public JobOpeningsViewHolder(View v) {
            super(v);
            jobTitle = (TextView) v.findViewById(R.id.TVJobTitle);
        }
    }
}
