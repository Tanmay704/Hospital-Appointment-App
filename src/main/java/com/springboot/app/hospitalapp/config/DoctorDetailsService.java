package com.springboot.app.hospitalapp.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.springboot.app.hospitalapp.entity.Doctor;
import com.springboot.app.hospitalapp.entity.Patient;
import com.springboot.app.hospitalapp.repository.DoctorRepository;

@Service
public class DoctorDetailsService implements UserDetailsService{

	@Autowired
	DoctorRepository doctorRepository;
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		// TODO Auto-generated method stub
	
		Doctor doctor = doctorRepository.findByUsername(username);

		if(doctor == null) {
			throw new UsernameNotFoundException(username);
		}	
		PasswordEncoder encoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();
//		System.out.println("___________________________________________" + username + "__"+encoder.encode(doctor.getPassword()));
		UserDetails user = User.withUsername(doctor.getUsername())
				.password(encoder.encode(doctor.getPassword())).authorities("DOCTOR").build();
		return user;
	}
	

}