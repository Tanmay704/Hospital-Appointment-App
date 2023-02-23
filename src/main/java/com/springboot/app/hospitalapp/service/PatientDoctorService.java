package com.springboot.app.hospitalapp.service;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.springboot.app.hospitalapp.entity.PatientDoctor;




public interface PatientDoctorService {


	public List<PatientDoctor> findAll();
	
	public PatientDoctor findByAppointmentNo(int appoNo);
	
	public void save(PatientDoctor patientDoctor);
	public void updateStatus(int appointmentNo, int status);
	public void deleteById(int pId);
	public List<String> findbyDid(int dId);
	public List<PatientDoctor> findAllBydId(int dId);
	public List<PatientDoctor> findByPId(Integer pId);
	public List<PatientDoctor> findAllByDId(Integer dId);
	public Page<PatientDoctor> findAllByStatus(int dId,int status, String today, Pageable pageable);
	public Page<PatientDoctor> findAllBydIdHistory(int dId, String today, Pageable pageable);
	public Page<PatientDoctor> findByKeyword(int dId, String keyword,String today, Pageable pageable);
	
	public Page<PatientDoctor> findAllUpcomingByStatus(int dId,int status, String today, Pageable pageable);
	public Page<PatientDoctor> findAllUpcomingBydIdHistory(int dId, String today, Pageable pageable);
	public Page<PatientDoctor> findByUpcomingKeyword(int dId, String keyword,String today, Pageable pageable);
}
