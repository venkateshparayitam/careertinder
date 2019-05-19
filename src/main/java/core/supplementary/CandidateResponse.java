package core.supplementary;

import java.util.UUID;

/**
 * @author: Bora Bejleri
 */

public class CandidateResponse {

	private String message;
	private String auth_token = UUID.randomUUID().toString();
	
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
