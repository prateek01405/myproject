package com.emp.service;

import java.util.List;



import com.emp.entity.employee;


public interface empService {

	
	public com.emp.entity.employee savEemp(employee emp);
	
	public List<employee>getAllEmp();
	
	public employee getEmpById(int id);
	
	public boolean delEmp(int id);
	
	public employee getEmpByName(String name);
}
