package com.mantprev;

import java.text.SimpleDateFormat;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.mantprev.repositorios.Usuarios_Repository;

import lombok.RequiredArgsConstructor;


@Configuration
@RequiredArgsConstructor
public class ApplicationConfigs  implements WebMvcConfigurer{

	
	private final Usuarios_Repository usuariosRepository;


    @Bean
    ModelMapper modelMapper() {
	/********************************/	
		return new ModelMapper();
	}


    @Bean
    SimpleDateFormat dateFormat() {
	/*************************************/	
		return new SimpleDateFormat("yyyy-MM-dd");
	}


    /****  CONFIGURACIONES DE SPRING SECURITY Y JWT  ****/
    /****************************************************/
    
    @Bean
    AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
	//******************************************************************************************************
        return config.getAuthenticationManager();
    }


    @Bean
    AuthenticationProvider authenticationProvider(){
	//*****************************************************	 
        DaoAuthenticationProvider authenticationProvider= new DaoAuthenticationProvider();
        	authenticationProvider.setUserDetailsService(userDatailsService()); 
        	authenticationProvider.setPasswordEncoder(passwordEncoder());
        	
        return authenticationProvider;
    }


    @Bean
    UserDetailsService userDatailsService() {
	//*********************************************	
        return username -> usuariosRepository.findOneByEmailUsuario(username)
        .orElseThrow(()-> new UsernameNotFoundException("User not fournd"));
    }


    @Bean
    PasswordEncoder passwordEncoder() {
	//**************************************	
		return new BCryptPasswordEncoder();
	}
	
	
	
	
	
	
	
}
