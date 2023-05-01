package com.incture.loneworker.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.incture.loneworker.model.EmployeeAlert;

public interface AlertRepository extends JpaRepository<EmployeeAlert, Long> {

	List<EmployeeAlert> findByOrderById();

}
