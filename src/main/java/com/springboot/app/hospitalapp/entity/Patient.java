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
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;


@Entity
@Table(name="patient")

public class Patient {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="patient_id")
	private Integer pId;
	
	@Column(name="full_name")
	@NotNull(message = "LastName can not be null!!")
    @NotEmpty(message = "LastName can not be empty!!")
	private String fullName;
	
	@NotNull(message = "Email can't be null !")
	@Column(name="email")
	private String email;
	

	@OneToOne(cascade=CascadeType.ALL)
	@JoinColumn(name="a_id")
	private Patientaddress patientaddress;
	
	  public Patientaddress getPatientAddress() {
		return patientaddress;
	}
	public void setPatientAddress(Patientaddress patientaddress) {
		this.patientaddress = patientaddress;
	}
	public Patient() {}
	public Patient(String fullName, String email, Integer age, Float height, Float weight, String occupation, String mobNo,
			String password) {
		super();
		this.fullName = fullName;
		this.email = email;
		this.age = age;
		this.height = height;
		this.weight = weight;
		this.occupation = occupation;
		this.mobNo = mobNo;
		this.password = password;
	}

	@NotNull(message = "Age can't be null ! ")
	@Column(name="age")
	private Integer age;
	
	@NotNull(message = "Height can't be null !")
	@Column(name="height")
	private Float height;
	
	@NotNull(message = "Weight can't be null !")
	@Column(name="weight")
	private Float weight;
	
	@NotNull(message = "Occupation can't be null !")
	@Column(name="occupation")
	private String occupation;
	
    @NotNull
    @Size(max = 10, min = 10, message = "Mobile number should be of 10 digits")
    @Pattern(regexp = "[7-9][0-9]{9}", message = "Mobile number is invalid!!")
	@Column(name="mob_no")
	private String mobNo;
	
	
	public String getMobNo() {
		return mobNo;
	}

	public void setMobNo(String mobNo) {
		this.mobNo = mobNo;
	}

//	@NotNull(message = "Password can't be null !")
	@Column(name="password")
	private String password;
	
	
     
	 
	public Integer getPId() {
		return pId;
	}

	public void setPId(Integer pId) {
		this.pId = pId;
	}

	public String getFullName() {
	       
		return fullName;
	}

	public void setFullName(String fullName) {
		
		this.fullName = fullName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Integer getAge() {
		return age;
	}

	public void setAge(Integer age) {
		this.age = age;
	}

	public Float getHeight() {
		return height;
	}

	public void setHeight(Float height) {
		this.height = height;
	}

	public Float getWeight() {
		return weight;
	}

	public void setWeight(Float weight) {
		this.weight = weight;
	}

	public String getOccupation() {
		return occupation;
	}

	public void setOccupation(String occupation) {
		this.occupation = occupation;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

		
	
}
