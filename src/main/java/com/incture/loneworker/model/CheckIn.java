package com.incture.loneworker.model;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Embeddable;

import com.fasterxml.jackson.annotation.JsonProperty;

@Embeddable
public class CheckIn implements Serializable{
	

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Column(name = "emp_id")
	@JsonProperty(value = "emp_id")
	private long emp_id;
	
	@Column(name = "check_in")
	@JsonProperty(value = "check_in")
	private String checkin;
	
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CheckIn that = (CheckIn) o;
        return (emp_id == that.emp_id) &&
        		checkin.equals(that.checkin);
    }

    @Override
    public int hashCode() {
        return Objects.hash(emp_id, checkin);
    }

	public CheckIn(long emp_id, String check_in) {
		super();
		this.emp_id = emp_id;
		this.checkin = check_in;
	}

	public CheckIn() {
		
	}

	public long getEmp_id() {
		return emp_id;
	}

	public void setEmp_id(long emp_id) {
		this.emp_id = emp_id;
	}

	public String getCheck_in() {
		return checkin;
	}

	public void setCheck_in(String check_in) {
		this.checkin = check_in;
	}
    
    

}
