package core;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;


/**
 * Main method for testing
 * @author: Pravin Garad
 */

@SpringBootApplication
@EnableAutoConfiguration
public class Application  {
	
	 
	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

}
