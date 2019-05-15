package core.controllers;


import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import core.entities.CTApplicantEntity;
import core.entities.CTUserEntity;
import core.repositories.*;

/**
 * 
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
			
		CTApplicantEntity applicant = new CTApplicantEntity();
		
		try
		{
			
			
			applicant.setName(user.getName());
			applicant.setEmailid(user.getEmailid());
			applicant.setUser(user);
			
			applicant_repository.save(applicant);
			user.setApplicant(applicant);
			user_repository.save(user);
			
		}
		catch(Exception ex)
		{
			ResponseCode response = new ResponseCode();
			response.setResponse("Record Not Saved");
			return response;
		}
		
		ResponseCode response_success = new ResponseCode();
		response_success.setResponse("Record Saved");
		return response_success;	
		
	 }
	
	
	@GetMapping(path = "/display/", produces = "text/plain")
	public String Acknowledge() {
	    return "It is working";
	}
	
	
public class ResponseCode{
	String response;

	public String getResponse() {
		return response;
	}

	public void setResponse(String response) {
		this.response = response;
	}
	
}

}


