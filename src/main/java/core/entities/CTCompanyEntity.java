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

/**
 * Entity implementation class for Entity: ApplicantEntity.
 * @author: Pravin Garad
 */

@Entity
@Table(name="ctcompany", catalog="career_tinder")
public class CTCompanyEntity implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name = "company_id", updatable = false, nullable = true)
	private Long id;
	
	@Column(name = "companyname", updatable = false, nullable = true)
	private String companyname;
	
	@Column(name = "location", updatable = false, nullable = true)
	private String location;
	
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name = "job_id", updatable = false, nullable = true)
	private int jobid;
	
	@Column(name = "jobtitle", updatable = false, nullable = true)
	private String jobtitle;
	
	@Column(name="job_description", nullable = true)
	private String jobdescription;
	
	@Column(name = "skill1", updatable = false, nullable = true)
	private String skill1;
	
	@Column(name = "skill2", updatable = false, nullable = true)
	private String skill2;
	
	@Column(name = "skill3", updatable = false, nullable = true)
	private String skill3;
	
	@Column(name="work_experience", updatable = false, nullable = true)
	private String workexperience;
	
	@Column(name="education", updatable = false, nullable = true)
	private String education;
	
	@Column(name="preferredlanguage1", updatable = false, nullable = true)
	private String preferredlanguage1;
	
	@Column(name="preferredlanguage2", updatable = false, nullable = true)
	private String preferredlanguage2;
	
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

	public CTUserEntity getUser_company() {
		return user_company;
	}

	public void setUser_company(CTUserEntity user_company) {
		this.user_company = user_company;
	}
	

}
