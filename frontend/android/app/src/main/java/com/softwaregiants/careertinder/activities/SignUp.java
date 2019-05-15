package com.softwaregiants.careertinder.activities;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

import com.softwaregiants.careertinder.R;
import com.softwaregiants.careertinder.models.BaseBean;
import com.softwaregiants.careertinder.models.SignUpModel;
import com.softwaregiants.careertinder.networking.ApiResponseCallback;
import com.softwaregiants.careertinder.networking.RetrofitClient;

public class SignUp extends AppCompatActivity {

    private Button createMyAccount;
    Context mContext;
    RetrofitClient mRetrofitClient;

    EditText fullName;
    EditText emailAddress;
    EditText password;
    EditText confirmPassword;

    String userTypeString = "";
    String name = "";
    String email = "";
    String pass = "";
    String confirmPass = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        createMyAccount = findViewById(R.id.createMyAccountBtn);
        createMyAccount.setOnClickListener(ocl);
        mContext = this;
        mRetrofitClient = RetrofitClient.getRetrofitClient(mApiResponseCallback);

        fullName = (EditText)findViewById(R.id.fullName);
        emailAddress = (EditText)findViewById(R.id.emailAddress);
        password = (EditText)findViewById(R.id.password);
        confirmPassword = (EditText)findViewById(R.id.confirmPassword);
    }

    View.OnClickListener ocl = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            name = fullName.getText().toString();
            email = emailAddress.getText().toString();
            pass = password.getText().toString();
            confirmPass = confirmPassword.getText().toString();
            if (matchPasswords(pass, confirmPass)) {
                SignUpModel signUpModel = new SignUpModel();
                signUpModel.setName(name);
                signUpModel.setEmail(email);
                signUpModel.setPassword(pass);
                signUpModel.setUserType(userTypeString);

                mRetrofitClient.mApiInterface.signUp(signUpModel).enqueue(mRetrofitClient);
            }
            else{
                Toast.makeText(mContext,"Passwords don't match", Toast.LENGTH_SHORT).show();
            }
        }
    };

    ApiResponseCallback mApiResponseCallback = new ApiResponseCallback() {
        @Override
        public void onSuccess(BaseBean baseBean) {
            Toast.makeText(mContext,baseBean.getStatusCode(),Toast.LENGTH_SHORT).show();
        }

        @Override
        public void onFailure(Throwable t) {
            Toast.makeText(mContext,t.getMessage(),Toast.LENGTH_SHORT).show();
        }
    };

    public void onRadioButtonClicked(View view) {
        boolean checked = ((RadioButton) view).isChecked();

        switch(view.getId()) {
            case R.id.jobSeekerRadioButton:
                if (checked)
                    userTypeString = "jobseeker";
                    break;
            case R.id.employerRadioButton:
                if (checked)
                    userTypeString = "employer";
                    break;
        }
    }

    public boolean matchPasswords(String p, String c){
        if(p.equals(c)){
            return true;
        }
        else{
            return false;
        }
    }
}
