package core.entities;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

/**
 * Entity implementation class for Entity: ApplicantEntity
 * @author: Pravin Garad
 */

@Entity
@Table(name="ctapplicant", catalog="career_tinder")
public class CTApplicantEntity implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name = "applicant_id", updatable = false, nullable = true)
	private Long id;
	
	@Column(name="applicant_name", nullable = true)
	private String name;
	
	@Column(name = "emailid", updatable = false, nullable = true)
	private String emailid;
	
	@Column(name="work_experience", nullable = false)
	private int workexperience;
	
	@Column(name="university", nullable = true)
	private String university;
	
	@Column(name="eu_citizen")
	private boolean eu = false;
	
	@Column(name="qualification")
	private String qualification;
	
	@Column(name="address", nullable = true)
	private String address;
	
	@Column(name="bio")
	private String bio;
	
	@Column(name="first_skill", nullable = true)
	private String firstskill;
	
	@Column(name="second_skill", nullable = true)
	private String secondskill;
	
	@Column(name="third_skill", nullable = true)
	private String thirdskill;
	
	@Column(name="additional_skill")
	private String additionalskill;
	
	@Column(name="mother_tounge", nullable = true)
	private String mothertounge;
	
	@Column(name="first_language")
	private String firstlanguage;
	
	@Column(name="second_language")
	private String secondlanguage;
	
	@Column(name="birthday", nullable = true)
	private String birthday;
	
	@Column(name="nationality")
	private String nationality;
	
	@OneToOne(mappedBy = "applicant", cascade = CascadeType.ALL,
            fetch = FetchType.LAZY, optional = false)
            private CTUserEntity user;
	
	
	public Long getId() {
		return id;
	}


	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}


	public int getWorkexperience() {
		return workexperience;
	}


	public void setWorkexperience(int workexperience) {
		this.workexperience = workexperience;
	}


	public String getQualification() {
		return qualification;
	}


	public void setQualification(String qualification) {
		this.qualification = qualification;
	}


	public String getAddress() {
		return address;
	}


	public void setAddress(String address) {
		this.address = address;
	}


	public String getBio() {
		return bio;
	}


	public void setBio(String bio) {
		this.bio = bio;
	}


	public String getFirstskill() {
		return firstskill;
	}


	public void setFirstskill(String firstskill) {
		this.firstskill = firstskill;
	}


	public String getSecondskill() {
		return secondskill;
	}


	public void setSecondskill(String secondskill) {
		this.secondskill = secondskill;
	}


	public String getThirdskill() {
		return thirdskill;
	}


	public void setThirdskill(String thirdskill) {
		this.thirdskill = thirdskill;
	}


	public String getAdditionalskill() {
		return additionalskill;
	}


	public void setAdditionalskill(String additionalskill) {
		this.additionalskill = additionalskill;
	}


	public CTUserEntity getUser() {
		return user;
	}


	public void setUser(CTUserEntity user) {
		this.user = user;
	}


	public String getUniversity() {
		return university;
	}


	public void setUniversity(String university) {
		this.university = university;
	}


	public boolean getEu() {
		return eu;
	}


	public void setEu(boolean eu) {
		this.eu = eu;
	}


	public void setId(Long id) {
		this.id = id;
	}


	public String getMothertounge() {
		return mothertounge;
	}


	public void setMothertounge(String mothertounge) {
		this.mothertounge = mothertounge;
	}


	public String getFirstlanguage() {
		return firstlanguage;
	}


	public void setFirstlanguage(String firstlanguage) {
		this.firstlanguage = firstlanguage;
	}


	public String getSecondlanguage() {
		return secondlanguage;
	}


	public void setSecondlanguage(String secondlanguage) {
		this.secondlanguage = secondlanguage;
	}


	public String getBirthday() {
		return birthday;
	}


	public void setBirthday(String birthday) {
    //of the form("YYYY-MM-DD");
		this.birthday = birthday;
	}


	public String getNationality() {
		return nationality;
	}


	public void setNationality(String nationality) {
		this.nationality = nationality;
	}


	public String getEmailid() {
		return emailid;
	}


	public void setEmailid(String emailid) {
		this.emailid = emailid;
	}



}
