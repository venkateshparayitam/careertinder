package core.supplementary;

import core.entities.CTCompanyEntity;
import java.util.*;

import com.fasterxml.jackson.annotation.JsonProperty;


public class ResponseCode{
	
	
	String response_code;
	String auth_code;
	String user_type;
    String api_method;
    String message;
    String is_profile_created;
    
    ArrayList<CTCompanyEntity> joblist = new ArrayList<CTCompanyEntity>();

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
	public String getAuth_code() {
		return auth_code;
	}

	@JsonProperty
	public void setAuth_code(String auth_code) {
		this.auth_code = auth_code;
	}

	@JsonProperty
	public String getUser_type() {
		return user_type;
	}

	@JsonProperty
	public void setUser_type(String user_type) {
		this.user_type = user_type;
	}

	@JsonProperty
	public ArrayList<CTCompanyEntity> getJoblist() {
		return joblist;
	}

	@JsonProperty
	public void setJoblist(ArrayList<CTCompanyEntity> joblist) {
		this.joblist = joblist;
	}

	@JsonProperty
	public String getMessage() {
		return message;
	}

	@JsonProperty
	public void setMessage(String message) {
		this.message = message;
	}

	@JsonProperty
	public String getIs_profile_created() {
		return is_profile_created;
	}

	@JsonProperty
	public void setIs_profile_created(String is_profile_created) {
		this.is_profile_created = is_profile_created;
	}

	
	
}