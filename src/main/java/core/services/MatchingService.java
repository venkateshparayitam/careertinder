package core.services;

import java.text.DecimalFormat;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.common.math.Stats;

import core.entities.CTApplicantEntity;
import core.entities.CTCompanyEntity;
import core.entities.CTMatchingEntity;
import core.repositories.ApplicantRepository;
import core.repositories.CompanyRepository;
import core.repositories.MatchingRepository;
import info.debatty.java.stringsimilarity.NormalizedLevenshtein;

/**
 * Service to provide the maching logic. 
 * Technique: Normalized Levenshtein Distance.
 * The output gives a number from 0 - 100 of how DIFFERENT two strings are.
 * The lower the number, the more SIMILAR strings are.
 * 
 * @author: Bora Bejleri
 */

@Service
public class MatchingService {
	
	@Autowired
	private ApplicantRepository applicantRepository;
	
	@Autowired
	private CompanyRepository companyRepository;
	
	@Autowired
	private MatchingRepository matchingRepository;
	
	private double distance1;
	private double distance2;
	private double distance3;
	private double response;
	private double result;
	private double percentage;

	public void calculateMatchingPercentage() {
		
		NormalizedLevenshtein ld = new NormalizedLevenshtein();	
		DecimalFormat df = new DecimalFormat("####0.00");
		
		List<CTApplicantEntity> applicants = applicantRepository.findAll();
		List<CTCompanyEntity> companies = companyRepository.findAll();
		
		if((!applicants.isEmpty()) && (!companies.isEmpty())) {
			
			for (CTApplicantEntity applicant : applicants) {
				
				for (CTCompanyEntity company : companies) {
					
					try {
					
					CTMatchingEntity databaseMatching = new CTMatchingEntity();
					
				    distance1 = ld.distance(applicant.getFirstskill(), company.getSkill1());
				    distance2 = ld.distance(applicant.getSecondskill(), company.getSkill2());
				    distance3 = ld.distance(applicant.getThirdskill(), company.getSkill3());
				    
				    result = Stats.meanOf(distance1, distance2, distance3);
				    response = Double.valueOf(df.format(result));	
				    percentage = response * 100;
				    
				    databaseMatching.setApplicant_id(applicant.getId());
				    databaseMatching.setCompany_id(company.getId());
				    databaseMatching.setPercentage(percentage);
				    matchingRepository.save(databaseMatching);	
				    
				    //System.out.println(applicant.getName() + " - " + company.getCompanyname());
				    //System.out.println(percentage);
				    
					   } catch(Exception e) {
						   e.printStackTrace();
					}
				   			    		    
				}
								
			} 
			
		}	
					
	}
	
}
