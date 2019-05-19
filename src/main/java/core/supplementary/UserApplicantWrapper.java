package core.supplementary;

import com.fasterxml.jackson.annotation.JsonProperty;

import core.entities.CTApplicantEntity;
import core.entities.CTUserEntity;

public class UserApplicantWrapper {
	
	@JsonProperty("user")
	private CTUserEntity user;
	@JsonProperty("applicant")
	private CTApplicantEntity applicant;
	private String message;
	
//	public UserApplicantWrapper(CTUserEntity user, CTApplicantEntity applicant) {
//		this.user = user;
//		this.applicant = applicant;
//	}
	
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public CTUserEntity getUser() {
		return user;
	}
	public void setUser(CTUserEntity user) {
		this.user = user;
	}
	public CTApplicantEntity getApplicant() {
		return applicant;
	}
	
	public void setApplicant(CTApplicantEntity applicant) {
		this.applicant = applicant;
	}

	

}
