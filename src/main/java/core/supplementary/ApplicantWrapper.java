package core.supplementary;

import java.util.List;

import core.entities.CTApplicantEntity;

/**
 * @author: Bora Bejleri
 */

public class ApplicantWrapper {
	
	private List<CTApplicantEntity> applicant_profiles;
	
	private String api_method;
	private String response_code;
	private String response_message;

	public String getApi_method() {
		return api_method;
	}

	public void setApi_method(String api_method) {
		this.api_method = api_method;
	}

	public String getResponse_code() {
		return response_code;
	}

	public void setResponse_code(String response_code) {
		this.response_code = response_code;
	}

	public String getResponse_message() {
		return response_message;
	}

	public void setResponse_message(String response_message) {
		this.response_message = response_message;
	}

	public List<CTApplicantEntity> getApplicant_profiles() {
		return applicant_profiles;
	}

	public void setApplicant_profiles(List<CTApplicantEntity> applicant_profiles) {
		this.applicant_profiles = applicant_profiles;
	}

}
