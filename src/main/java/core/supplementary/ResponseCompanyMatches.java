package core.supplementary;

import core.entities.CTApplicantEntity;
import core.entities.CTCompanyEntity;
import java.util.*;

import com.fasterxml.jackson.annotation.JsonProperty;


public class ResponseCompanyMatches{
	
	
	String response_code;
    String api_method;
    String message;
   
    List<CTCompanyEntity> joblist = new ArrayList<CTCompanyEntity>();
   

    @JsonProperty("response_code")
	public String getStatus_code() {
		return response_code;
	}

    @JsonProperty("response_code")
	public void setStatus_code(String response_code) {
		this.response_code = response_code;
	}
    
	@JsonProperty("api_method")
    public String getMethod() {
		return api_method;
	}
    
	@JsonProperty("api_method")
    public void setMethod(String api_method) {
		this.api_method = api_method;
	}

	@JsonProperty
	public String getMessage() {
		return message;
	}

	@JsonProperty
	public void setMessage(String message) {
		this.message = message;
	}

	public List<CTCompanyEntity> getJoblist() {
		return joblist;
	}

	public void setJoblist(List<CTCompanyEntity> joblist) {
		this.joblist = joblist;
	}

	
}