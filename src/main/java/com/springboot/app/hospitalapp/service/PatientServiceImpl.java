package com.springboot.app.hospitalapp.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springboot.app.hospitalapp.entity.Patient;
import com.springboot.app.hospitalapp.repository.PatientRepository;

@Service
public class PatientServiceImpl implements PatientService {
	@Autowired
	PatientRepository patientRepository;
	@Override
	public List<Patient> findAll() {
	
		return patientRepository.findAll();
	}

	@Override
	public Patient findByMobNo(String mobNo) {
		// TODO Auto-generated method stub
		
		return patientRepository.findByMobNo(mobNo);
	}

	@Override
	public int save(Patient patient) {
		patientRepository.save(patient);
		
		return patient.getPId();

	}

	@Override
	public void deleteById(int theId) {
		// TODO Auto-generated method stub
		
		patientRepository.deleteById(theId);
	}

	@Override
	public Patient findByPId(int pId) {
		// TODO Auto-generated method stub
		return patientRepository.findBypId(pId);
	}

}
