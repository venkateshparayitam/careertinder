package core.controllers;

import core.entities.CTApplicantEntity;
import core.entities.CTCompanyEntity;
import core.entities.CTMatchingEntity;
import core.entities.CTUserEntity;
import core.repositories.ApplicantRepository;
import core.repositories.CompanyRepository;
import core.repositories.MatchingRepository;
import core.repositories.UserRepository;
import core.supplementary.ResponseCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import core.services.MatchingService;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

/**
 * @author: Bora Bejleri
 */

@RestController
// @RequestMapping("/api")
public class MatchingController {

	@Autowired
	MatchingRepository matchingRepository;
	
	@Autowired
	private MatchingService matchingService;
	
	@Autowired
	UserRepository userRepository;
	
	@Autowired
	CompanyRepository companyRepository;
	
	@Autowired 
    ApplicantRepository applicantRepository;


	@RequestMapping(path = "/getdistance", method = RequestMethod.GET, produces = "application/json")
	public void getResponse() {

		matchingService.calculateMatchingPercentage();

	}


	@RequestMapping(
			path = "/applicantSwipe/{authtoken}",
			method = RequestMethod.PUT,
			produces = "application/json",
			consumes = "application/json"
	)
	public ResponseCode applicantSwipe(
			@PathVariable(value = "authtoken") String authtoken,
			@Valid @RequestBody CTMatchingEntity request) {

//		System.out.println("inside applicantSwipe method");
		ResponseCode responseCode = new ResponseCode();
		CTUserEntity user = new CTUserEntity();

		try {
			user = userRepository.getByToken(authtoken);
			if (user != null) {
				Long companyId = request.getCompany_id();
				Long applicantId = request.getApplicant_id();
				CTMatchingEntity matchRow = matchingRepository.getMatchRow(companyId, applicantId);
				if (matchRow != null) {
					matchRow.setApplicant_swipe(request.getApplicant_swipe());
					matchingRepository.save(matchRow);

					responseCode.setStatus_code("Success");
					responseCode.setMethod("update_applicant_swipe");
					responseCode.setMessage("Applicant swipe data updated Successfully");
				} else {
					responseCode.setStatus_code("Failure");
					responseCode.setMethod("update_applicant_swipe");
					responseCode.setMessage("No record found in \"ctmatching\" table!");
				}
				return responseCode;
			}
			else {
			 	responseCode.setStatus_code("Failure");
			 	responseCode.setMethod("update_applicant_swipe");
			 	responseCode.setMessage("authToken did not match!");
			}
			return responseCode;
		} catch (Exception ex) {
			responseCode.setStatus_code("Failure");
			responseCode.setMethod("update_applicant_swipe");
			responseCode.setMessage("And exception occurred");
			return responseCode;
		}


	}



	@RequestMapping(path = "/companySwipe/{authtoken}",method = RequestMethod.PUT,produces = "application/json",consumes = "application/json")
	public ResponseCode companySwipe(@PathVariable(value = "authtoken") String authtoken, @Valid @RequestBody CTMatchingEntity request) 
	{
	ResponseCode responseCode = new ResponseCode();
	CTUserEntity user = new CTUserEntity();

	try {
		user = userRepository.getByToken(authtoken);
		if (user != null) {
			Long companyId = request.getCompany_id();
			Long applicantId = request.getApplicant_id();
			CTMatchingEntity matchRow = matchingRepository.getMatchRow(companyId, applicantId);
			if (matchRow != null) {
				matchRow.setCompany_swipe(request.getCompany_swipe());
				matchingRepository.save(matchRow);

				responseCode.setStatus_code("Success");
				responseCode.setMethod("update_company_swipe");
				responseCode.setMessage("Company swipe data updated Successfully");
			} 
			else 
			{
				responseCode.setStatus_code("Failure");
				responseCode.setMethod("update_company_swipe");
				responseCode.setMessage("No record found in \"ctmatching\" table!");
			}
			return responseCode;
		}
		else 
		{
		 	responseCode.setStatus_code("Failure");
		 	responseCode.setMethod("update_company_swipe");
		 	responseCode.setMessage("authToken did not match!");
		}
		return responseCode;
	} 
	catch (Exception ex) {
		responseCode.setStatus_code("Failure");
		responseCode.setMethod("update_company_swipe");
		responseCode.setMessage("And exception occurred");
		return responseCode;
	}
   }
	
