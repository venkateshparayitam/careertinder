package core.supplementary;


public class ResponseCode{
	String status_code;
	String auth_code;
	String user_type;
    String method;

	public String getStatus_code() {
		return status_code;
	}

	public void setStatus_code(String status_code) {
		this.status_code = status_code;
	}
    
    public String getMethod() {
		return method;
	}
    
    public void setMethod(String method) {
		this.method = method;
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

	
	
}