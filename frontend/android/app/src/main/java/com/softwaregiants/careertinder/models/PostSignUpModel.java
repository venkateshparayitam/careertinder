package com.softwaregiants.careertinder.models;

import com.google.gson.annotations.SerializedName;

public class PostSignUpModel extends BaseBean {

    private String university;

    @SerializedName("qualification")
    private String highest_education;

    private String work_experience;

    @SerializedName("first_skill")
    private String skill_one;

    @SerializedName("second_skill")
    private String skill_two;

    @SerializedName("third_skill")
    private String skill_three;

    private String address;

    @SerializedName("bio")
    private String about_me;

    @SerializedName("birthday")
    private String dateBirth;

    private String place;

    private Boolean eu_citizen;

    private String additional_skill;
    private String first_language;
    private String second_language;


    public String getUniversity() { return university; }
    public void setUniversity(String university) { this.university = university; }

    public String getHighest_education() {
        return highest_education;
    }
    public void setHighest_education(String highest_education) { this.highest_education = highest_education; }

    public String getWork_experience() { return work_experience; }
    public void setWork_experience(String work_experience) { this.work_experience = work_experience; }

    public String getSkill_one() { return skill_one; }
    public void setSkill_one(String skill_one) { this.skill_one = skill_one; }

    public String getSkill_two() { return skill_two; }
    public void setSkill_two(String skill_two) { this.skill_two = skill_two; }

    public String getSkill_three() { return skill_three; }
    public void setSkill_three(String skill_three) { this.skill_three = skill_three; }

    public String getAddress() { return address; }
    public void setAddress(String address) { this.address = address; }

    public String getAbout_me() { return about_me; }
    public void setAbout_me(String about_me) { this.about_me = about_me; }

    public String getDateBirth() { return dateBirth; }
    public void setDateBirth(String dateBirth) { this.dateBirth = dateBirth; }

    public String getPlace() { return place; }
    public void setPlace(String place) { this.place = place; }

    public Boolean getEu_citizen() { return eu_citizen; }
    public void setEu_citizen(Boolean eu_citizen) { this.eu_citizen = eu_citizen; }

    public String getAdditional_skill() {
        return additional_skill;
    }

    public void setAdditional_skill(String additional_skill) {
        this.additional_skill = additional_skill;
    }

    public String getFirst_language() {
        return first_language;
    }

    public void setFirst_language(String first_language) {
        this.first_language = first_language;
    }

    public String getSecond_language() {
        return second_language;
    }

    public void setSecond_language(String second_language) {
        this.second_language = second_language;
    }
}