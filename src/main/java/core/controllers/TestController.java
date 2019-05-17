package core.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {
	
	 
	    @GetMapping("/testing")
	    public String sayHello() {
	        return "This is a test method";
	    }
	}
