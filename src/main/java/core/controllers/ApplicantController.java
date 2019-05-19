package core.controllers;


import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import core.entities.CTApplicantEntity;
import core.entities.CTUserEntity;
import core.repositories.ApplicantRepository;
import core.repositories.UserRepository;
import core.supplementary.UserApplicantWrapper;

/**
 * @author: Bora Bejleri
 */

@RestController
public class ApplicantController {
	
	    @Autowired 
	    protected ApplicantRepository applicant_repository;
	    
	    @Autowired
	    protected UserRepository user_repository;
	 
	    @RequestMapping(path = "/candidate/create", method = RequestMethod.POST, produces = "application/json")
	    public UserApplicantWrapper setCandidateDetails(@Valid @RequestBody UserApplicantWrapper candidate) {
	    	
	    	if(candidate != null) {
	    	CTUserEntity user_with_token = user_repository.getByToken(candidate.getUser().getAuthToken());
	    	if(user_with_token != null) {
	    	String email = user_with_token.getEmailid(); 
	    	CTApplicantEntity databaseApplicant = applicant_repository.findByEmail(email);
	    	if(databaseApplicant != null) {
	    	try {
	    	databaseApplicant.setWorkexperience(candidate.getApplicant().getWorkexperience());
	    	databaseApplicant.setBirthday(candidate.getApplicant().getBirthday());
	    	databaseApplicant.setFirstskill(candidate.getApplicant().getFirstskill());
	    	databaseApplicant.setSecondskill(candidate.getApplicant().getSecondskill());
	    	databaseApplicant.setAdditionalskill(candidate.getApplicant().getAdditionalskill());
	    	databaseApplicant.setAddress(candidate.getApplicant().getAddress());
	    	databaseApplicant.setBio(candidate.getApplicant().getBio());
	    	databaseApplicant.setEu(candidate.getApplicant().isEu());
	    	databaseApplicant.setFirstlanguage(candidate.getApplicant().getFirstlanguage());
	    	databaseApplicant.setSecondlanguage(candidate.getApplicant().getSecondlanguage());
	    	databaseApplicant.setNationality(candidate.getApplicant().getNationality());
	    	databaseApplicant.setUniversity(candidate.getApplicant().getUniversity());
	    	databaseApplicant.setMothertounge(candidate.getApplicant().getMothertounge());
	    	databaseApplicant.setQualification(candidate.getApplicant().getQualification());
	    	applicant_repository.save(databaseApplicant);
	    	}
	    	catch(Exception e) {
	    	    
	    		UserApplicantWrapper response = new UserApplicantWrapper();
	    		response.setMessage("Something went wrong");
	    	    return response;
	           } 
	    	   UserApplicantWrapper response = new UserApplicantWrapper();
	    	   response.setMessage("Success!");
	    	   return response;
	    	
	    	  }
	    	}
	    	
	    }
	    	UserApplicantWrapper response = new UserApplicantWrapper();
	    	response.setMessage("No entry");
	    	return response;
	    }
	}

 
   
