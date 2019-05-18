package com.softwaregiants.careertinder.activities;

import android.app.DatePickerDialog;
import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.view.Window;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.Toast;

import com.softwaregiants.careertinder.R;
import com.softwaregiants.careertinder.models.BaseBean;
import com.softwaregiants.careertinder.networking.ApiResponseCallback;
import com.softwaregiants.careertinder.networking.RetrofitClient;

import java.util.Calendar;

public class PostSignup extends AppCompatActivity {

    Button submitButton;
    Context mContext;
    RetrofitClient mRetrofitClient;

    EditText university;
    EditText highest_education;
    EditText work_experience;
    EditText skill_one;
    EditText skill_two;
    EditText skill_three;
    EditText address;
    EditText about_me;
    EditText dateBirth;

    Switch eu_citizen;
    DatePickerDialog picker;

    String university_value = "";
    String highest_education_value = "";
    String work_experience_value = "";
    String skill_one_value = "";
    String skill_two_value = "";
    String skill_three_value = "";
    String address_value = "";
    String about_me_value = "";
    String dateBirth_value = "";

    Boolean eu_citizen_value = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_post_signup);

        setupSpinner();
        setupDatePicker();
        setupController();
    }

    private void setupController() {

        submitButton = findViewById(R.id.btnSubmit);
        submitButton.setOnClickListener(submitListener);
        mContext = this;
        mRetrofitClient = RetrofitClient.getRetrofitClient(mApiResponseCallback);

        university = findViewById(R.id.ETUniversity);
        highest_education = findViewById(R.id.ETEducation);
        work_experience = findViewById(R.id.ETWorkExperience);
        skill_one = findViewById(R.id.ETSkillOne);
        skill_two = findViewById(R.id.ETSkillTwo);
        skill_three = findViewById(R.id.ETSkillThree);
        address = findViewById(R.id.ETAddress);
        about_me = findViewById(R.id.ETAboutMe);
        dateBirth = findViewById(R.id.ETDateBirth);
        eu_citizen = findViewById(R.id.switchEU);
    }

    View.OnClickListener submitListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            university_value = university.getText().toString();
            highest_education_value = highest_education.getText().toString();
            work_experience_value = work_experience.getText().toString();
            skill_one_value = skill_one.getText().toString();
            skill_two_value = skill_two.getText().toString();
            skill_three_value = skill_three.getText().toString();
            address_value = address.getText().toString();
            about_me_value = about_me.getText().toString();
            dateBirth_value = dateBirth.getText().toString();
            eu_citizen_value = eu_citizen.isChecked();
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

    private void setupSpinner() {
        Spinner spinner = findViewById(R.id.spinner);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.city_array, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
    }

    private void setupDatePicker() {
        dateBirth = findViewById(R.id.ETDateBirth);
        dateBirth.setInputType(InputType.TYPE_NULL);
        dateBirth.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Calendar cldr = Calendar.getInstance();
                int day = cldr.get(Calendar.DAY_OF_MONTH);
                int month = cldr.get(Calendar.MONTH);
                int year = cldr.get(Calendar.YEAR);
                // date picker dialog
                picker = new DatePickerDialog(PostSignup.this,
                        new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                                dateBirth.setText(dayOfMonth + "/" + (monthOfYear + 1) + "/" + year);
                            }
                        }, year, month, day);
                picker.show();
            }
        });
    }
}
