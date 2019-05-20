package com.softwaregiants.careertinder.models;

import com.google.gson.annotations.SerializedName;

public class AddJobOpeningModel {

    @SerializedName("companyname")
    private String companyName;

    @SerializedName("jobtitle")
    private String jobTitle;

    @SerializedName("jobdescription")
    private String jobDescription;

    @SerializedName("education")
    private String desiredQualification;

    @SerializedName("workexperience")
    private String desiredWorkExperience;

    @SerializedName("location")
    private String placeOfWork;

    private String skill1;

    private String skill2;

    private String skill3;

    private String preferredlanguage1;

    private String preferredlanguage2;

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getJobTitle() {
        return jobTitle;
    }

    public void setJobTitle(String jobTitle) {
        this.jobTitle = jobTitle;
    }

    public String getJobDescription() {
        return jobDescription;
    }

    public void setJobDescription(String jobDescription) {
        this.jobDescription = jobDescription;
    }

    public String getDesiredQualification() {
        return desiredQualification;
    }

    public void setDesiredQualification(String desiredQualification) {
        this.desiredQualification = desiredQualification;
    }

    public String getDesiredWorkExperience() {
        return desiredWorkExperience;
    }

    public void setDesiredWorkExperience(String desiredWorkExperience) {
        this.desiredWorkExperience = desiredWorkExperience;
    }

    public String getPlaceOfWork() {
        return placeOfWork;
    }

    public void setPlaceOfWork(String placeOfWork) {
        this.placeOfWork = placeOfWork;
    }

    public String getSkill1() {
        return skill1;
    }

    public void setSkill1(String skill1) {
        this.skill1 = skill1;
    }

    public String getSkill2() {
        return skill2;
    }

    public void setSkill2(String skill2) {
        this.skill2 = skill2;
    }

    public String getSkill3() {
        return skill3;
    }

    public void setSkill3(String skill3) {
        this.skill3 = skill3;
    }

    public String getPreferredlanguage1() {
        return preferredlanguage1;
    }

    public void setPreferredlanguage1(String preferredlanguage1) {
        this.preferredlanguage1 = preferredlanguage1;
    }

    public String getPreferredlanguage2() {
        return preferredlanguage2;
    }

    public void setPreferredlanguage2(String preferredlanguage2) {
        this.preferredlanguage2 = preferredlanguage2;
    }
}
