package com.example.demo.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.Employee;
import com.example.demo.model.Project;
import com.example.demo.repository.EmployeeRepository;

@Service
public class EmployeeService {
	
	@Autowired
	private EmployeeRepository employeeRepository;
	
	public List<Employee> getAllEmployees(Integer projectId) {
		 List<Employee> employees = new ArrayList<>();
			employeeRepository.findByProjectProjectId(projectId).forEach(employees::add);
			 return employees;
	}

	public Optional<Employee> getEmployee(Integer projectId, Integer id) {	
		List<Employee> employees = new ArrayList<>();
		employees = getAllEmployees(projectId);
		for(Integer i=0;i<employees.size();i++) {
			if(employees.get(i).getEmployeeId()==id) {
				return employeeRepository.findById(id);
			}
		}		
		return null;
	}

	public void addEmployee(Employee employee) {
		employeeRepository.save(employee);
	}

	public void updateEmployee(int id, Employee employee) {
		Employee employee1 = employeeRepository.findByEmployeeId(id);
		if(employee1!=null) {
			employee1.setEmployeeId(employee.getEmployeeId());
			employee1.setEmployeeName(employee.getEmployeeName());
			employee1.setEmail(employee.getEmail());
			employee1.setProject(employee.getProject());
			employeeRepository.save(employee1);
		}
		else {
			System.out.println("Nothing to update");
		}
	}

	public void deleteEmployee(Integer projectId, Integer id) {
		employeeRepository.deleteById(id);
		
		List<Employee> employees = new ArrayList<>();
		employees = getAllEmployees(projectId);
		for(Integer i=0;i<employees.size();i++) {
			if(employees.get(i).getEmployeeId()==id) {
				employeeRepository.deleteById(id);
			}
		}	
	}

}
