package core.controllers;

import core.entities.CTMatchingEntity;
import core.entities.CTUserEntity;
import core.repositories.MatchingRepository;
import core.repositories.UserRepository;
import core.supplementary.ResponseCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import core.services.MatchingService;

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


	@RequestMapping(path = "/getdistance", method = RequestMethod.GET, produces = "application/json")
	public void getResponse() {

		matchingService.calculateMatchingPercentage();

	}


	@RequestMapping(
			path = "/applicantSwipe/{authToken}",
			method = RequestMethod.PUT,
			produces = "application/json",
			consumes = "application/json"
	)
	public ResponseCode applicantSwipe(
			@PathVariable(value = "authcode") String authtoken,
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
}