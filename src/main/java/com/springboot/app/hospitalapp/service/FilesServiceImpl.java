package com.springboot.app.hospitalapp.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springboot.app.hospitalapp.entity.FileDB;
import com.springboot.app.hospitalapp.repository.FilesRepository;

@Service
public class FilesServiceImpl implements FilesService {
	
	@Autowired
	FilesRepository filesRepository; 
	@Override
	public List<FileDB> findAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public FileDB findById(int theId) {
		// TODO Auto-generated method stub
		return filesRepository.findById(theId);
	}

	@Override
	public FileDB findByUserName(String username) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void save(FileDB fileDB) {
		
       filesRepository.save(fileDB);
	}

	@Override
	public void deleteById(int theId) {
		// TODO Auto-generated method stub

	}

}
