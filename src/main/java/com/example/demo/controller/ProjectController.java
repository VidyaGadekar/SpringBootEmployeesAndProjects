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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.LogDetails;
import com.example.demo.model.Project;
import com.example.demo.service.LogService;
import com.example.demo.service.ProjectService;

@RestController
public class ProjectController {
	@Autowired
	private ProjectService projectService;
	
	@Autowired
	private LogService logService;
	
	Logger logger = LoggerFactory.getLogger(ProjectController.class);
	LocalDateTime startTime, endTime;
	
	
	private List<Project> projects;
	
	@GetMapping("/projects/getAll")
	public List<Project> getAllProjects(@RequestParam(name = "start",required = false, defaultValue = "0" ) int start,
			@RequestParam (name = "size",required = false, defaultValue = "0" ) int size,
			HttpServletRequest request) {
		LogDetails log = new LogDetails();
		startTime = LocalDateTime.now();
		String url = request.getRequestURI();
		if(start>0 && size>=0)
			projects = projectService.getAllProjectsPaginated(start-1, size);
		else
			projects = projectService.getAllProjects();
		endTime = LocalDateTime.now();
		log.setEndPoint(url);
		log.setStartTime(startTime);
		log.setEndTime(endTime);
		logService.addLogs(log);
		logger.trace("Getting all Projects");
		return projects;
	}
	
	@GetMapping("/projects/get/{id}")
	public Optional<Project> getProject(@PathVariable Integer id, HttpServletRequest request) {
		LogDetails log = new LogDetails();
		startTime = LocalDateTime.now();
		String url = request.getRequestURI();
		Optional<Project> project = projectService.getProject(id);
		endTime = LocalDateTime.now();
		log.setEndPoint(url);
		log.setStartTime(startTime);
		log.setEndTime(endTime);
		logService.addLogs(log);
		logger.trace("Getting project with mentioned id");
		return project;
	}
	
	@PostMapping(value = "/projects/add")
	public void addProject(@RequestBody Project project, HttpServletRequest request) {
		LogDetails log = new LogDetails();
		startTime = LocalDateTime.now();
		String url = request.getRequestURI();
		projectService.addProject(project);
		endTime = LocalDateTime.now();
		log.setEndPoint(url);
		log.setStartTime(startTime);
		log.setEndTime(endTime);
		logService.addLogs(log);
		logger.trace("Adding project");
	}
	
	@PutMapping(value = "/projects/update/{id}")
	public void updateProject(@RequestBody Project project, @PathVariable Integer id, HttpServletRequest request) {
		LogDetails log = new LogDetails();
		startTime = LocalDateTime.now();
		String url = request.getRequestURI();
		projectService.updateProject(id,project);
		endTime = LocalDateTime.now();
		log.setEndPoint(url);
		log.setStartTime(startTime);
		log.setEndTime(endTime);
		logService.addLogs(log);
		logger.trace("Updating project of given id");
	}
	
	@DeleteMapping(value = "/projects/delete/{id}")
	public void deleteProject(@PathVariable Integer id,HttpServletRequest request) {
		LogDetails log = new LogDetails();
		startTime = LocalDateTime.now();
		String url = request.getRequestURI();
		projectService.deleteProject(id);		
		endTime = LocalDateTime.now();
		log.setEndPoint(url);
		log.setStartTime(startTime);
		log.setEndTime(endTime);
		logService.addLogs(log);
		logger.trace("Deleting project of entered id");
	}

}
