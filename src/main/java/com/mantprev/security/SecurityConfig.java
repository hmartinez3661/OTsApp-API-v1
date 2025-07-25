package com.mantprev.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
//import static org.springframework.security.config.Customizer.withDefaults;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

//import com.mantprev.security.JWTAuthenticationFilter; 

import lombok.RequiredArgsConstructor;


@Configuration
@EnableWebSecurity   //(debug = true)
@RequiredArgsConstructor
public class SecurityConfig {
	
	
	private final AuthenticationProvider authProvider;
	private final JWTAuthenticationFilter jwtAuthenticationFilter;
	
	
	@Bean
	public SecurityFilterChain securityFilterChain (HttpSecurity http) throws Exception {
	/************************************************************************************/	
		
		return http
				.csrf(csrf -> csrf.disable())
				.authorizeHttpRequests(authRequest -> authRequest
	                .requestMatchers("/auth/**").permitAll()
	                .anyRequest().authenticated())
				.sessionManagement(sessionManager -> sessionManager 
					.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
				.authenticationProvider(authProvider)
				.addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class)
				.build(); 
		
	}
	

    


}
