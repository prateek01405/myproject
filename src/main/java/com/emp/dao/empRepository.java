package com.emp.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.emp.entity.employee;

public interface empRepository extends JpaRepository<com.emp.entity.employee,Integer> {

	 public employee findByName(String fname);
	 public employee findByEmail(String email);
} 
