package core.controllers;

import java.util.Objects;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import core.entities.CTApplicantEntity;
import core.repositories.ApplicantRepository;

@RestController
public class ApplicantController {
	
	    @Autowired 
	    protected ApplicantRepository applicant_repository;
	 
	    @RequestMapping(path = "/candidate/{id}", method = RequestMethod.POST, produces = "application/json")
	    public CTApplicantEntity setCandidateDetails(@PathVariable long id, @Valid @RequestBody final CTApplicantEntity candidate) {
	    	final CTApplicantEntity databaseApplicant = applicant_repository.getOne(id);
	    	if(!Objects.isNull(databaseApplicant)) {
	    	databaseApplicant.setWorkexperience(candidate.getWorkexperience());
	    	databaseApplicant.setBirthday(candidate.getBirthday());
	    	databaseApplicant.setFirstskill(candidate.getFirstskill());
	    	databaseApplicant.setSecondskill(candidate.getSecondskill());
	    	databaseApplicant.setAdditionalskill(candidate.getAdditionalskill());
	    	databaseApplicant.setAddress(candidate.getAddress());
	    	databaseApplicant.setBio(candidate.getBio());
	    	databaseApplicant.setEu(candidate.isEu());
	    	databaseApplicant.setFirstlanguage(candidate.getFirstlanguage());
	    	databaseApplicant.setSecondlanguage(candidate.getSecondlanguage());
	    	databaseApplicant.setNationality(candidate.getNationality());
	    	databaseApplicant.setUniversity(candidate.getUniversity());
	    	databaseApplicant.setMothertounge(candidate.getMothertounge());
	    	databaseApplicant.setQualification(candidate.getQualification());
	    	}
			return applicant_repository.save(databaseApplicant);
	    	
	    }
	}
