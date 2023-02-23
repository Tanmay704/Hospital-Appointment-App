package com.springboot.app.hospitalapp.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.springboot.app.hospitalapp.entity.PatientDoctor;
import com.springboot.app.hospitalapp.repository.PatientDoctorRepository;

@Service
public class PatientDoctorServiceImpl implements PatientDoctorService {
	@Autowired
	PatientDoctorRepository patientDoctorRepository;
	
	@Override
	public List<PatientDoctor> findAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public PatientDoctor findByAppointmentNo(int appoNo) {
		// TODO Auto-generated method stub
		return patientDoctorRepository.findById(appoNo);
	}

	@Override
	public void save(PatientDoctor patientDoctor) {
			patientDoctorRepository.save(patientDoctor);
	}

	@Override
	public void deleteById(int pId) {
			patientDoctorRepository.deleteByPId(pId);

	}

	@Override
	public List<String> findbyDid(int dId) {
		
		return patientDoctorRepository.findbyDid(dId);
	}

	@Override
	public List<PatientDoctor> findByPId(Integer pId) {
		// TODO Auto-generated method stub
		return patientDoctorRepository.findbyPid(pId);
	}

	@Override
	public List<PatientDoctor> findAllByDId(Integer dId) {
		// TODO Auto-generated method stub
		return patientDoctorRepository.findAllByDId(dId);
	}

	@Override
	public void updateStatus(int appointmentNo, int status) {
		// TODO Auto-generated method stub
		 patientDoctorRepository.updateStatus(appointmentNo, status);
	}

	@Override
	public List<PatientDoctor> findAllBydId(int dId) {
	
		return patientDoctorRepository.findAllBydId(dId);
	}
	
	@Override
	public Page<PatientDoctor> findAllByStatus(int dId, int status, String today, Pageable pageable)
	{
		return patientDoctorRepository.findAllByStatus(dId, status, today, pageable);
	}

	@Override
	public Page<PatientDoctor> findAllBydIdHistory(int dId, String today, Pageable pageable) {
		// TODO Auto-generated method stub
		return patientDoctorRepository.findAllBydIdHistory(dId, today, pageable);
	}

	@Override
	public Page<PatientDoctor> findByKeyword(int dId, String keyword, String today, Pageable pageable) {
		// TODO Auto-generated method stub
		return patientDoctorRepository.findByKeyword(dId, keyword, today, pageable);
	}
	
	
	
	@Override
	public Page<PatientDoctor> findAllUpcomingByStatus(int dId, int status, String today, Pageable pageable)
	{
		return patientDoctorRepository.findAllUpcomingByStatus(dId, status, today, pageable);
	}

	@Override
	public Page<PatientDoctor> findAllUpcomingBydIdHistory(int dId, String today, Pageable pageable) {
		// TODO Auto-generated method stub
		return patientDoctorRepository.findAllUpcomingBydIdHistory(dId, today, pageable);
	}

	@Override
	public Page<PatientDoctor> findByUpcomingKeyword(int dId, String keyword, String today, Pageable pageable) {
		// TODO Auto-generated method stub
		return patientDoctorRepository.findByUpcomingKeyword(dId, keyword, today, pageable);
	}

}
