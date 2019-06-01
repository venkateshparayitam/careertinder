package core.supplementary;

import core.entities.CTCompanyEntity;
import java.util.*;


public class ResponseCode{
	String response_code;
	String auth_code;
	String user_type;
    String api_method;
    String message;
    String is_profile_created;
    
    ArrayList<CTCompanyEntity> joblist = new ArrayList<CTCompanyEntity>();

	public String getStatus_code() {
		return response_code;
	}

	public void setStatus_code(String response_code) {
		this.response_code = response_code;
	}
    
    public String getMethod() {
		return api_method;
	}
    
    public void setMethod(String api_method) {
		this.api_method = api_method;
	}

	public String getAuth_code() {
		return auth_code;
	}

	public void setAuth_code(String auth_code) {
		this.auth_code = auth_code;
	}

	public String getUser_type() {
		return user_type;
	}

	public void setUser_type(String user_type) {
		this.user_type = user_type;
	}

	public ArrayList<CTCompanyEntity> getJoblist() {
		return joblist;
	}

	public void setJoblist(ArrayList<CTCompanyEntity> joblist) {
		this.joblist = joblist;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getIs_profile_created() {
		return is_profile_created;
	}

	public void setIs_profile_created(String is_profile_created) {
		this.is_profile_created = is_profile_created;
	}

	
	
}