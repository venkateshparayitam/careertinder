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
 * Entity implementation class for Entity: ApplicantEntity
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
	
    
	
	@Column(name = "job_id", updatable = false, nullable = true)
	private int jobid;
	
	@Column(name="job_description", nullable = true)
	private String name;
	
	@Column(name="work_experience")
	private int workexperience;
	
	@ManyToOne (cascade=CascadeType.ALL)
	  private CTUserEntity user_company;
	
    /*
	@ManyToOne (cascade=CascadeType.ALL)
	@JoinColumn(name="id")
    private CTUserEntity user_company;
	*/
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public int getJobid() {
		return jobid;
	}

	public void setJobid(int jobid) {
		this.jobid = jobid;
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

	
	public CTUserEntity getUser()
	   {
	      return user_company;
	   }
	
	public void setUser(CTUserEntity user)
	   {
	      this.user_company = user;
	   }
	


}
