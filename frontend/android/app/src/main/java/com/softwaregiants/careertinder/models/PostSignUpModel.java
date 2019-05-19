package com.softwaregiants.careertinder.models;

public class PostSignUpModel extends BaseBean {

    private String university;
    private String highest_education;
    private String work_experience;
    private String skill_one;
    private String skill_two;
    private String skill_three;
    private String address;
    private String about_me;
    private String dateBirth;
    private Boolean eu_citizen;

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

    public Boolean getEu_citizen() { return eu_citizen; }
    public void setEu_citizen(Boolean eu_citizen) { this.eu_citizen = eu_citizen; }
}