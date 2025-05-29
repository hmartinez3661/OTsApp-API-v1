package com.mantprev;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;



@SpringBootApplication
public class OTsAppApiV1Application extends SpringBootServletInitializer {
	

	public static void main(String[] args) {
		SpringApplication.run(OTsAppApiV1Application.class, args);
	}
	
	
	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		return application.sources(OTsAppApiV1Application.class);
	}
	
	
	
	
	

}
