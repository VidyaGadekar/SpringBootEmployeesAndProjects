package com.example.demo.controller;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.Employee;
import com.example.demo.model.LogDetails;
import com.example.demo.model.Project;
import com.example.demo.service.EmployeeService;
import com.example.demo.service.LogService;

@RestController
public class EmployeeController {
	@Autowired
	private EmployeeService employeeService;
	
	@Autowired
	private LogService logService;
	
	Logger logger = LoggerFactory.getLogger(EmployeeController.class);
	
	private List<Employee> employees;
	LocalDateTime start, end;
	
	
	@GetMapping("/projects/{id}/employees/getAll")
	public List<Employee> getAllEmployees(@PathVariable int id,HttpServletRequest request) {
		LogDetails log = new LogDetails();
		start = LocalDateTime.now();
		String url = request.getRequestURI();
		employees = employeeService.getAllEmployees(id);
		end = LocalDateTime.now();
		log.setEndPoint(url);
		log.setStartTime(start);
		log.setEndTime(end);
		logService.addLogs(log);
		logger.trace("Getting all Employees of specific project");
		return employees;
	}
	
	@GetMapping("/projects/{projectId}/employees/get/{id}")
	public Optional<Employee> getEmployee(@PathVariable int projectId,@PathVariable int id,HttpServletRequest request) {
		LogDetails log = new LogDetails();
		start = LocalDateTime.now();
		String url = request.getRequestURI();
		Optional<Employee> employee = employeeService.getEmployee(projectId, id);
		end = LocalDateTime.now();
		log.setEndPoint(url);
		log.setStartTime(start);
		log.setEndTime(end);
		logService.addLogs(log);
		logger.trace("Getting employee details of entered project id and employee id");
		return employee;
	}
	
	@PostMapping(value = "/projects/{projectId}/employees/add")
	public void addEmployee(@RequestBody Employee employee, @PathVariable int projectId,HttpServletRequest request) {
		LogDetails log = new LogDetails();
		start = LocalDateTime.now();
		String url = request.getRequestURI();
		employee.setProject(new Project(projectId, ""));
		employeeService.addEmployee(employee);	
		end = LocalDateTime.now();
		log.setEndPoint(url);
		log.setStartTime(start);
		log.setEndTime(end);
		logService.addLogs(log);
		logger.trace("Adding employee to given project");
	}
	
	@PutMapping(value = "/projects/{projectId}/employees/update/{id}")
	public void updateEmployee(@RequestBody Employee employee,@PathVariable int projectId,
			@PathVariable int id,HttpServletRequest request) {
		LogDetails log = new LogDetails();
		start = LocalDateTime.now();
		String url = request.getRequestURI();
		employee.setProject(new Project(projectId, ""));
		employeeService.updateEmployee(id,employee);	
		end = LocalDateTime.now();
		log.setEndPoint(url);
		log.setStartTime(start);
		log.setEndTime(end);
		logService.addLogs(log);
		logger.trace("Updating employee info of entered project id and employee id");
	}
	
	@DeleteMapping(value = "/projects/{projectId}/employees/delete/{id}")
	public void deleteEmployee(@PathVariable int projectId,@PathVariable int id,HttpServletRequest request) {
		LogDetails log = new LogDetails();
		start = LocalDateTime.now();
		String url = request.getRequestURI();
		employeeService.deleteEmployee(projectId,id);		
		end = LocalDateTime.now();
		log.setEndPoint(url);
		log.setStartTime(start);
		log.setEndTime(end);
		logService.addLogs(log);
		logger.trace("Deleting employee info of entered project id and employee id");
	}

}
