package com.springboot.app.hospitalapp.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table(name="patient_address")
public class Patientaddress {

	@Id
	@GeneratedValue (strategy = GenerationType.IDENTITY)
	@Column(name="address_id")
	private int aId;
	
//	@Column(name="patient_id")
//	private int pId;
//	
	public Integer getaId() {
		return aId;
	}

	public void setaId(int aId) {
		this.aId = aId;
	}
	@NotNull(message = "State can't be null !")
	@Column(name="state")
	private String state;
	@NotNull(message = "City can't be null !")
	@Column(name="city")
	private String city;
	@NotNull(message = "Street can't be null !")
	@Column(name="street")
	private String street;
	@NotNull(message = "Zipcode can't be null !")
	@Column(name="zipcode")
	private String zipCode;
	
	public Patientaddress() {}
	
	public Patientaddress(int pId, String state, String city, String street, String zipCode) {
		super();
	
		this.state = state;
		this.city = city;
		this.street = street;
		this.zipCode = zipCode;
	}

//	public int getpId() {
//		return pId;
//	}
//
//	public void setpId(int pId) {
//		this.pId = pId;
//	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getStreet() {
		return street;
	}

	public void setStreet(String street) {
		this.street = street;
	}

	public String getZipCode() {
		return zipCode;
	}

	public void setZipCode(String zipCode) {
		this.zipCode = zipCode;
	}
	
	
}
