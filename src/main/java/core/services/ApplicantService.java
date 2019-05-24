package core.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import core.entities.CTApplicantEntity;
import core.repositories.ApplicantRepository;

/**
 * @author: Bora Bejleri
 */

@Service
public class ApplicantService {
	
	@Autowired
	ApplicantRepository applicantRepository;
	
	public List<CTApplicantEntity> getAllApplicantProfiles(){
		
		
		return this.applicantRepository.findAll();
		
	}

}
