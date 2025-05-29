
package com.mantprev;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;


@Configuration
public class UploadsConfig implements WebMvcConfigurer {
/*********************************************************/

	
	@Value("${rutaUploads}")     //-->Ruta definida en applitation.properties
	private String rutaUploads;  //C:/Users/Admin/Desktop/Tmp2/rutaUploadsOTs/ (Windows) --> /rutaUploadsOTs (Linux)
	
	
	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
	/******************************************************************/		
		
		WebMvcConfigurer.super.addResourceHandlers(registry);
		
		//Ruta definida en applitation.properties --> Windows y Linux 
		registry.addResourceHandler("/rutaUploads/**").addResourceLocations("file:/" + rutaUploads);  
		
	}
	
	
	
	
}
