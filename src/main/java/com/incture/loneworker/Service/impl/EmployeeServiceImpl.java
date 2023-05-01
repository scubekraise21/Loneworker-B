package com.incture.loneworker.Service.impl;
import javax.annotation.Resource;
import java.io.IOException;
import java.time.Instant;
import java.time.ZoneId;
import java.time.ZonedDateTime;

import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;

import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;

import com.incture.loneworker.Service.EmployeeService;
import com.incture.loneworker.exception.ExistingUsernameException;
import com.incture.loneworker.exception.IncorrectPasswordException;
import com.incture.loneworker.exception.ResourceNotFoundException;
import com.incture.loneworker.model.Employee;
import com.incture.loneworker.model.EmployeeActivity;
import com.incture.loneworker.model.EmployeeAlert;
import com.incture.loneworker.model.EmployeeImage;
import com.incture.loneworker.repository.ActivityRepository;
import com.incture.loneworker.repository.AlertRepository;
import com.incture.loneworker.repository.EmployeeRepository;
import com.incture.loneworker.repository.ImageRepository;

@Service
public class EmployeeServiceImpl implements EmployeeService{

	private EmployeeRepository employeeRepository;
	private ActivityRepository activityRepository;
	private AlertRepository alertRepository;
	private ImageRepository imageRepository;
	

	public EmployeeServiceImpl(EmployeeRepository employeeRepository, ActivityRepository activityRepository,
			AlertRepository alertRepository, ImageRepository imageRepository) {
		super();
		this.employeeRepository = employeeRepository;
		this.activityRepository = activityRepository;
		this.alertRepository = alertRepository;
		this.imageRepository = imageRepository;
	}



	@Override
	public Employee saveEmployee(Employee employee) {
		
		String email = employee.getUsername();
		Optional<Employee> emp = employeeRepository.findByUsername(email);
		if(emp.isPresent()){

			throw new ExistingUsernameException(email);
		}
		else {
			employeeRepository.save(employee);
			EmployeeActivity obj = new EmployeeActivity();
			obj.setId(employee.getId());
			activityRepository.save(obj);
			return employee;
			
		}
		
	}



	@Override
	public List<Employee> getAllEmployees() {
		return employeeRepository.findByOrderById();
	}

	@Override
	public Employee getEmployeeById(long id) {
		Optional<Employee> employee = employeeRepository.findById(id);
		if(employee.isPresent()){
			return employee.get();
		}else {
			throw new ResourceNotFoundException("Employee", "id", id);
		}
		
	}

	@Override
	public Employee updateEmployee(Employee employee, long id) {
		
		//check if employee exists
		Employee existingEmployee = employeeRepository.findById(id).orElseThrow( () -> new ResourceNotFoundException("Employee", "id", id));
		existingEmployee.setFirstname(employee.getFirstname());
		existingEmployee.setLastname(employee.getLastname());
		existingEmployee.setPassword(employee.getPassword());
		
		//save to db
		employeeRepository.save(existingEmployee);
		
		return existingEmployee;
	}

	@Override
	public void deleteEmployee(long id) {
		employeeRepository.findById(id).orElseThrow( () -> new ResourceNotFoundException("Employee", "id", id));
		employeeRepository.deleteById(id);
		activityRepository.deleteById(id);
		
	}


	@Override
	public Employee loginEmployee(Employee employee) {
		
		String username = employee.getUsername();
		String password = employee.getPassword();
		Optional<Employee> existingEmployee = employeeRepository.findByUsername(username);
		  if(existingEmployee.isPresent()){
			  Employee loggedIn = existingEmployee.get();
			  String pw = loggedIn.getPassword();
			  if(pw.equals(password)){
				  loggedIn.setLoggedin(true);
	              employeeRepository.save(loggedIn);
			      return loggedIn;
			  }
			   
			  else {
				  throw new IncorrectPasswordException(username);
				}
		  }else {
			  throw new ResourceNotFoundException("Employee", "username", username);
		  }

	}

