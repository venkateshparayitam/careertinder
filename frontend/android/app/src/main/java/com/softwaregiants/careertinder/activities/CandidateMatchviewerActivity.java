package com.softwaregiants.careertinder.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.softwaregiants.careertinder.R;
import com.softwaregiants.careertinder.adapters.CandidateMatchviewerAdapter;
import com.softwaregiants.careertinder.models.BaseBean;
import com.softwaregiants.careertinder.models.JobOpeningsListModel;
import com.softwaregiants.careertinder.networking.ApiResponseCallback;
import com.softwaregiants.careertinder.networking.RetrofitClient;
import com.softwaregiants.careertinder.preferences.PreferenceManager;
import com.softwaregiants.careertinder.utilities.Constants;
import com.softwaregiants.careertinder.utilities.UtilityMethods;

public class CandidateMatchviewerActivity extends BaseActivity {

    String authCode;
    RetrofitClient mRetrofitClient;
    TextView TVNoItems;

    private RecyclerView recyclerView;
    private CandidateMatchviewerAdapter mAdapter;
    private RecyclerView.LayoutManager layoutManager;

    private JobOpeningsListModel jobOpeningsListModel;

    Intent nextActivity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_candidate_matchviewer);

        Button btn = findViewById(R.id.addJobOpeningBtn);
        btn.setVisibility(View.GONE);

        nextActivity = new Intent(this, JobDetailActivity.class);

        init();
    }

    public void init(){
        authCode = PreferenceManager.getInstance(getApplicationContext()).getString(Constants.PK_AUTH_CODE, "");

        mContext = this;
        mRetrofitClient = RetrofitClient.getRetrofitClient(mApiResponseCallback,getApplicationContext());
        TVNoItems = findViewById(R.id.TVNoItems);
        TVNoItems.setText("No Matches Found");

        if ( UtilityMethods.isConnected(mContext) ) {
            mRetrofitClient.mApiInterface.getMatchesForCandidate(authCode).enqueue(mRetrofitClient.createProgress(mContext));
        }
    }

    ApiResponseCallback mApiResponseCallback = new ApiResponseCallback() {
        @Override
        public void onSuccess(BaseBean baseBean) {
            if (baseBean.getStatusCode().equals("Success")) {
                jobOpeningsListModel = (JobOpeningsListModel) baseBean;
                if ( jobOpeningsListModel != null && jobOpeningsListModel.getJobOpeningModelList() != null &&
                        !jobOpeningsListModel.getJobOpeningModelList().isEmpty()){
                    TVNoItems.setVisibility(View.INVISIBLE);
                    buildRV();
                }
            }
            else {
                Toast.makeText(mContext, Constants.MSG_ERROR,Toast.LENGTH_SHORT).show();
            }
        }

        @Override
        public void onFailure(Throwable t) {
        }
    };

    public void buildRV(){
        recyclerView = (RecyclerView) findViewById(R.id.my_recycler_view);
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        mAdapter = new CandidateMatchviewerAdapter(jobOpeningsListModel.getJobOpeningModelList());
        recyclerView.setAdapter(mAdapter);

        mAdapter.setOnItemClickListener(new CandidateMatchviewerAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                startActivity(nextActivity.putExtra("job", jobOpeningsListModel.getJobOpeningModelList().get(position)));
            }
        });
    }
}
