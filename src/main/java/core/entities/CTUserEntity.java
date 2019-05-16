package core.entities;

import java.io.Serializable;
import java.util.List;

import javax.persistence.*;

/**
 * Entity implementation class for Entity: UserEntity
 * @author: Pravin Garad.
 */

@Entity
@Table(name="ctuser", catalog="career_tinder")
public class CTUserEntity implements Serializable {
	
	private static final long serialVersionUID = 1L;    
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name = "id", updatable = false, nullable = true)
	private Long id;
	
	@Column(name = "name", updatable = false, nullable = true)
	private String name;
	
	
	@Column(name = "emailid", updatable = false, nullable = true)
	private String emailid;
	
	@Column(name = "password", updatable = false, nullable = true)
	private String password;
	
	@Column(name = "userType", updatable = false, nullable = true)
	private String userType;
	
	@Column(name = "authToken", updatable = false, nullable = true)
	private String authToken;

	
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private CTApplicantEntity applicant;    
    

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
		return authToken;
	}

	public void setAuthToken(String authToken) {
		this.authToken = authToken;
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
   
}