	@Override
	public void logout(Employee employee) {
		String username = employee.getUsername();
		Optional<Employee> existingEmployee = employeeRepository.findByUsername(username);
		  if(existingEmployee.isPresent()){
			  Employee loggedOut = existingEmployee.get();
			  loggedOut.setLoggedin(false);
			  employeeRepository.save(loggedOut);			  			  
		  }

	}



	@Override
	public EmployeeActivity updateLocation(EmployeeActivity employee, long employeeId) {
		EmployeeActivity existingEmployee = activityRepository.findById(employeeId).orElseThrow( () -> new ResourceNotFoundException("Employee", "id", employeeId));
		existingEmployee.setLastLocation(employee.getLastLocation());
		activityRepository.save(existingEmployee);
		return existingEmployee;
	}


	@Override
	public List<EmployeeActivity> getAllActivity() {
		return activityRepository.findByOrderById();
	}

	@Override
	public void checkInEmployee(long employeeId) {
		EmployeeActivity existingEmployee = activityRepository.findById(employeeId).orElseThrow( () -> new ResourceNotFoundException("Employee", "id", employeeId));
		Instant nowUtc = Instant.now();
        ZoneId asiaSingapore = ZoneId.of("Asia/Kolkata");

        ZonedDateTime nowAsiaSingapore = ZonedDateTime.ofInstant(nowUtc, asiaSingapore);
        System.out.println("now: " + nowAsiaSingapore );
        
        DateTimeFormatter format = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");  
        String formatDateTime = nowAsiaSingapore.format(format);
        existingEmployee.setCheckInTime(formatDateTime);
        existingEmployee.setCheckOutTime(null);
		activityRepository.save(existingEmployee);
		
	}

	@Override
	public void checkOutEmployee(long employeeId) {
		EmployeeActivity existingEmployee = activityRepository.findById(employeeId).orElseThrow( () -> new ResourceNotFoundException("Employee", "id", employeeId));
		Instant nowUtc = Instant.now();
        ZoneId asiaSingapore = ZoneId.of("Asia/Kolkata");
        ZonedDateTime nowAsiaSingapore = ZonedDateTime.ofInstant(nowUtc, asiaSingapore);
        System.out.println("now: " + nowAsiaSingapore );
        DateTimeFormatter format = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");  
        String formatDateTime = nowAsiaSingapore.format(format);
        existingEmployee.setCheckOutTime(formatDateTime);
		activityRepository.save(existingEmployee);	
	}


	@Override
	public EmployeeActivity getActivityById(long employeeId) {
		Optional<EmployeeActivity> employee = activityRepository.findById(employeeId);
		if(employee.isPresent()){
			return employee.get();
		}else {
			throw new ResourceNotFoundException("Employee", "id", employeeId);
		}
	}


	@Override
	public void sendAlert(EmployeeAlert employeeAlert) {
		Instant nowUtc = Instant.now();
        ZoneId asiaSingapore = ZoneId.of("Asia/Kolkata");
        ZonedDateTime nowAsiaSingapore = ZonedDateTime.ofInstant(nowUtc, asiaSingapore);
        System.out.println("now: " + nowAsiaSingapore );
        DateTimeFormatter format = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");  
        String formatDateTime = nowAsiaSingapore.format(format);
		employeeAlert.setAlertTime(formatDateTime);
        
        alertRepository.save(employeeAlert);
	}


	@Override
	public List<EmployeeAlert> getAllAlerts() {
		return alertRepository.findByOrderById();
	}


	@Override
	public void deleteAlert(long alertId) {
		alertRepository.deleteById(alertId);
		
	}

	@Override
	public long uploadImage(MultipartFile multipartImage, long employeeId) throws Exception {
		Employee existingEmployee = employeeRepository.findById(employeeId).orElseThrow( () -> new ResourceNotFoundException("Employee", "id", employeeId));
		existingEmployee.setName(multipartImage.getName());
		existingEmployee.setContent(multipartImage.getBytes());

        return employeeRepository.save(existingEmployee)
            .getId();
	}


	@Override
	public ByteArrayResource downloadImage(Long imageId) {
		byte[] image = employeeRepository.findById(imageId)
			      .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND))
			      .getContent();

		return new ByteArrayResource(image);
	}






}
	

