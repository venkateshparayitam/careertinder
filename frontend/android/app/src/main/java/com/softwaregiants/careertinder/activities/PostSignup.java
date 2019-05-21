package com.softwaregiants.careertinder.activities;

import android.Manifest;
import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.media.MediaScannerConnection;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.text.InputType;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.Toast;

import com.karumi.dexter.Dexter;
import com.karumi.dexter.MultiplePermissionsReport;
import com.karumi.dexter.PermissionToken;
import com.karumi.dexter.listener.DexterError;
import com.karumi.dexter.listener.PermissionRequest;
import com.karumi.dexter.listener.PermissionRequestErrorListener;
import com.karumi.dexter.listener.multi.MultiplePermissionsListener;
import com.softwaregiants.careertinder.R;
import com.softwaregiants.careertinder.models.BaseBean;
import com.softwaregiants.careertinder.models.PostSignUpModel;
import com.softwaregiants.careertinder.networking.ApiResponseCallback;
import com.softwaregiants.careertinder.networking.RetrofitClient;
import com.softwaregiants.careertinder.preferences.PreferenceManager;
import com.softwaregiants.careertinder.utilities.Constants;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Calendar;
import java.util.List;

public class PostSignup extends AppCompatActivity {

    final String EMPTY_STRING = "";
    Button submitButton;
    Button updateImageButton;
    Context mContext;
    RetrofitClient mRetrofitClient;

    private int GALLERY = 1, CAMERA = 2;
    private static final String IMAGE_DIRECTORY = "/career_tinder";

    ImageView imageUser;

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

    String address_value = EMPTY_STRING;
    String about_me_value = EMPTY_STRING;
    String dateBirth_value = EMPTY_STRING;
    String place_value = EMPTY_STRING;

    private String first_language = EMPTY_STRING;
    private String second_language = EMPTY_STRING;

    Boolean eu_citizen_value = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getSupportActionBar().hide();
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
        mRetrofitClient = RetrofitClient.getRetrofitClient(mApiResponseCallback);

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
    }


    View.OnClickListener updateImageListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            showPictureDialog();
        }
    };

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {

        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == this.RESULT_CANCELED) {
            return;
        }
        if (requestCode == GALLERY) {
            if (data != null) {
                Uri contentURI = data.getData();
                try {
                    Bitmap bitmap = MediaStore.Images.Media.getBitmap(this.getContentResolver(), contentURI);
                    String path = saveImage(bitmap);
                    Toast.makeText(PostSignup.this, "Image Saved!", Toast.LENGTH_SHORT).show();
                    imageUser.setImageBitmap(bitmap);

                } catch (IOException e) {
                    e.printStackTrace();
                    Toast.makeText(PostSignup.this, "Failed!", Toast.LENGTH_SHORT).show();
                }
            }

        } else if (requestCode == CAMERA) {
            Bitmap thumbnail = (Bitmap) data.getExtras().get("data");
            imageUser.setImageBitmap(thumbnail);
            saveImage(thumbnail);
            Toast.makeText(PostSignup.this, "Image Saved!", Toast.LENGTH_SHORT).show();
        }
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
            postSignUpModel.setAbout_me(about_me_value);
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
                mRetrofitClient.mApiInterface.postSignUp(postSignUpModel, PreferenceManager.getInstance(mContext).getString(Constants.PK_AUTH_CODE, EMPTY_STRING)).enqueue(mRetrofitClient);
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

    private void  requestMultiplePermissions(){
        Dexter.withActivity(this)
                .withPermissions(
                        Manifest.permission.CAMERA,
                        Manifest.permission.WRITE_EXTERNAL_STORAGE,
                        Manifest.permission.READ_EXTERNAL_STORAGE)
                .withListener(new MultiplePermissionsListener() {
                    @Override
                    public void onPermissionsChecked(MultiplePermissionsReport report) {
                        // check if all permissions are granted
                        if (report.areAllPermissionsGranted()) {
                            Toast.makeText(getApplicationContext(), "All permissions are granted by user!", Toast.LENGTH_SHORT).show();
                        }

                        // check for permanent denial of any permission
                        if (report.isAnyPermissionPermanentlyDenied()) {
                            // show alert dialog navigating to Settings
                            //openSettingsDialog();
                        }
                    }

                    @Override
                    public void onPermissionRationaleShouldBeShown(List<PermissionRequest> permissions, PermissionToken token) {
                        token.continuePermissionRequest();
                    }
                }).
                withErrorListener(new PermissionRequestErrorListener() {
                    @Override
                    public void onError(DexterError error) {
                        Toast.makeText(getApplicationContext(), "Some Error! ", Toast.LENGTH_SHORT).show();
                    }
                })
                .onSameThread()
                .check();
    }

    public String saveImage(Bitmap myBitmap) {
        ByteArrayOutputStream bytes = new ByteArrayOutputStream();
        myBitmap.compress(Bitmap.CompressFormat.JPEG, 90, bytes);
        File wallpaperDirectory = new File(
                Environment.getExternalStorageDirectory() + IMAGE_DIRECTORY);
        // have the object build the directory structure, if needed.
        if (!wallpaperDirectory.exists()) {
            wallpaperDirectory.mkdirs();
        }

        try {
            File f = new File(wallpaperDirectory, Calendar.getInstance()
                    .getTimeInMillis() + ".jpg");
            f.createNewFile();
            FileOutputStream fo = new FileOutputStream(f);
            fo.write(bytes.toByteArray());
            MediaScannerConnection.scanFile(this,
                    new String[]{f.getPath()},
                    new String[]{"image/jpeg"}, null);
            fo.close();
            Log.d("TAG", "File Saved::---&gt;" + f.getAbsolutePath());

            return f.getAbsolutePath();
        } catch (IOException e1) {
            e1.printStackTrace();
        }
        return EMPTY_STRING;
    }


    public void choosePhotoFromGallery() {
        Intent galleryIntent = new Intent(Intent.ACTION_PICK,
                android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);

        startActivityForResult(galleryIntent, GALLERY);
    }

    private void takePhotoFromCamera() {
        Intent intent = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
        startActivityForResult(intent, CAMERA);
    }

    private void showPictureDialog(){
        AlertDialog.Builder pictureDialog = new AlertDialog.Builder(this);
        pictureDialog.setTitle("Select Action");
        String[] pictureDialogItems = {
                "Select photo from gallery",
                "Capture photo from camera" };
        pictureDialog.setItems(pictureDialogItems,
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        switch (which) {
                            case 0:
                                choosePhotoFromGallery();
                                break;
                            case 1:
                                takePhotoFromCamera();
                                break;
                        }
                    }
                });
        pictureDialog.show();
    }
}
