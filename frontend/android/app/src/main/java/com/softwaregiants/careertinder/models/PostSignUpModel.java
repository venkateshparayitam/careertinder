package com.softwaregiants.careertinder.models;

import com.google.gson.annotations.SerializedName;

public class PostSignUpModel extends BaseBean {

    private String university;

    @SerializedName("highereducation")
    private String highest_education;

    @SerializedName("workexperience")
    private String work_experience;

    @SerializedName("firstskill")
    private String skill_one;

    @SerializedName("secondskill")
    private String skill_two;

    @SerializedName("thirdskill")
    private String skill_three;

    private String address;

    private String aboutme;

    @SerializedName("dob")
    private String dateBirth;

    private String place;

    @SerializedName("eu")
    private Boolean eu_citizen;

    @SerializedName("additionalskill")
    private String additional_skill;

    @SerializedName("firstlanguage")
    private String first_language;

    @SerializedName("secondlanguage")
    private String second_language;

    @SerializedName("nationality")
    private String nationality;


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

    public String getAboutme() { return aboutme; }
    public void setAboutme(String aboutme) { this.aboutme = aboutme; }

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

    public String getNationality() {
        return nationality;
    }

    public void setNationality(String nationality) {
        this.nationality = nationality;
    }
}