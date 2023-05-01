package com.incture.loneworker.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.incture.loneworker.model.EmployeeImage;

public interface ImageRepository extends JpaRepository<EmployeeImage, Long>{

}
