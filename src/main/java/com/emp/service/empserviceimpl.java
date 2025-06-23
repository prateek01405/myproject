package com.emp.service;
import com.emp.entity.employee;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.emp.dao.empRepository;


@Service
public class empserviceimpl implements empService {

	@Autowired
	private empRepository emprepo;
	
	@Override
	public com.emp.entity.employee savEemp(com.emp.entity.employee emp) {
		employee save = emprepo.save(emp);
		return save;
	}

	@Override
	public List<employee> getAllEmp() {

		return (List<employee>) emprepo.findAll();
	}

	@Override
	public employee getEmpById(int id) {
		
		return emprepo.findById(id).get();
	}

	@Override
	public boolean delEmp(int id) {
		employee employee = emprepo.findById(id).get();
		if(employee!=null)
		{
			emprepo.delete(employee);
			return true;
		}
		return false;
	}
	
	public void removeSession()
	{
	HttpSession session = ((ServletRequestAttributes)	(RequestContextHolder.getRequestAttributes())).getRequest().getSession();
	session.removeAttribute("msg");
	}

	@Override
	public employee getEmpByName(String name) {
		employee first = emprepo.findByName(name);
		return first;
	}

}
