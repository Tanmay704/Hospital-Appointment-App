package com.springboot.app.hospitalapp.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.springboot.app.hospitalapp.entity.Patient;
import com.springboot.app.hospitalapp.repository.PatientRepository;

@Service
public class PatientDetailsService implements UserDetailsService{

	@Autowired
	PatientRepository patientRepository;
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		// TODO Auto-generated method stub
	
		Patient patient = patientRepository.findByMobNo(username);

		if(patient == null) {
			
			throw new UsernameNotFoundException(username);
		}	
		PasswordEncoder encoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();

		UserDetails user = User.withUsername(patient.getMobNo())
				.password(encoder.encode(patient.getPassword())).authorities("PATIENT").build();
		return user;
	}
	

}
