package com.incture.loneworker.model;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
@Entity
@Table(name="EMPLOYEE_DETAILS")
@SequenceGenerator(name="seq", initialValue=10000)

public class Employee {
	
	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="seq")
	private long id;
	
	@Column(name="first_name" ,  nullable = false)
	@JsonProperty(value = "firstname")
	private String firstname;
	
	@Column(name="last_name")
	@JsonProperty(value = "lastname")
	private String lastname;
	
	@Column(name="username" ,  nullable = false)
	@JsonProperty(value = "username")
	private String username;

	@Column(name="password" ,  nullable = false)
	@JsonProperty(value = "password")
	private String password;
	
	@Column(name="dept" ,  nullable = false)
	@JsonProperty(value = "dept")
	private String dept;

	@Column(name="admin" ,  nullable = false)
	@JsonProperty(value = "admin")
	private String admin;
	
	@Column(name="logged_in")
	@JsonProperty(value = "loggedin")
	private boolean loggedin;
	
	@Column(name="content")
    @Lob
    byte[] content;

    @Column(name="name" )
    String name;
	
	public Employee() {
		
	}
	
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getFirstname() {
		return firstname;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	public String getLastname() {
		return lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getDept() {
		return dept;
	}

	public void setDept(String dept) {
		this.dept = dept;
	}


	public String getAdmin() {
		return admin;
	}

	public void setAdmin(String admin) {
		this.admin = admin;
	}

	public boolean isLoggedin() {
		return loggedin;
	}

	public void setLoggedin(boolean loggedin) {
		this.loggedin = loggedin;
	}

	public byte[] getContent() {
		return content;
	}

	public void setContent(byte[] content) {
		this.content = content;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Employee(long id, String firstname, String lastname, String username, String password, String dept,
			String admin, boolean loggedin, byte[] content, String name) {
		super();
		this.id = id;
		this.firstname = firstname;
		this.lastname = lastname;
		this.username = username;
		this.password = password;
		this.dept = dept;
		this.admin = admin;
		this.loggedin = loggedin;
		this.content = content;
		this.name = name;
	}

	

}
