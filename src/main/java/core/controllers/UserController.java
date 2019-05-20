package core.controllers;


import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import core.entities.CTApplicantEntity;
import core.entities.CTCompanyEntity;
import core.entities.CTUserEntity;
import core.repositories.*;
import core.supplementary.RandomString;

/**
 * Creation of User Controller to API calls.
 * @author: Pravin Garad.
 */


@RestController
@RequestMapping("/api")
public class UserController {
	
	@Autowired
	UserRepository user_repository;
	
	@Autowired
	ApplicantRepository applicant_repository;
	
	@PostMapping(path ="/signUp", produces = "application/json")
	public ResponseCode signup(@Valid @RequestBody CTUserEntity user) {
		
		int random = (int )(Math.random() * 20 + 1);
		
		
		try
		{
			if(user.getUserType().equals("jobseeker"))
			{
				CTApplicantEntity applicant = new CTApplicantEntity();
				
				applicant.setName(user.getName());
				applicant.setEmailid(user.getEmailid());
				applicant.setUser(user);
				applicant_repository.save(applicant);
				user.setApplicant(applicant);
				user_repository.save(user);
				user_repository.updateUser(new RandomString().getAlphaNumericString(random), user.getEmailid());
							
			}
			else if(user.getUserType().equals("employer"))
			{
					CTCompanyEntity company = new CTCompanyEntity();
					company.setCompanyname(user.getName());
					
					user_repository.save(user);
					user_repository.updateUser(new RandomString().getAlphaNumericString(random), user.getEmailid()); 
					
					
			}
			
		}
		catch(Exception ex)
		{
			ResponseCode response = new ResponseCode();
			response.setStatus_code("email_exists");
            response.setMethod("sign_up");
			return response;
		}
		
		ResponseCode response_success = new ResponseCode();
		response_success.setStatus_code("account_created");
		response_success.setMethod("sign_up");
		return response_success;	
		
	 }
	
	@PostMapping(path ="/login", produces = "application/json")
	public ResponseCode login(@Valid @RequestBody CTUserEntity user) {
		
			CTUserEntity password = new CTUserEntity();
			password = user_repository.checkPassword(user.getEmailid(), user.getPassword());
			
			if (password != null)
			{
				ResponseCode response = new ResponseCode();
				response.setStatus_code("Success");
				response.setAuth_code(password.getAuthToken());
				response.setUser_type(password.getUserType());
                response.setMethod("login");
				return response;
			}
			else
			{
				ResponseCode response = new ResponseCode();
				response.setStatus_code("Invalid emailid or password");
				return response;
			}
		
	}
	
	
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

}


