package core.controllers;


import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import core.entities.CTApplicantEntity;
import core.entities.CTUserEntity;
import core.repositories.ApplicantRepository;
import core.repositories.UserRepository;
import core.services.ApplicantService;
import core.supplementary.ApplicantWrapper;

/**
 * @author: Bora Bejleri
 */

@RestController
public class ApplicantController {
	
	    @Autowired 
	    protected ApplicantRepository applicant_repository;
	    
	    @Autowired
	    protected UserRepository user_repository;
	    
	    @Autowired ApplicantService applicantService;
	    
	    @RequestMapping(path = "/candidate/create/{token}", method = RequestMethod.POST, produces = "application/json")
	    public ArrayList<String> setCandidateDetails(@PathVariable(value = "token") String token, @Valid @RequestBody CTApplicantEntity candidate){
	    	ArrayList<String> response = new ArrayList<String>();
	    	if(candidate != null) {
	    		CTUserEntity user_with_token = user_repository.getByToken(token);
	    		if (user_with_token != null) {
	    			String email = user_with_token.getEmailid();
	    			CTApplicantEntity databaseApplicant = applicant_repository.findByEmail(email);
	    			if(databaseApplicant != null) {
	    				databaseApplicant.setWorkexperience(candidate.getWorkexperience());
	    		    	databaseApplicant.setBirthday(candidate.getBirthday());
	    		    	databaseApplicant.setFirstskill(candidate.getFirstskill());
	    		    	databaseApplicant.setSecondskill(candidate.getSecondskill());
	    		    	databaseApplicant.setAdditionalskill(candidate.getAdditionalskill());
	    		    	databaseApplicant.setAddress(candidate.getAddress());
	    		    	databaseApplicant.setBio(candidate.getBio());
	    		    	databaseApplicant.setEu(candidate.getEu());
	    		    	databaseApplicant.setFirstlanguage(candidate.getFirstlanguage());
	    		    	databaseApplicant.setSecondlanguage(candidate.getSecondlanguage());
	    		    	databaseApplicant.setNationality(candidate.getNationality());
	    		    	databaseApplicant.setUniversity(candidate.getUniversity());
	    		    	databaseApplicant.setMothertounge(candidate.getMothertounge());
	    		    	databaseApplicant.setQualification(candidate.getQualification());
	    		    	applicant_repository.save(databaseApplicant);
	    		    	
	    		    	response.add(token);
	    		    	response.add("Success!");
	    		    	return response;
	    			} response.add("Candidate does not exist");
	    			  return response;
	    		}response.add("Token does not correspond to any candidate");
  			     return response;
	    	}
	    	
	    	response.add("Invalid request body");
	    	return response;	    	
	    }
//	    @RequestMapping(path = "/candidate/create", method = RequestMethod.POST, produces = "application/json")
//	    public UserApplicantWrapper setCandidateDetails(@Valid @RequestBody UserApplicantWrapper candidate) {
//	    	
//	    	if(candidate != null) {
//	    	CTUserEntity user_with_token = user_repository.getByToken(candidate.getUser().getAuthToken());
//	    	if(user_with_token != null) {
//	    	String email = user_with_token.getEmailid(); 
//	    	CTApplicantEntity databaseApplicant = applicant_repository.findByEmail(email);
//	    	if(databaseApplicant != null) {
//	    	try {
//	    	databaseApplicant.setWorkexperience(candidate.getApplicant().getWorkexperience());
//	    	databaseApplicant.setBirthday(candidate.getApplicant().getBirthday());
//	    	databaseApplicant.setFirstskill(candidate.getApplicant().getFirstskill());
//	    	databaseApplicant.setSecondskill(candidate.getApplicant().getSecondskill());
//	    	databaseApplicant.setAdditionalskill(candidate.getApplicant().getAdditionalskill());
//	    	databaseApplicant.setAddress(candidate.getApplicant().getAddress());
//	    	databaseApplicant.setBio(candidate.getApplicant().getBio());
//	    	databaseApplicant.setEu(candidate.getApplicant().isEu());
//	    	databaseApplicant.setFirstlanguage(candidate.getApplicant().getFirstlanguage());
//	    	databaseApplicant.setSecondlanguage(candidate.getApplicant().getSecondlanguage());
//	    	databaseApplicant.setNationality(candidate.getApplicant().getNationality());
//	    	databaseApplicant.setUniversity(candidate.getApplicant().getUniversity());
//	    	databaseApplicant.setMothertounge(candidate.getApplicant().getMothertounge());
//	    	databaseApplicant.setQualification(candidate.getApplicant().getQualification());
//	    	applicant_repository.save(databaseApplicant);
//	    	}
//	    	catch(Exception e) {
//	    	    
//	    		UserApplicantWrapper response = new UserApplicantWrapper();
//	    		response.setMessage("Something went wrong" + e);
//	    	    return response;
//	           } 
//	    	   UserApplicantWrapper response = new UserApplicantWrapper();
//	    	   response.setMessage("Success!");
//	    	   return response;
//	    	
//	    	  }
//	    	}
//	    	
//	    }
//	    	UserApplicantWrapper response = new UserApplicantWrapper();
//	    	response.setMessage("No entry");
//	    	return response;
//	    }
	    
	    @SuppressWarnings("deprecation")
		@RequestMapping(path="/candidate/page", method = RequestMethod.GET, produces="application/json")
	    public Page<CTApplicantEntity> getPagination(){
	    	return this.applicant_repository.findAll(new PageRequest(0, 2));
	    }
	    
	    
	    @RequestMapping(path = "/candidate/all", method = RequestMethod.GET, produces = "application/json")
	    public ApplicantWrapper getAll(){
	    	
	    	List<CTApplicantEntity> profiles = new ArrayList<CTApplicantEntity>();
	    	List<CTApplicantEntity> student_info = new ArrayList<CTApplicantEntity>();
	    	ApplicantWrapper applicant_response = new ApplicantWrapper();
	    	CTApplicantEntity databaseApplicant = new CTApplicantEntity();
	    	profiles = this.applicantService.getAllApplicantProfiles();
	    	
	    	for (CTApplicantEntity student : profiles) {
	    		
	    		databaseApplicant.setName(student.getUser().getName());
	    		databaseApplicant.setWorkexperience(student.getWorkexperience());
		    	databaseApplicant.setBirthday(student.getBirthday());
		    	databaseApplicant.setFirstskill(student.getFirstskill());
		    	databaseApplicant.setSecondskill(student.getSecondskill());
		    	databaseApplicant.setThirdskill(student.getThirdskill());
		    	databaseApplicant.setAdditionalskill(student.getAdditionalskill());
		    	databaseApplicant.setAddress(student.getAddress());
		    	databaseApplicant.setBio(student.getBio());
		    	databaseApplicant.setEu(student.getEu());
		    	databaseApplicant.setFirstlanguage(student.getFirstlanguage());
		    	databaseApplicant.setSecondlanguage(student.getSecondlanguage());
		    	databaseApplicant.setNationality(student.getNationality());
		    	databaseApplicant.setUniversity(student.getUniversity());
		    	databaseApplicant.setMothertounge(student.getMothertounge());
		    	databaseApplicant.setQualification(student.getQualification());
		    	
		    	student_info.add(databaseApplicant);
		    	applicant_response.setApplicant_profiles(student_info);
	    		
	    	}
			return applicant_response;
	    	
	    }
	    
	}

 
   
