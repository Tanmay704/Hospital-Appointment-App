package com.springboot.app.hospitalapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.springboot.app.hospitalapp.entity.Patient;

public interface PatientRepository extends JpaRepository<Patient, Integer> {

		Patient findByMobNo(String mobNo);
		Patient findBypId(int pId);
}
