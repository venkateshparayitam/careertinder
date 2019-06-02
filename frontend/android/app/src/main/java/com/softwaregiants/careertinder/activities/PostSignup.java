package com.softwaregiants.careertinder.activities;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.Toast;

import com.softwaregiants.careertinder.R;
import com.softwaregiants.careertinder.models.BaseBean;
import com.softwaregiants.careertinder.models.PostSignUpModel;
import com.softwaregiants.careertinder.networking.ApiResponseCallback;
import com.softwaregiants.careertinder.networking.RetrofitClient;
import com.softwaregiants.careertinder.preferences.PreferenceManager;
import com.softwaregiants.careertinder.utilities.Constants;

import java.util.Calendar;

public class PostSignup extends ImagePickerActivity {

    //region Globals
    Button submitButton;
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
    EditText skill_addnl;

    Switch eu_citizen;
    Spinner place_spinner;
    Spinner lang1_spinner;
    Spinner lang2_spinner;
    DatePickerDialog picker;

    String university_value = EMPTY_STRING;
    String highest_education_value = EMPTY_STRING;
    String work_experience_value = EMPTY_STRING;
    String skill_one_value = EMPTY_STRING;
    String skill_two_value = EMPTY_STRING;
    String skill_three_value = EMPTY_STRING;
    String skill_addnl_value = EMPTY_STRING;
    String authToken;

    String address_value = EMPTY_STRING;
    String about_me_value = EMPTY_STRING;
    String dateBirth_value = EMPTY_STRING;
    String place_value = EMPTY_STRING;

    private String first_language = EMPTY_STRING;
    private String second_language = EMPTY_STRING;

