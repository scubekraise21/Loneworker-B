package com.incture.loneworker.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name="EMPLOYEE_IMAGE")
public class EmployeeImage {
    @Id
    Long id;

    @Column(name="content")
    @Lob
    byte[] content;
    

    @Column(name="name" )
    String name;


	public Long getId() {
		return id;
	}


	public void setId(Long id) {
		this.id = id;
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


	public EmployeeImage() {
		
	}


	public EmployeeImage(Long id, byte[] content, String name) {
		super();
		this.id = id;
		this.content = content;
		this.name = name;
	}

    
    
}
