package core.supplementary;

/**
 * @author: Bora Bejleri
 */

public class CandidateResponse {
    
	private String response_code;
	private String message;
	private String auth_token;
	private String api_method;
	
	
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
	
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public String getAuth_token() {
		return auth_token;
	}
	public void setAuth_token(String auth_token) {
		this.auth_token = auth_token;
	}
	
	
	
	
}
