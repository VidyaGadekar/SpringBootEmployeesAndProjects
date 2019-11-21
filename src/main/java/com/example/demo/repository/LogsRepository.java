package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.model.LogDetails;

public interface LogsRepository extends JpaRepository<LogDetails, Integer>{

}
