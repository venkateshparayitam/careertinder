package core.controllers;


import java.util.ArrayList;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import core.entities.CTApplicantEntity;
import core.entities.CTCompanyEntity;
import core.entities.CTUserEntity;
import core.repositories.*;
import core.supplementary.*;

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
				user.setProfcreated("No");
				user_repository.save(user);
				user_repository.updateUser(new RandomString().getAlphaNumericString(random), user.getEmailid(), "No");
							
			}
			else if(user.getUserType().equals("employer"))
			{
					CTCompanyEntity company = new CTCompanyEntity();
					company.setCompanyname(user.getName());
					user_repository.save(user);
					user_repository.updateUser(new RandomString().getAlphaNumericString(random), user.getEmailid(), "Not Applicable"); 
					
					
			}
			
		}
		catch(Exception ex)
		{
			ResponseCode response = new ResponseCode();
			response.setStatus_code("Fail");
            response.setMethod("sign_up");
            response.setMessage("Email already exists");
			return response;
		}
		
		ResponseCode response_success = new ResponseCode();
		response_success.setStatus_code("Success");
		response_success.setMethod("sign_up");
		response_success.setMessage("Account Created");
		return response_success;	
		
	 }
	
	@PostMapping(path ="/login", produces = "application/json")
	public ResponseCode login(@Valid @RequestBody CTUserEntity user) {
		
			CTUserEntity password = new CTUserEntity();
			password = user_repository.checkPassword(user.getEmailid(), user.getPassword());
			
			ResponseCode response = new ResponseCode();
			
			if (password != null)
			{
				if (password.getProfcreated().equalsIgnoreCase("No"))
				{
					response.setIs_profile_created("No");
				}
				else
				{
					response.setIs_profile_created("Yes");
				}
				response.setStatus_code("Success");
				response.setAuth_code(password.getAuthToken());
				response.setUser_type(password.getUserType());
                response.setMethod("login");
				return response;
			}
			else
			{
				response.setStatus_code("Fail");
				response.setMessage("Invalid emailid or password");
				response.setMethod("login");
				return response;
			}
		
	}
	
	
}


