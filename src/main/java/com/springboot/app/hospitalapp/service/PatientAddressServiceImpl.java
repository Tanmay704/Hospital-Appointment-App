package com.springboot.app.hospitalapp.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springboot.app.hospitalapp.entity.Patient;

import com.springboot.app.hospitalapp.entity.Patientaddress;
import com.springboot.app.hospitalapp.repository.PatientAddressRepository;

@Service
public class PatientAddressServiceImpl implements PatientAddressService {
	@Autowired
	PatientAddressRepository patientAddressRepository;
	@Override
	public List<Patient> findAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Patient findById(int theId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void save(Patientaddress patientAddress) {
		// TODO Auto-generated method stub
		patientAddressRepository.save(patientAddress);
	}

	@Override
	public void deleteById(int theId) {
		// TODO Auto-generated method stub
		patientAddressRepository.deleteById(theId);

	}


}
