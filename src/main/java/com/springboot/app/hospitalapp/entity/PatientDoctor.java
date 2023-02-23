package com.springboot.app.hospitalapp.entity;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;

import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.Future;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name="patient_doctor")
public class PatientDoctor {

	
	
	@Id
	@GeneratedValue (strategy = GenerationType.IDENTITY)
	@Column(name="appointment_no")
    private int appointmentNo;

	 


	public String getSymptoms() {
		return symptoms;
	}

	public void setSymptoms(String symptoms) {
		this.symptoms = symptoms;
	}

	@Column(name="symptoms")
    private String symptoms;
	
	@OneToOne(cascade=CascadeType.ALL)
	@JoinColumn(name="doctor_id")
	private Doctor doctor;

	@OneToOne(cascade=CascadeType.ALL)
	@JoinColumn(name="file_id")
	private FileDB fileDB;
	
	public FileDB getFileDB() {
		return fileDB;
	}

	public void setFileDB(FileDB fileDB) {
		this.fileDB = fileDB;
	}

	@OneToOne(cascade=CascadeType.ALL)
	@JoinColumn(name="patient_id")
	private Patient patient;
	
	public Patient getPatient() {
		return patient;
	}

	public void setPatient(Patient patient) {
		this.patient = patient;
	}

	@Column(name="appointment_time")
	@NotEmpty(message = "Please select date time")
   // @Future(message = "Appointment date should be less than current date!!")
   // @DateTimeFormat(iso = DateTimeFormat.ISO.DATE, pattern = "dd-yyyy-MM hh:mm")
    String appointmentTime;
 	
	@Column(name="status")
    int status;

	public int getAppointmentNo() {
		return appointmentNo;
	}

	public void setAppointmentNo(int appointmentNo) {
		this.appointmentNo = appointmentNo;
	}

	public Doctor getDoctor() {
		return doctor;
	}

	public void setDoctor(Doctor doctor) {
		this.doctor = doctor;
	}



	public String getAppointmentTime() {
		return appointmentTime;
	}

	public void setAppointmentTime(String appointmentTime) {
		this.appointmentTime = appointmentTime;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}
    
    
    
}
