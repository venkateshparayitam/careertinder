package core.controllers;


import java.util.ArrayList;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import core.entities.CTCompanyEntity;
import core.entities.CTUserEntity;
import core.repositories.*;
import core.supplementary.ResponseCode;
import core.supplementary.RandomNumber;

/**
 * Creation of Company Controller to API calls.
 * @author: Pravin Garad.
 */


@RestController
@RequestMapping("/api")
public class CompanyController {
	
	@Autowired
	UserRepository user_repository;
	
	@Autowired
	CompanyRepository company_repository;
	
	@PostMapping(path ="/createProfileCompany/{authcode}", produces = "application/json")
	public ResponseCode createProfileCompany(@PathVariable(value = "authcode") String authcode, @Valid @RequestBody CTCompanyEntity company) {
		
		ResponseCode response_fail = new ResponseCode();
		ResponseCode response_success = new ResponseCode();
		CTUserEntity user = new CTUserEntity();
		
		try
		{
			user = user_repository.getByToken(authcode);
			
			if (user != null)
				
		{
				company.setJobid(new RandomNumber().generateJobId());
				company.setUser_company(user);
				company_repository.save(company);
				
			}
			else
			{
				response_fail.setStatus_code("Job not found");
				return response_fail;
			}
		}
		catch(Exception ex)
		{
			response_fail.setStatus_code("job not found");
			response_fail.setMethod("add_job_opening");
			return response_fail;
		}
		
		response_success.setStatus_code("job_opening_created");
		response_success.setMethod("add_job_opening");
		return response_success;
	}

	
	@GetMapping(path ="/listJobsCompany/{authcode}", produces = "application/json")
	public ResponseCode listJobsCompany(@PathVariable(value = "authcode") String authcode) {
		
		ResponseCode response_joblist = new ResponseCode();
		
		CTUserEntity user = new CTUserEntity();
		
		try
		{
			user = user_repository.getByToken(authcode);
			
			if (user != null)
				
			{
				response_joblist.setStatus_code("Success");
				response_joblist.setMethod("get_job_list");
				response_joblist.setJoblist(company_repository.getListOfJobs(user.getId()));
				
				return response_joblist; 
			}
			else
			{
				response_joblist.setStatus_code("Fail");
				response_joblist.setMethod("get_job_list");
				
				return response_joblist; 
			}
		}
		catch(Exception ex)
		{
			response_joblist.setStatus_code("job not found");
			response_joblist.setMethod("get_job_list");
			return response_joblist;
		}
		
	}
	
	@GetMapping(path ="/allJobsCompany", produces = "application/json")
	public ResponseCode allJobsCompany() {

			ResponseCode response_get_all_jobs = new ResponseCode();
			
			try
			{
			response_get_all_jobs.setStatus_code("Success");
			response_get_all_jobs.setMethod("get_job_list");
			response_get_all_jobs.setJoblist(new ArrayList<CTCompanyEntity>(company_repository.findAll()));
			}
			catch (Exception ex)
			{
				response_get_all_jobs.setMessage("Please try again later");
			}
			return response_get_all_jobs;
	}
}

