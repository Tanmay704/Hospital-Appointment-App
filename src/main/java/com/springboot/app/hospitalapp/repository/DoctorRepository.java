package com.springboot.app.hospitalapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.springboot.app.hospitalapp.entity.Doctor;

public interface DoctorRepository extends JpaRepository<Doctor, Integer> {
		Doctor findBydId(int id);
		Doctor findByUsername(String username);
}
