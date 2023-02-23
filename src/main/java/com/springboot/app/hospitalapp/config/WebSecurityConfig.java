package com.springboot.app.hospitalapp.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;



@Configuration
@Order(2)
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	@Qualifier("patientDetailsService")
	private UserDetailsService userDetailsService;
	


	@Bean
	public DaoAuthenticationProvider authProvider() {
		DaoAuthenticationProvider daoAuthProvider = new DaoAuthenticationProvider();
	//	daoAuthProvider.setPasswordEncoder(passwordEncoder);
		daoAuthProvider.setUserDetailsService(userDetailsService);
	
		return daoAuthProvider;
	}
	@Override
	public void configure(WebSecurity web) throws Exception {
	    web.ignoring().antMatchers("/patient/register");
	    web.ignoring().antMatchers("/patient/add");
	    web.ignoring().antMatchers("/patient/submit");
	}
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		// TODO Auto-generated method stub
		http.antMatcher("/patient/**")
		.authorizeRequests()
		.anyRequest()
		.hasAuthority("PATIENT")
		.and()
		.formLogin()
				.loginPage("/patient/login")
				.loginProcessingUrl("/patient/process")
				.failureUrl("/patient/login?error=loginError")
				.defaultSuccessUrl("/patient/dashboard")
				 .permitAll()
		   		 .and()
		          .logout()
		          .logoutRequestMatcher(new AntPathRequestMatcher("/patient/logout"))
		          .logoutSuccessUrl("/patient/login")
	          .deleteCookies("JSESSIONID")
		          
	          .and()
	          .exceptionHandling()
	          .accessDeniedPage("/403");
	}


	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		// TODO Auto-generated method stub
		auth.authenticationProvider(authProvider());
	}
	
	
		
}
