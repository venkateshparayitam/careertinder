package core.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import core.services.MatchingService;

/**
 * @author: Bora Bejleri
 */

@RestController
public class MatchingController {
	
	@Autowired 
	private MatchingService matchingService;
	
	
	@RequestMapping(path="/getdistance", method = RequestMethod.GET, produces="application/json")
	public void getResponse() {
		
    matchingService.calculateMatchingPercentage();	
	
	}

}