	@GetMapping(path ="/applicant/listOfJobs/{authcode}", produces = "application/json")
	public ResponseCode applicantListOfJobs(@PathVariable(value = "authcode") String authcode) {
		
		ResponseCode response = new ResponseCode();
		CTApplicantEntity applicant = new CTApplicantEntity();
		List<CTMatchingEntity> matchingList = new ArrayList<CTMatchingEntity>();
		ArrayList<CTCompanyEntity> companyList = new ArrayList<CTCompanyEntity>();
		
		CTUserEntity user = new CTUserEntity();
		user = userRepository.getByToken(authcode);
		boolean noMatchProfile = true; 
		
		try {
			
		
		if(user != null)
		{
			applicant = applicantRepository.findByEmail(user.getEmailid());
			matchingList = matchingRepository.getRecordsForApplicant(applicant.getId());
			
			for(CTMatchingEntity matching : matchingList)
			{
				if(matching.getApplicant_swipe() == 0 && matching.getCompany_swipe() == 0 && matching.getPercentage() <= 90)
				{
					noMatchProfile = false;
					companyList.add(companyRepository.getCompanyById(matching.getCompany_id()));
					
				}
				else if(matching.getApplicant_swipe() == 0 && matching.getCompany_swipe() == 1 && matching.getPercentage() <= 90)
				{
					noMatchProfile = false;
					companyList.add(companyRepository.getCompanyById(matching.getCompany_id()));
				}
				
			}
			
			if(noMatchProfile)
			{
				response.setStatus_code("Success");
				response.setMethod("getPerspectiveJobMatches");
				response.setMessage("No Matching Profiles Found");
			}
			else
			{
				response.setStatus_code("Success");
				response.setJoblist(companyList);
				response.setApplicant(applicant);
				response.setMethod("getPerspectiveJobMatches");
				response.setMessage("List of company profiles");
			}
		}
		else
		{
			response.setStatus_code("Fail");
			response.setMethod("getPerspectiveMatches");
			response.setMessage("User Not Found");
		}
	}catch(Exception ex)
	{
		response.setMessage("Technical Error. If this continues please report this issue ");
	}
		return response;
	}
	
	
	@GetMapping(path ="/company/listOfApplicants/{jobid}", produces = "application/json")
	public ResponseCode companyListOfApplicants(@PathVariable(value = "jobid") String jobid) {
		
		ResponseCode response = new ResponseCode();
		CTCompanyEntity company = new CTCompanyEntity();
		List<CTMatchingEntity> matchingList = new ArrayList<CTMatchingEntity>();
		ArrayList<CTApplicantEntity> applicantlist = new ArrayList<CTApplicantEntity>();
		
		company = companyRepository.getJobById(Integer.parseInt(jobid));
		boolean noMatchProfile = true; 
		
		try
		{
		
		if(company != null)
		{
			matchingList = matchingRepository.getRecordsForCompany(company.getId());
			
			for(CTMatchingEntity matching : matchingList)
			{

				if(matching.getApplicant_swipe() == 0 && matching.getCompany_swipe() == 0 && matching.getPercentage() <= 90)
				{
					noMatchProfile = false;
					applicantlist.add(applicantRepository.getApplicantById(matching.getApplicant_id()));
					
				}
				else if(matching.getApplicant_swipe() == 0 && matching.getCompany_swipe() == 1 && matching.getPercentage() <= 90)
				{
					noMatchProfile = false;
					applicantlist.add(applicantRepository.getApplicantById(matching.getApplicant_id()));
				}
			}
			
			if(noMatchProfile)
			{
				response.setStatus_code("Success");
				response.setMethod("getPerspectiveMatches");
				response.setMessage("No Matching Profiles Found");
			}
			else
			{
				response.setStatus_code("Success");
				response.setApplicantProfiles(applicantlist);
				response.setMethod("getPerspectiveMatches");
				response.setMessage("List of company profiles");
			}
		}
		else
		{
		
			response.setStatus_code("Fail");
			response.setMethod("getPerspectiveMatches");
			response.setMessage("Job Not Found");
		}
	}catch(Exception ex)
	{
		response.setMessage("Technical Error. If this continues please report this issue "+ex.getStackTrace()[0].getLineNumber());
	}
		
		return response;
	}
 }