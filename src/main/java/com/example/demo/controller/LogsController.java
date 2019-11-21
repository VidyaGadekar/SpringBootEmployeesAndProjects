package com.example.demo.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.LogDetails;
import com.example.demo.service.LogService;

@RestController
public class LogsController {
	@Autowired
	private LogService logService;
	
	@GetMapping("/logs/getAll")
	public List<LogDetails> getAllLogs(@RequestParam(name = "start",required = false, defaultValue = "0" ) int start,
			@RequestParam (name = "size",required = false, defaultValue = "0" ) int size,
			HttpServletRequest request) {
		if(start>0 && size>=0)
			return logService.getAllLogsPaginated(start-1, size);
		return logService.getAllLogs();
	}
}
