package com.softwaregiants.careertinder.activities;

import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.softwaregiants.careertinder.R;
import com.softwaregiants.careertinder.models.CandidateProfileModel;
import com.squareup.picasso.Picasso;

public class CandidateDetailActivity extends BaseActivity {

    FirebaseStorage storage = FirebaseStorage.getInstance();

    CandidateProfileModel candidateProfileModel;

    ImageView picture;
    TextView TVCompanyName;
    TextView TVJobDescription;

    TextView TVDesiredQualification;
    TextView TVDesiredWorkExperience;
    TextView TVPlaceOfWork;

    TextView TVSkill1;
    TextView TVSkill2;
    TextView TVSkill3;

    TextView TVLanguage1;
    TextView TVLanguage2;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_candidate_detail);
        candidateProfileModel = getIntent().getParcelableExtra("job");
        init();
    }

    private void init() {
        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        // add back arrow to toolbar
        if (getSupportActionBar() != null){
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
        }
        getSupportActionBar().setTitle("Job Details");

        picture = findViewById(R.id.picture);
        TVCompanyName = findViewById(R.id.TVCompanyName);
        TVJobDescription = findViewById(R.id.TVJobDescription);

        TVDesiredQualification = findViewById(R.id.TVDesiredQualification);
        TVDesiredWorkExperience = findViewById(R.id.TVDesiredWorkExperience);
        TVPlaceOfWork = findViewById(R.id.TVPlaceOfWork);

        TVSkill1 = findViewById(R.id.TVSkill1);
        TVSkill2 = findViewById(R.id.TVSkill2);
        TVSkill3 = findViewById(R.id.TVSkill3);

        TVLanguage1 = findViewById(R.id.TVLanguage1);
        TVLanguage2 = findViewById(R.id.TVLanguage2);

        TVCompanyName.setText(candidateProfileModel.getName());
        TVJobDescription.setText(candidateProfileModel.getAboutme());

        TVDesiredQualification.setText(candidateProfileModel.getHighest_education());
        TVDesiredWorkExperience.setText(candidateProfileModel.getWork_experience() + "months");
        TVPlaceOfWork.setText(candidateProfileModel.getPlace());

        TVSkill1.setText(candidateProfileModel.getSkill_one());
        TVSkill2.setText(candidateProfileModel.getSkill_two());
        TVSkill3.setText(candidateProfileModel.getSkill_three());

        TVLanguage1.setText(candidateProfileModel.getFirst_language());
        TVLanguage2.setText(candidateProfileModel.getSecond_language());

        if ( null != candidateProfileModel.getImageUrl() && !candidateProfileModel.getImageUrl().isEmpty()) {
            StorageReference storageRef = storage.getReference(candidateProfileModel.getImageUrl());
            storageRef.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                @Override
                public void onSuccess(Uri uri) {
                    Picasso.get().load(uri).fit()
                            .placeholder(R.drawable.image_placeholder)
                            .error(R.drawable.image_placeholder).into(picture);
                }
            });
        } else {
            picture.setImageResource(R.drawable.image_placeholder);
        }

    }
}
