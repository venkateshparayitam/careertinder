package com.softwaregiants.careertinder.adapters;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
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
                .inflate(R.layout.job_openings_list_item, parent, false);
        JobOpeningsViewHolder jobOpeningsViewHolder = new JobOpeningsViewHolder(itemView);
        return jobOpeningsViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull JobOpeningsViewHolder holder, int position) {
        JobOpeningModel jobOpeningModel = jobOpenings.get(position);
        holder.jobOpening.setImageResource(R.drawable.baseline_assignment_ind_black_24);
        holder.jobTitle.setText(jobOpeningModel.getJobTitle());
        holder.placeOfWork.setText(jobOpeningModel.getPlaceOfWork());
        holder.preferredSkill.setText(jobOpeningModel.getSkill1());
    }

    @Override
    public int getItemCount() {
        return jobOpenings.size();
    }

    public static class JobOpeningsViewHolder extends RecyclerView.ViewHolder {
        // each data item is just a string in this case

        public ImageView jobOpening;
        public TextView jobTitle;
        public TextView placeOfWork;
        public TextView preferredSkill;

        public JobOpeningsViewHolder(View v) {
            super(v);
            jobOpening = (ImageView) v.findViewById(R.id.IVJobOpening);
            jobTitle = (TextView) v.findViewById(R.id.TVJobTitle);
            placeOfWork = (TextView) v.findViewById(R.id.TVPlaceOfWork);
            preferredSkill = (TextView) v.findViewById(R.id.TVPreferredSkill);
        }
    }
}
