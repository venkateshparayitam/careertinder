package entities;

import java.io.Serializable;
import java.time.LocalDate;

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
 * Entity implementation class for Entity: StudentEntity
 * @author: Bora Bejleri
 */

@Entity
@Table(name="student", catalog="career_tinder")
public class StudentEntity implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name = "student_id", updatable = false, nullable = false)
	private Long id;
	
	@Column(name="student_name", nullable = false)
	private String name;
	
	@Column(name="email", nullable = false)
	private String email;
	
	@Column(name="password", nullable = false)
	private String password;
	
	@Column(name="work_experience")
	private int workexperience;
	
	@Column(name="university", nullable = false)
	private String university;
	
	@Column(name="euCitizen", nullable = false)
	private boolean eu;
	
	@Column(name="qualification")
	private String qualification;
	
	@Column(name="address", nullable = false)
	private String address;
	
	@Column(name="bio")
	private String bio;
	
	@Column(name="first_skill", nullable = false)
	private String firstskill;
	
	@Column(name="second_skill", nullable = false)
	private String secondskill;
	
	@Column(name="third_skill", nullable = false)
	private String thirdskill;
	
	@Column(name="additional_skill")
	private String additionalskill;
	
	@Column(name="mother_tounge", nullable = false)
	private String mothertounge;
	
	@Column(name="first_language")
	private String firstlanguage;
	
	@Column(name="second_language")
	private String secondlanguage;
	
	@Column(name="birthday", nullable = false)
	private LocalDate birthday;
	
	@Column(name="nationality")
	private String nationality;
	
	@OneToOne(mappedBy = "student", cascade = CascadeType.ALL,
            fetch = FetchType.LAZY, optional = false)
            private UserEntity user;
	
	
	public Long getId() {
		return id;
	}


	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}


	public String getEmail() {
		return email;
	}


	public void setEmail(String email) {
		this.email = email;
	}


	public String getPassword() {
		return password;
	}


	public void setPassword(String password) {
		this.password = password;
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


	public UserEntity getUser() {
		return user;
	}


	public void setUser(UserEntity user) {
		this.user = user;
	}


	public String getUniversity() {
		return university;
	}


	public void setUniversity(String university) {
		this.university = university;
	}


	public boolean isEu() {
		return eu;
	}


	public void setEu(boolean eu) {
		this.eu = eu;
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


	public LocalDate getBirthday() {
		return birthday;
	}


	public void setBirthday(LocalDate birthday) {
    //of the form("YYYY-MM-DD");
		this.birthday = birthday;
	}


	public String getNationality() {
		return nationality;
	}


	public void setNationality(String nationality) {
		this.nationality = nationality;
	}



}
