package com.softwaregiants.careertinder.activities;

import android.os.Bundle;

import com.mindorks.placeholderview.SwipePlaceHolderView;
import com.softwaregiants.careertinder.R;
import com.softwaregiants.careertinder.networking.RetrofitClient;

public class CompanyDashboardActivity extends BaseActivity {

    private static final String TAG = "CompanyDashboardActivity";

    private SwipePlaceHolderView swipePlaceHolderView;
//TODO    List<JobOpeningModel> jobOpeningModelList;
    private RetrofitClient mRetrofitClient;
    int items = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_company_dashboard);
        addDrawer("");
        init();
    }

    private void init() {

    }
}
