package com.springboot.app.hospitalapp.service;

import java.util.List;

import com.springboot.app.hospitalapp.entity.Patient;




public interface PatientService {


	public List<Patient> findAll();
	
	public Patient findByMobNo(String string);
	
	public Patient findByPId(int pId);
	
	public int save(Patient patient);
	
	public void deleteById(int theId);

}
