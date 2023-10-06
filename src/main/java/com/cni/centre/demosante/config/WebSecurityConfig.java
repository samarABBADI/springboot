package com.cni.centre.demosante.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.cni.centre.demosante.services.CustomUserDetailsService;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
	
	// service that retrieve user details from database
	     @Autowired
	    CustomUserDetailsService userDetailsService;

	     // bean for encoding password using Bcrypt
	    @Bean
	    public PasswordEncoder passwordEncoder() {
	        return new BCryptPasswordEncoder();
	    }

	    @Override
	    public void configure(AuthenticationManagerBuilder auth) throws Exception
	    {
	        auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
	    }
	
	@Override
	protected void configure(HttpSecurity http) throws Exception
	{
		 
		http.csrf().disable();      // disable csrf 
		http.authorizeRequests()     
		.antMatchers("/role")
        .hasRole("admin")       // route with "/role"/ only how has role admin can access them
		.anyRequest().authenticated()    // any other request must be authenticated
		.and()
		.httpBasic();
		
	}

}
