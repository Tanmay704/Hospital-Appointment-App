package com.springboot.app.hospitalapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.springboot.app.hospitalapp.entity.Doctor;
import com.springboot.app.hospitalapp.entity.FileDB;

public interface FilesRepository extends JpaRepository<FileDB, Integer> {

	FileDB findById(int id);
	
}
