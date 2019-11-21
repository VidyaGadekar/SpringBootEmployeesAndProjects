package com.example.demo.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.LogDetails;
import com.example.demo.model.Project;
import com.example.demo.repository.LogsRepository;
import com.example.demo.repository.ProjectRepository;

@Service
public class LogService {
	@Autowired
	private LogsRepository logsRepository;
	
	public List<LogDetails> getAllLogs() {
		List<LogDetails> logs = new ArrayList<>();
		logsRepository.findAll().forEach(logs::add);
		 return logs;
	}
	
	public List<LogDetails> getAllLogsPaginated(int start, int size) {
		List<LogDetails> logs = new ArrayList<>();
		logsRepository.findAll().forEach(logs::add);
		if(start>logs.size()|| size==0) {
			return null;
		}
		else if(start+size>logs.size())
			return logs.subList(start, logs.size());
		else
			return logs.subList(start, start+size);
	}
	
	public void addLogs(LogDetails logs) {
		logsRepository.save(logs);
	}

}