    Boolean eu_citizen_value = false;
    //endregion

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post_signup);

        setupSpinners();
        setupDatePicker();
        setupController();
        requestMultiplePermissions();
    }

    private void setupController() {

        submitButton = findViewById(R.id.btnSubmit);
        updateImageButton = findViewById(R.id.updatePicture);
        imageUser = findViewById(R.id.picture);

        submitButton.setOnClickListener(submitListener);
        updateImageButton.setOnClickListener(updateImageListener);

        mContext = this;
        mRetrofitClient = RetrofitClient.getRetrofitClient(mApiResponseCallback,getApplicationContext());

        university = findViewById(R.id.ETUniversity);
        highest_education = findViewById(R.id.ETEducation);
        work_experience = findViewById(R.id.ETWorkExperience);
        skill_one = findViewById(R.id.ETSkillOne);
        skill_two = findViewById(R.id.ETSkillTwo);
        skill_three = findViewById(R.id.ETSkillThree);
        skill_addnl = findViewById(R.id.ETSkillAddnl);
        address = findViewById(R.id.ETAddress);
        about_me = findViewById(R.id.ETAboutMe);
        dateBirth = findViewById(R.id.ETDateBirth);
        eu_citizen = findViewById(R.id.switchEU);
        place_spinner = findViewById(R.id.spinnerPlace);
        lang1_spinner = findViewById(R.id.spinnerLanguage1);
        lang2_spinner = findViewById(R.id.spinnerLanguage2);
        authToken = PreferenceManager.getInstance(getApplicationContext()).getString(Constants.PK_AUTH_CODE, EMPTY_STRING);
    }

    //region submit listener
    View.OnClickListener submitListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            university_value = university.getText().toString();
            highest_education_value = highest_education.getText().toString();
            work_experience_value = work_experience.getText().toString();
            skill_one_value = skill_one.getText().toString();
            skill_two_value = skill_two.getText().toString();
            skill_three_value = skill_three.getText().toString();
            skill_addnl_value = skill_one.getText().toString();
            address_value = address.getText().toString();
            about_me_value = about_me.getText().toString();
            dateBirth_value = dateBirth.getText().toString();
            eu_citizen_value = eu_citizen.isChecked();
            place_value = place_spinner.getSelectedItem().toString();
            first_language = lang1_spinner.getSelectedItem().toString();
            second_language = lang2_spinner.getSelectedItem().toString();

            PostSignUpModel postSignUpModel = new PostSignUpModel();
            postSignUpModel.setUniversity(university_value);
            postSignUpModel.setHighest_education(highest_education_value);
            postSignUpModel.setWork_experience(work_experience_value);
            postSignUpModel.setSkill_one(skill_one_value);
            postSignUpModel.setSkill_two(skill_two_value);
            postSignUpModel.setSkill_three(skill_three_value);
            postSignUpModel.setAdditional_skill(skill_addnl_value);
            postSignUpModel.setAddress(address_value);
            postSignUpModel.setAboutme(about_me_value);
            postSignUpModel.setDateBirth(dateBirth_value);
            postSignUpModel.setEu_citizen(eu_citizen_value);
            postSignUpModel.setPlace(place_value);
            postSignUpModel.setFirst_language(first_language);
            postSignUpModel.setSecond_language(second_language);

            if(university_value.equals(EMPTY_STRING)){
                Toast.makeText(mContext,"Please enter university name", Toast.LENGTH_SHORT).show();
                return;
            }
            else if (highest_education_value.equals(EMPTY_STRING)){
                Toast.makeText(mContext,"Please enter your highest education", Toast.LENGTH_SHORT).show();
                return;
            }
            else if (work_experience_value.equals(EMPTY_STRING)){
                Toast.makeText(mContext,"Please enter your work experience", Toast.LENGTH_SHORT).show();
                return;
            }
            else if (skill_one_value.equals(EMPTY_STRING)){
                Toast.makeText(mContext,"Please enter your Skill One", Toast.LENGTH_SHORT).show();
                return;
            }
            else if (skill_two_value.equals(EMPTY_STRING)){
                Toast.makeText(mContext,"Please enter your Skill Two", Toast.LENGTH_SHORT).show();
                return;
            }
            else if (skill_three_value.equals(EMPTY_STRING)){
                Toast.makeText(mContext,"Please enter your Skill Three", Toast.LENGTH_SHORT).show();
                return;
            }
            else if (address_value.equals(EMPTY_STRING)){
                Toast.makeText(mContext,"Please enter your Address", Toast.LENGTH_SHORT).show();
                return;
            }
            else if (about_me_value.equals(EMPTY_STRING)){
                Toast.makeText(mContext,"Please tell something about yourself", Toast.LENGTH_SHORT).show();
                return;
            }
            else if (dateBirth_value.equals(EMPTY_STRING)){
                Toast.makeText(mContext,"Please enter your date of birth", Toast.LENGTH_SHORT).show();
                return;
            }
            else{
                mRetrofitClient.mApiInterface.postSignUp(postSignUpModel,authToken).enqueue(mRetrofitClient);
            }
        }
    };
    //endregion

    ApiResponseCallback mApiResponseCallback = new ApiResponseCallback() {
        @Override
        public void onSuccess(BaseBean baseBean) {
            Toast.makeText(mContext,"Your profile was created successfully.",Toast.LENGTH_SHORT).show();
            //TODO
//            if (baseBean.getStatusCode().equals("")) {
                PreferenceManager.getInstance(getApplicationContext()).putBoolean(Constants.PK_PROFILE_CREATED,true);
                startActivity(new Intent(mContext,CandidateDashboardActivity.class));
                finish();
//            }
        }

        @Override
        public void onFailure(Throwable t) {
        }
    };

    private void setupSpinners() {
        Spinner spinner = findViewById(R.id.spinnerPlace);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.city_array, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);

        spinner = findViewById(R.id.spinnerLanguage1);
        ArrayAdapter<CharSequence> adapter2 = ArrayAdapter.createFromResource(this,
                R.array.language_array_1, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter2);

        spinner = findViewById(R.id.spinnerLanguage2);
        ArrayAdapter<CharSequence> adapter3 = ArrayAdapter.createFromResource(this,
                R.array.language_array_2, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter2);


    }

    private void setupDatePicker() {
        dateBirth = findViewById(R.id.ETDateBirth);
        dateBirth.setInputType(InputType.TYPE_NULL);
        dateBirth.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Calendar calendar = Calendar.getInstance();
                int day = calendar.get(Calendar.DAY_OF_MONTH);
                int month = calendar.get(Calendar.MONTH);
                int year = calendar.get(Calendar.YEAR);
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
