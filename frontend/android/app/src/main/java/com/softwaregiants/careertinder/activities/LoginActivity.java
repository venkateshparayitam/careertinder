package com.softwaregiants.careertinder.activities;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.softwaregiants.careertinder.R;
import com.softwaregiants.careertinder.models.BaseBean;
import com.softwaregiants.careertinder.models.LoginModel;
import com.softwaregiants.careertinder.models.LoginSuccessModel;
import com.softwaregiants.careertinder.networking.ApiResponseCallback;
import com.softwaregiants.careertinder.networking.RetrofitClient;
import com.softwaregiants.careertinder.preferences.PreferenceManager;
import com.softwaregiants.careertinder.utilities.Constants;

import org.apache.commons.validator.routines.EmailValidator;

public class LoginActivity extends AppCompatActivity {


    private Button btnHit;
    Context mContext;
    RetrofitClient mRetrofitClient;

    EditText username;
    EditText password;

    String usernameText = "";
    String passwordText = "";

    LoginModel loginModel;

    Intent candidateIntent;
    Intent companyIntent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE); //will hide the title
        getSupportActionBar().hide(); // hide the title bar
        setContentView(R.layout.activity_login);
        init();
    }

    public void init() {
        btnHit = findViewById(R.id.btnHit);
        btnHit.setOnClickListener(ocl);
        mContext = this;
        mRetrofitClient = RetrofitClient.getRetrofitClient(mApiResponseCallback);

        username = (EditText)findViewById(R.id.ETUsername);
        password = (EditText)findViewById(R.id.ETPass);

        candidateIntent = new Intent(this, PostSignup.class);
        companyIntent = new Intent(this, AddNewJobOpening.class);
    }

    View.OnClickListener ocl = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch( v.getId() ) {
                case R.id.btnHit: {
                    login();
                    break;
                }
            }
        }
    };

    ApiResponseCallback mApiResponseCallback = new ApiResponseCallback() {

        @Override
        public void onSuccess(BaseBean baseBean) {
            LoginSuccessModel loginSuccessModel = (LoginSuccessModel) baseBean;
            if (loginSuccessModel.getStatusCode().equals(Constants.SC_SUCCESS)) {
                PreferenceManager.getInstance(mContext).putString(Constants.PK_AUTH_CODE, loginSuccessModel.getAuth_code());
                PreferenceManager.getInstance(mContext).putString(Constants.PK_USER_TYPE, loginSuccessModel.getUser_type());
                PreferenceManager.getInstance(mContext).putBoolean(Constants.PK_LOGIN_STATE, true);

                if (loginSuccessModel.getUser_type().equals(Constants.USER_TYPE_JOB_SEEKER)){
                    startActivity(candidateIntent);
                }
                else if (loginSuccessModel.getUser_type().equals(Constants.USER_TYPE_EMPLOYER)){
                    startActivity(companyIntent);
                }
            } else {
                Toast.makeText(mContext, Constants.MSG_ERROR,Toast.LENGTH_SHORT).show();
            }
        }

        @Override
        public void onFailure(Throwable t) {
            Toast.makeText(mContext,t.getMessage(),Toast.LENGTH_SHORT).show();
        }
    };


    public boolean validateEmail(String email){
        return EmailValidator.getInstance().isValid(email);
    }

    public void register(View view){
        Intent i = new Intent(this, SignUp.class);
        startActivity(i);
    }

    public void login(){
        usernameText = username.getText().toString();
        passwordText = password.getText().toString();

        if(usernameText.equals("") || (!validateEmail(usernameText))){
            if (usernameText.equals("")){
                Toast.makeText(mContext,"Please enter Username",Toast.LENGTH_SHORT).show();
                return;
            }
            if (!validateEmail(usernameText)){
                Toast.makeText(mContext,"Username is not valid",Toast.LENGTH_SHORT).show();
                return;
            }
        }
        else if (passwordText.equals("") || passwordText.length() < 8){
            if (passwordText.equals("")){
                Toast.makeText(mContext,"Please enter Password",Toast.LENGTH_SHORT).show();
                return;
            }
            if (passwordText.length() < 8){
                Toast.makeText(mContext,"Please should be at least 8 characters",Toast.LENGTH_SHORT).show();
                return;
            }
        }
        else {
            loginModel = new LoginModel();
            loginModel.setEmailid(usernameText);
            loginModel.setPassword(passwordText);
            mRetrofitClient.mApiInterface.login(loginModel).enqueue(mRetrofitClient);
        }
    }
}