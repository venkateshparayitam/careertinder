package core.controllers;


import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
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
import core.supplementary.CandidateResponse;
import core.supplementary.ResponseCode;

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
	    
	    /**
	     * Updates a user into an applicant 
	     * @param token 
	     * @return CandidateResponse - authtoken, responsecode, message, apimethod 
	     * @throws User/Applicant 404 not found
	     */
	    
	    @RequestMapping(path = "/candidate/create/{token}", method = RequestMethod.POST, produces = "application/json")
	    public CandidateResponse setCandidateDetails(@PathVariable(value = "token") String token, @Valid @RequestBody CTApplicantEntity candidate){
	    	CandidateResponse response = new CandidateResponse();
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
	    		    	databaseApplicant.setThirdskill(candidate.getThirdskill());
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
	    		    	databaseApplicant.setJobType(candidate.getJobType());
	    		    	databaseApplicant.setPhone(candidate.getPhone());
	    		    	databaseApplicant.setImageUrl(candidate.getImageUrl());
	    		    	applicant_repository.save(databaseApplicant);
	    		    	
	    		    	user_with_token.setProfcreated("Yes");
	    		    	user_repository.save(user_with_token); 
	    		    	
	    		    	response.setAuth_token(token);
	    		    	response.setResponse_code("Success");
	    		    	response.setApi_method("/candidate/create/" + token);
	    		    	return response;
	    		    	
	    			} response.setApi_method("/candidate/create/" + token);
	    			  response.setResponse_code("Failed");
	    			  response.setMessage("Job seeker does not exist.");
	    			  return response;
	    			  
	    		}response.setApi_method("/candidate/create/" + token);
	    		 response.setResponse_code("Failed");
	    		 response.setMessage("User does not exist.");
  			     return response;
	    	}
	    	
	    	response.setMessage("Invalid job seeker data");
	    	return response;	    	
	    }
	    
	    /**
	     * Returns an applicant profile
	     * @param token 
	     * @return ResponseCode - authtoken, responsecode, message, apimethod 
	     */
	    
	    @RequestMapping(path = "/candidate/display/{token}", method = RequestMethod.GET, produces = "application/json")
	    public ResponseCode getCandidateDetails(@PathVariable(value = "token") String token) {
			ResponseCode res = new ResponseCode();
	    	
	    		CTUserEntity user_with_token = user_repository.getByToken(token);
	    		if (user_with_token != null) {
	    			String email = user_with_token.getEmailid();
	    			CTApplicantEntity databaseApplicant = applicant_repository.findByEmail(email);
	    			if(databaseApplicant != null) {

	    		    	res.setMethod("GET_CANDIDATE_PROFILE");
	    		    	res.setApplicant(databaseApplicant);
	    		    	res.setStatus_code("Success");
	    		    	return res;
	    			} 
					else 
					{
					  res.setStatus_code("Failure");
					  res.setMessage("Candidate does not exist");
	    			  return res;
					}   			
	    		}
	    		res.setStatus_code("Failure");
	    		res.setMessage("Token does not correspond to any candidate");
  			    return res;    	    	
	    }	    
	    
	    /**
	     * Returns all applicant profiles for display 
	     * No matching logic added 
	     * @return ApplicantWrapper
	     */
	    
	    @RequestMapping(path = "/candidate/all", method = RequestMethod.GET, produces = "application/json")
	    public ApplicantWrapper getAll(){
	    	
	    	List<CTApplicantEntity> profiles = new ArrayList<CTApplicantEntity>();
	    	List<CTApplicantEntity> student_info = new ArrayList<CTApplicantEntity>();
	    	ApplicantWrapper applicant_response = new ApplicantWrapper();
	    	CTApplicantEntity databaseApplicant = new CTApplicantEntity();
	    	profiles = this.applicantService.getAllApplicantProfiles();
	    	
	    	if (!profiles.isEmpty()) {
	    	 
	    		for (CTApplicantEntity student : profiles) {
	    			
	    	    try {
	    		
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
		    	databaseApplicant.setJobType(student.getJobType());
		    	databaseApplicant.setImageUrl(student.getImageUrl());
		    	databaseApplicant.setPhone(student.getPhone());
		    	
		    	student_info.add(databaseApplicant);
		    	applicant_response.setApplicant_profiles(student_info);
		    	
	    	       } catch (Exception e) {
	    	    	   applicant_response.setApi_method("/candidate/all");
	    		       applicant_response.setResponse_code("Failed");
	    		       applicant_response.setResponse_message("Something went wrong." + e);
	    			   return applicant_response;
	    	       }
	    		
	         	}
	    		applicant_response.setApi_method("/candidate/all");
	    		applicant_response.setResponse_code("Success");
	    		return applicant_response;
	    	}
	    	applicant_response.setApi_method("/candidate/all");
	    	applicant_response.setResponse_code("Failed");
	    	applicant_response.setResponse_message("No jobseeker profiles at the moment!");
			return applicant_response;	    	
	    }	    
	}
