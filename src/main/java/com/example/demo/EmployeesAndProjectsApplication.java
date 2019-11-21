package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication(scanBasePackages = "com.example.demo")
@EnableSwagger2
public class EmployeesAndProjectsApplication {

	public static void main(String[] args) {
		SpringApplication.run(EmployeesAndProjectsApplication.class, args);
	}

}
