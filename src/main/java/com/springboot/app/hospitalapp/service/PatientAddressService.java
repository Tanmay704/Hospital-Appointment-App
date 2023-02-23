package com.springboot.app.hospitalapp.service;

import java.util.List;

import com.springboot.app.hospitalapp.entity.Patient;
import com.springboot.app.hospitalapp.entity.PatientDoctor;
import com.springboot.app.hospitalapp.entity.Patientaddress;




public interface PatientAddressService {


	public List<Patient> findAll();
	
	public Patient findById(int theId);
	
	public void save(Patientaddress patientAddress);
	
	public void deleteById(int theId);

	

}
