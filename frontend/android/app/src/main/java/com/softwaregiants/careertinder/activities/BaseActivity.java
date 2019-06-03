package com.softwaregiants.careertinder.activities;

import android.content.Context;
import android.content.Intent;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.softwaregiants.careertinder.R;
import com.softwaregiants.careertinder.preferences.PreferenceManager;
import com.softwaregiants.careertinder.utilities.Constants;

import de.hdodenhof.circleimageview.CircleImageView;

class BaseActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    Context mContext;
    CircleImageView circleImageView;
    NavigationView navigationView;
    DrawerLayout drawer;
    Toolbar toolbar;
    TextView TVNavEmail;

    public void addDrawer(String title) {
        switch ( PreferenceManager.getInstance(getApplicationContext()).getString(Constants.PK_USER_TYPE,"") ) {
            case Constants.USER_TYPE_EMPLOYER:
                break;
            case Constants.USER_TYPE_JOB_SEEKER:
                break;
        }
        bindViews();
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle(title);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();
        navigationView.setNavigationItemSelectedListener(this);
        navigationView.setCheckedItem(R.id.nav_job_search);
        TVNavEmail.setText(PreferenceManager.getInstance(getApplicationContext()).getString(Constants.PK_EMAIL, ""));
    }

    public void bindViews() {
        toolbar = findViewById(R.id.toolbar);
        drawer = findViewById(R.id.drawer_layout);
        navigationView = findViewById(R.id.nav_view);
        View hView =  navigationView.getHeaderView(0);
        TVNavEmail = hView.findViewById(R.id.TVNavEmail);
    }

    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        switch ( item.getItemId() ) {
            case R.id.nav_job_search: break;
            case R.id.nav_edit_profile: break;
            case R.id.nav_logout:
                logOut();
                break;
        }
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        if ( drawer != null &&drawer.isDrawerOpen(GravityCompat.START) ) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    public void logOut(){
        PreferenceManager.getInstance(getApplicationContext()).clear();

        Toast.makeText(this, "You have been logged out", Toast.LENGTH_SHORT).show();
        startActivity(new Intent(this,LoginActivity.class));
        finish();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish(); // close this activity and return to preview activity (if there is any)
        }

        return super.onOptionsItemSelected(item);
    }
}
