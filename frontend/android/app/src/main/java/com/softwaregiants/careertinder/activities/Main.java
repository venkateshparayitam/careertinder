package com.softwaregiants.careertinder.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;

import com.softwaregiants.careertinder.R;

public class Main extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE); //will hide the title
        getSupportActionBar().hide(); // hide the title bar
        setContentView(R.layout.activity_main);
    }

    public void signUp(View view){
        Intent i = new Intent(this, SignUp.class);
        startActivity(i);
    }

    public void login(View view){
        Intent i = new Intent(this, LoginActivity.class);
        startActivity(i);
    }
}
