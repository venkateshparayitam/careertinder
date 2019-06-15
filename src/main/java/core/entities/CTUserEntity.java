package core.entities;

import java.io.Serializable;
import java.util.List;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonBackReference;

/**
 * Entity implementation class for Entity: UserEntity
 * @author: Pravin Garad.
 */

@Entity
@Table(name="ctuser",  uniqueConstraints=@UniqueConstraint(columnNames="emailid"))

public class CTUserEntity implements Serializable {
	
	private static final long serialVersionUID = 1L;    
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name = "id",  nullable = true)
	private Long id;
	
	@Column(name = "name",  nullable = true)
	private String name;

	@Column(name = "emailid",  nullable = true)
	private String emailid;
	
	@Column(name = "password",  nullable = true)
	private String password;
	
	@Column(name = "userType",  nullable = true)
	private String userType;
	
	@Column(name = "authtoken",  nullable = true)
	private String authtoken;
	
	@Column(name = "profcreated", nullable = true)
	private String profcreated;

	
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    @JsonBackReference
    private CTApplicantEntity applicant;
  
    
    @OneToMany (mappedBy = "user_company")
    List <CTCompanyEntity> company;
    

	public Long getId() {
		return id;
	}

	
	public CTApplicantEntity getApplicant() {
		return applicant;
	}

	public void setApplicant(CTApplicantEntity applicant) {
		this.applicant = applicant;
	}
	
	
	public String getEmailid() {
		return emailid;
	}

	public void setEmailid(String emailid) {
		this.emailid = emailid;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getUserType() {
		return userType;
	}

	public void setUserType(String userType) {
		this.userType = userType;
	}

	public String getAuthToken() {
		return authtoken;
	}

	public void setAuthToken(String authToken) {
		this.authtoken = authToken;
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}


	public List<CTCompanyEntity> getCompany() {
		return company;
	}


	public void setCompany(List<CTCompanyEntity> company) {
		this.company = company;
	}


	public String getProfcreated() {
		return profcreated;
	}


	public void setProfcreated(String profcreated) {
		this.profcreated = profcreated;
	}	
   
}
