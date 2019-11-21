package com.example.demo.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.LogDetails;
import com.example.demo.model.Project;
import com.example.demo.repository.ProjectRepository;

@Service
public class ProjectService {
	@Autowired
	private ProjectRepository projectRepository;
	public List<Project> getAllProjects() {
		List<Project> projects = new ArrayList<>();
		projectRepository.findAll().forEach(projects::add);
		 return projects;
	}

	public Optional<Project> getProject(Integer id) {
		return projectRepository.findById(id);
	}

	public void addProject(Project project) {
		projectRepository.save(project);
	}

	public void updateProject(Integer id, Project project) {
		Project project1 = projectRepository.findByProjectId(id);
		if(project1!=null) {
			project1.setProjectId(project.getProjectId());
			project1.setProjectName(project.getProjectName());
			projectRepository.save(project1);
		}
		else {
			System.out.println("Nothing to update");
		}
		
		
	}

	public void deleteProject(Integer id) {
		projectRepository.deleteById(id);
		
	}

	public List<Project> getAllProjectsPaginated(int start, int size) {
		List<Project> projects = new ArrayList<>();
		projectRepository.findAll().forEach(projects::add);
		if(start>projects.size() || size==0) {
			return null;
		}
		else if(start+size>projects.size())
			return projects.subList(start, projects.size());
		else
			return projects.subList(start, start+size);
	}

}