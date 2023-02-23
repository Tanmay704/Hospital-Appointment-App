package com.springboot.app.hospitalapp.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;



@Configuration
@Order(1)
@EnableWebSecurity
public class WebSecurityConfig2 extends WebSecurityConfigurerAdapter {

	@Autowired
	@Qualifier("doctorDetailsService")
	private UserDetailsService userDetailsService;
	
//	
//	@Autowired
//	PasswordEncoder passwordEncoder;

	@Bean
	public DaoAuthenticationProvider authProviderForDoctor() {
		DaoAuthenticationProvider daoAuthProvider = new DaoAuthenticationProvider();
	//	daoAuthProvider.setPasswordEncoder(passwordEncoder);
		daoAuthProvider.setUserDetailsService(userDetailsService);
		return daoAuthProvider;
	}
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		// TODO Auto-generated method stub
		http.antMatcher("/doctor/**")
		.authorizeRequests()
		.anyRequest()
		.hasAuthority("DOCTOR")
		.and()
		.formLogin()
				.loginPage("/doctor/login")
				.loginProcessingUrl("/doctor/process")
				.failureUrl("/doctor/login?error=loginError")
				.defaultSuccessUrl("/doctor/dashboard")
				  .permitAll()
			  .and()
	          .logout()
	          .logoutRequestMatcher(new AntPathRequestMatcher("/doctor/logout"))
	          .logoutSuccessUrl("/doctor/login")
	          .deleteCookies("JSESSIONID")
	          .and()
	          .exceptionHandling()
	          .accessDeniedPage("/403");
	}


	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		// TODO Auto-generated method stub
		auth.authenticationProvider(authProviderForDoctor());
	}
	
	
		
}
