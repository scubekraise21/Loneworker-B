package com.incture.loneworker.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.incture.loneworker.model.EmployeeActivity;

public interface ActivityRepository extends JpaRepository<EmployeeActivity, Long> {

	List<EmployeeActivity> findByOrderById();

}
