package com.springboot.app.hospitalapp.service;

import java.util.List;


import com.springboot.app.hospitalapp.entity.FileDB;




public interface FilesService {


	public List<FileDB> findAll();
	
	public FileDB findById(int theId);
	public FileDB findByUserName(String username);
	public void save(FileDB fileDB);
	
	public void deleteById(int theId);

}
