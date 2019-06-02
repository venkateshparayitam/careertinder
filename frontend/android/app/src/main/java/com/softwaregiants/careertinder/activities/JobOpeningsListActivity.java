package com.softwaregiants.careertinder.activities;

import android.content.Context;
import android.content.Intent;
import android.os.Parcelable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.softwaregiants.careertinder.R;
import com.softwaregiants.careertinder.adapters.JobOpeningsAdapter;
import com.softwaregiants.careertinder.models.BaseBean;
import com.softwaregiants.careertinder.models.JobOpeningsListModel;
import com.softwaregiants.careertinder.networking.ApiResponseCallback;
import com.softwaregiants.careertinder.networking.RetrofitClient;
import com.softwaregiants.careertinder.preferences.PreferenceManager;
import com.softwaregiants.careertinder.utilities.Constants;


public class JobOpeningsListActivity extends AppCompatActivity {


    private Button btn;
    Intent nextActivity;
    Context mContext;
    RetrofitClient mRetrofitClient;

    public String authCode;

    private RecyclerView recyclerView;
    private JobOpeningsAdapter mAdapter;
    private RecyclerView.LayoutManager layoutManager;

    private JobOpeningsListModel jobOpeningsListModel;

    Intent companyDashboardIntent;
    Intent editJobOpeningIntent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_job_openings_list);

        init();
        btn = findViewById(R.id.addJobOpeningBtn);
        btn.setOnClickListener(onClickListener);
        nextActivity = new Intent(this, AddNewJobOpening.class);
        companyDashboardIntent = new Intent(this, CompanyDashboardActivity.class);
        editJobOpeningIntent = new Intent(this, EditJobOpeningActivity.class);


    }

    public void init(){
        authCode = PreferenceManager.getInstance(getApplicationContext()).getString(Constants.PK_AUTH_CODE, "");

        mContext = this;
        mRetrofitClient = RetrofitClient.getRetrofitClient(mApiResponseCallback,getApplicationContext());

        mRetrofitClient.mApiInterface.getJobOpenings(authCode).enqueue(mRetrofitClient);
    }

    public void buildRV(){
        recyclerView = (RecyclerView) findViewById(R.id.my_recycler_view);
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        mAdapter = new JobOpeningsAdapter(jobOpeningsListModel.getJobOpeningModelList());
        recyclerView.setAdapter(mAdapter);

        mAdapter.setOnItemClickListener(new JobOpeningsAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                startActivity(companyDashboardIntent.putExtra("jobTitle", jobOpeningsListModel.getJobOpeningModelList().get(position).getJobId()));
            }

            @Override
            public void onEditClick(int position) {
                startActivity(editJobOpeningIntent.putExtra("jobOpeningObject", (Parcelable) jobOpeningsListModel.getJobOpeningModelList().get(position)));
            }

            @Override
            public void onDeleteClick(int position) {

            }
        });
    }

    ApiResponseCallback mApiResponseCallback = new ApiResponseCallback() {
        @Override
        public void onSuccess(BaseBean baseBean) {
            if (baseBean.getStatusCode().equals("Success")) {
                jobOpeningsListModel = (JobOpeningsListModel) baseBean;
                buildRV();
            }
            else {
                Toast.makeText(mContext, Constants.MSG_ERROR,Toast.LENGTH_SHORT).show();
            }
        }

        @Override
        public void onFailure(Throwable t) {
            Toast.makeText(mContext,t.getMessage(),Toast.LENGTH_SHORT).show();
        }
    };

    View.OnClickListener onClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            startActivity(nextActivity);
            finish();
        }
    };
}
