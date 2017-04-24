package com.tragent.pressing.auth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;

import com.tragent.pressing.repository.UserRepository;
import com.tragent.pressing.service.CustomUserDetailService;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(securedEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	private UserRepository userRepository;
		
	@Override
    protected void configure(HttpSecurity http) throws Exception {
		
		http.csrf().disable()
        	.authorizeRequests()
        	.antMatchers(HttpMethod.POST, "/api/v1/authenticate").permitAll()
            .anyRequest().authenticated()
            .and()
            .httpBasic()
            .authenticationEntryPoint(getBasicAuthEntryPoint())
            .and()
            .sessionManagement()
            .sessionCreationPolicy(org.springframework.security.config.http.SessionCreationPolicy.STATELESS);
        	
	}
	
	@Bean
	public CustomBasicAuthenticationEntryPoint getBasicAuthEntryPoint() {
		return new CustomBasicAuthenticationEntryPoint();
	}
	
	/**
	 *  To allow Pre-flight [OPTIONS] request from browser
	 **/
	@Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers(HttpMethod.OPTIONS, "/**");
    }

	/**
	 * configure spring security to use our user detail service
	 **/
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsServiceBean());
	}
	
	/**
	 * Register custom UserDetailService to Spring Security and tell 
	 * spring security to use this class for loading user form database.
	 **/
	@Override
	public UserDetailsService userDetailsServiceBean() throws Exception {
		return new CustomUserDetailService(userRepository);
	}
	
}
