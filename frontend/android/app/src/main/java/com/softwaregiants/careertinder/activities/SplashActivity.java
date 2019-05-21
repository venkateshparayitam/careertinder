package com.softwaregiants.careertinder.activities;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.view.Window;

import com.softwaregiants.careertinder.R;
import com.softwaregiants.careertinder.preferences.PreferenceManager;
import com.softwaregiants.careertinder.utilities.Constants;

public class SplashActivity extends AppCompatActivity {

    Context mContext;
    Intent nextActivity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE); //will hide the title
        getSupportActionBar().hide(); // hide the title bar
        setContentView(R.layout.activity_splash);
        mContext = this;

        final Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                if (PreferenceManager.getInstance(getApplicationContext()).getBoolean(Constants.PK_LOGIN_STATE,false)) {
                    if ( PreferenceManager.getInstance(mContext).getString(Constants.PK_USER_TYPE, "").equals(Constants.USER_TYPE_JOB_SEEKER) )  {
                        nextActivity = new Intent(mContext,CandidateDashboardActivity.class);
                    } else if ( PreferenceManager.getInstance(mContext).getString(Constants.PK_USER_TYPE, "").equals(Constants.USER_TYPE_EMPLOYER) )  {
                        nextActivity = new Intent(mContext,CompanyDashboardActivity.class);
                    } else {
                        //TODO
                    }
                } else {
                    nextActivity = new Intent(mContext,LoginActivity.class);
                }
                startActivity(nextActivity);
                finish();
            }
        }, 1500);

    }
}
