package com.springboot.app.hospitalapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;


import com.springboot.app.hospitalapp.entity.Patientaddress;

public interface PatientAddressRepository extends JpaRepository<Patientaddress, Integer> {

}
