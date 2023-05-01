package com.incture.loneworker.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.incture.loneworker.model.EmployeeStatus;

public interface StatusRepository extends JpaRepository<EmployeeStatus, Long>{

	
}
