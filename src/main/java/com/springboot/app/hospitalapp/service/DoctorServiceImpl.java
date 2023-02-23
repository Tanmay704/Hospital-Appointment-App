package com.springboot.app.hospitalapp.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springboot.app.hospitalapp.entity.Doctor;
import com.springboot.app.hospitalapp.repository.DoctorRepository;

@Service
public class DoctorServiceImpl implements DoctorService {

	@Autowired
	DoctorRepository doctorRepository;
	@Override
	public List<Doctor> findAll() {
		
		return (List<Doctor>) doctorRepository.findAll();
	}

	@Override
	public Doctor findById(int theId) {
		// TODO Auto-generated method stub
		return doctorRepository.findBydId(theId);
	}

	@Override
	public void save(Doctor doctor) {
		// TODO Auto-generated method stub
		doctorRepository.save(doctor);
	}

	@Override
	public void deleteById(int theId) {
		// TODO Auto-generated method stub

	}

	@Override
	public Doctor findByUserName(String username) {
		// TODO Auto-generated method stub
		return doctorRepository.findByUsername(username);
	}

}
