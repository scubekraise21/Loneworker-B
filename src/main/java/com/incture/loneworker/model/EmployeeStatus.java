package com.incture.loneworker.model;


import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
@Entity
@Table(name="EMPLOYEE_STATUS")

public class EmployeeStatus{

	@EmbeddedId
	private CheckIn emp_id;
	
	@Column(name="location" )
	@JsonProperty(value = "location")
	private String location;
	

	@Column(name="check_out" )
	@JsonProperty(value = "check_out")
	private String checkout;


	public EmployeeStatus() {
		
	}


	public CheckIn getEmp_id() {
		return emp_id;
	}


	public void setEmp_id(CheckIn emp_id) {
		this.emp_id = emp_id;
	}


	public String getLocation() {
		return location;
	}


	public void setLocation(String location) {
		this.location = location;
	}


	public String getCheckout() {
		return checkout;
	}


	public void setCheckout(String checkout) {
		this.checkout = checkout;
	}


	@Override
	public String toString() {
		return "EmployeeStatus [emp_id=" + emp_id + ", location=" + location + ", checkout=" + checkout + "]";
	}


	public EmployeeStatus(CheckIn emp_id, String location, String checkout) {
		super();
		this.emp_id = emp_id;
		this.location = location;
		this.checkout = checkout;
	}
	
	
	
	
	
	
	
	

}