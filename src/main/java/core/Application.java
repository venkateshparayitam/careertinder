package core;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;


/**
 * Spring Boot Application execution start point
 * @author: Pravin Garad
 */

@SpringBootApplication
@EnableAutoConfiguration
@EnableScheduling
public class Application extends SpringBootServletInitializer  {
	
	 
	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

}
