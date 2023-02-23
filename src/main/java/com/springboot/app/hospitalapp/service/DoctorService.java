package com.springboot.app.hospitalapp.service;

import java.util.List;

import com.springboot.app.hospitalapp.entity.Doctor;
import com.springboot.app.hospitalapp.entity.Patient;




public interface DoctorService {


	public List<Doctor> findAll();
	
	public Doctor findById(int theId);
	public Doctor findByUserName(String username);
	public void save(Doctor doctor);
	
	public void deleteById(int theId);

}
