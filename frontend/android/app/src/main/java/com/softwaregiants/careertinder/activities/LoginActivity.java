package com.softwaregiants.careertinder.activities;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.softwaregiants.careertinder.R;
import com.softwaregiants.careertinder.models.BaseBean;
import com.softwaregiants.careertinder.models.LoginModel;
import com.softwaregiants.careertinder.models.LoginSuccessModel;
import com.softwaregiants.careertinder.networking.ApiInterface;
import com.softwaregiants.careertinder.networking.ApiResponseCallback;
import com.softwaregiants.careertinder.networking.RetrofitClient;

import org.apache.commons.validator.routines.EmailValidator;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class LoginActivity extends AppCompatActivity {


    private Button btnHit;
    Context mContext;
    RetrofitClient mRetrofitClient;

    EditText username;
    EditText password;

    String usernameText = "";
    String passwordText = "";

    LoginModel loginModel;

    private Retrofit retrofit;
    private ApiInterface apiInterface;

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

        /*
        retrofit = new Retrofit.Builder()
                .baseUrl("http://192.168.1.105:8080/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        apiInterface = retrofit.create(ApiInterface.class);
        */
    }

    View.OnClickListener ocl = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch( v.getId() ) {
                case R.id.btnHit: {
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
                        //test();
                        mRetrofitClient.mApiInterface.login(loginModel).enqueue(mRetrofitClient);
                        break;
                    }
                }
            }
        }
    };

    /*
    public void test(){
        Call<LoginSuccessModel> call = apiInterface.login(loginModel);

        call.enqueue(new Callback<LoginSuccessModel>() {
            @Override
            public void onResponse(Call<LoginSuccessModel> call, Response<LoginSuccessModel> response) {
                if(!response.isSuccessful()){
                    Toast.makeText(mContext,"Response Code: " + response.code(),Toast.LENGTH_SHORT).show();
                    return;
                }
                else{
                    LoginSuccessModel loginSuccessModel = response.body();
                    String resp = "Auth Code: " + loginSuccessModel.getAuth_code() + "\n"
                            + "User Type: " + loginSuccessModel.getUser_type();

                    Toast.makeText(mContext,resp,Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<LoginSuccessModel> call, Throwable t) {

                Toast.makeText(mContext,"Failed",Toast.LENGTH_SHORT).show();
            }
        });
    }
    */

    ApiResponseCallback mApiResponseCallback = new ApiResponseCallback() {

        @Override
        public void onSuccess(BaseBean baseBean) {
            /*
            LoginSuccessModel loginSuccessModel = (LoginSuccessModel) baseBean;
            String resp = "Auth Code: " + loginSuccessModel.getAuth_code() + "\n"
                    + "User Type: " + loginSuccessModel.getUser_type();
            */
            Toast.makeText(mContext,baseBean.getStatusCode(),Toast.LENGTH_SHORT).show();
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
}
