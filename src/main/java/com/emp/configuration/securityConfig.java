package com.emp.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class securityConfig  {
	
	@Bean
	public BCryptPasswordEncoder BCryptPasswordEncoder()
	{
		return new BCryptPasswordEncoder();
	}
	
	@Bean
	public customUserDetailService customUserDetailService()
	{
		return new customUserDetailService();
	}
	
	@Bean
	public DaoAuthenticationProvider daoAuthenticationProvider()
	{
		DaoAuthenticationProvider authenticationProvider=new DaoAuthenticationProvider();
		
		authenticationProvider.setUserDetailsService(customUserDetailService());
		authenticationProvider.setPasswordEncoder(BCryptPasswordEncoder());
		return authenticationProvider;
	}
	@Bean
	public SecurityFilterChain SecurityFilterChain(HttpSecurity https) throws Exception
	{
		https.csrf().disable().authorizeHttpRequests().requestMatchers("/user").permitAll().anyRequest().authenticated().and().formLogin();
		return https.build();
	}
	
	

}
