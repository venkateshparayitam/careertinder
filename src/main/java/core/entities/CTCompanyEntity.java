package core.entities;

import java.io.Serializable;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * Entity implementation class for Entity: ApplicantEntity.
 * @author: Pravin Garad
 */

@Entity
@Table(name="ctcompany")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class CTCompanyEntity implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name = "company_id",  nullable = true)
	private Long id;
	
	@Column(name = "companyname",  nullable = true)
	private String companyname;
	
	@Column(name = "location",  nullable = true)
	private String location;
	
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name = "job_id",  nullable = true)
	private int jobid;
	
	@Column(name = "jobtitle",  nullable = true)
	private String jobtitle;
	
	@Column(name="job_description", nullable = true)
	private String jobdescription;
	
	@Column(name = "skill1",  nullable = true)
	private String skill1;
	
	@Column(name = "skill2",  nullable = true)
	private String skill2;
	
	@Column(name = "skill3",  nullable = true)
	private String skill3;
	
	@Column(name="work_experience",  nullable = true)
	private String workexperience;
	
	@Column(name="education",  nullable = true)
	private String education;
	
	@Column(name="preferredlanguage1",  nullable = true)
	private String preferredlanguage1;
	
	@Column(name="preferredlanguage2",  nullable = true)
	private String preferredlanguage2;
	
	@Column(name="werkstudent",  nullable = true)
	private boolean werkstudent = false;
	
	@Column(name="internship",  nullable = true)
	private boolean internship = false;
	
	@Column(name="mandtinternship",  nullable = true)
	private boolean mandtinternship = false;
	
	@Column(name="hrcontact",  nullable = true)
	private String hrcontact;
	
	
	
	@ManyToOne (cascade=CascadeType.ALL)
	  private CTUserEntity user_company;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getCompanyname() {
		return companyname;
	}

	public String getHrcontact() {
		return hrcontact;
	}

	public void setHrcontact(String hrcontact) {
		this.hrcontact = hrcontact;
	}

	public void setCompanyname(String companyname) {
		this.companyname = companyname;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	
	public int getJobid() {
		return jobid;
	}

	public void setJobid(int jobid) {
		this.jobid = jobid;
	}

	public String getJobtitle() {
		return jobtitle;
	}

	public void setJobtitle(String jobtitle) {
		this.jobtitle = jobtitle;
	}

	public String getJobdescription() {
		return jobdescription;
	}

	public void setJobdescription(String jobdescription) {
		this.jobdescription = jobdescription;
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

	public String getWorkexperience() {
		return workexperience;
	}

	public void setWorkexperience(String workexperience) {
		this.workexperience = workexperience;
	}

	public String getEducation() {
		return education;
	}

	public void setEducation(String education) {
		this.education = education;
	}

	public String getPreferedlanguage1() {
		return preferredlanguage1;
	}

	public void setPreferedlanguage1(String preferredlanguage1) {
		this.preferredlanguage1 = preferredlanguage1;
	}

	public String getPreferedlanguage2() {
		return preferredlanguage2;
	}

	public void setPreferedlanguage2(String preferredlanguage2) {
		this.preferredlanguage2 = preferredlanguage2;
	}

	/*
	public CTUserEntity getUser_company() {
		return user_company;
	}
	*/
	
	public void setUser_company(CTUserEntity user_company) {
		this.user_company = user_company;
	}

	public boolean isWerkstudent() {
		return werkstudent;
	}

	public void setWerkstudent(boolean werkstudent) {
		this.werkstudent = werkstudent;
	}

	public boolean isInternship() {
		return internship;
	}

	public void setInternship(boolean internship) {
		this.internship = internship;
	}

	public boolean isMandtinternship() {
		return mandtinternship;
	}

	public void setMandtinternship(boolean mandtinternship) {
		this.mandtinternship = mandtinternship;
	}
	
	
	

}
