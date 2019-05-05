package com.softwaregiants.careertinder.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;

import com.softwaregiants.careertinder.R;

public class LoginActivity extends AppCompatActivity {

    private Button btnHit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE); //will hide the title
        getSupportActionBar().hide(); // hide the title bar
        setContentView(R.layout.activity_login);
        init();
    }

    public void init() {
        btnHit = (Button) findViewById(R.id.btnHit);
        btnHit.setOnClickListener(ocl);
    }

    View.OnClickListener ocl = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch( v.getId() ) {
                case R.id.btnHit:
                    break;
            }
        }
    };
}
