package com.emp.controller;

import java.net.http.HttpClient.Redirect;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.emp.entity.employee;
import com.emp.service.empService;

import jakarta.servlet.http.HttpSession;

@Controller
public class homeController {
	
	@Autowired
	private empService empservice;
	
	@RequestMapping("/empbyname")
	public String empByName(@RequestParam("name")String emp ,Model m,HttpSession session)
	{
		
	employee allEmp = empservice.getEmpByName(emp);
	if(allEmp!=null)
	{
		System.out.println("=====");
		session.setAttribute("msg", "Match Found");
		System.out.println("***");
		m.addAttribute("allEmp", allEmp);
	}
	else
	{
		session.setAttribute("msg", "Not Found!!");
	}
	
	return"index";
	}
	
	@RequestMapping("")
	public String home()
	{
//		List<employee> allEmp = empservice.getAllEmp();
//		m.addAttribute("allEmp", allEmp);
		return"home";
	}
	
	
	@RequestMapping("/user")
	public String user()
	{
//		List<employee> allEmp = empservice.getAllEmp();
//		m.addAttribute("allEmp", allEmp);
		System.out.println(";;;;");
		return"user";
	}

	@RequestMapping("/empdata")
	public String empdata(Model m)
	{
		
		return"empdata";
	}
	
	
	@RequestMapping("/index")
	public String index(Model m)
	{
		
		List<employee> allEmp = empservice.getAllEmp();
		m.addAttribute("allEmp", allEmp);
		return"index";
	}
	

	

	@RequestMapping("/save")
	public String save()
	{
		return"save";
	}
	
	@PostMapping("/saveemp")
	public String saveemp(@ModelAttribute employee emp, HttpSession session)
	{
		employee newemp = empservice.savEemp(emp);
//		System.out.println(emp);
		if(newemp!=null)
		{
			
			session.setAttribute("msg", "Register successfully");
		}
		else
		{
			session.setAttribute("msg", "something went wrong!!");
		}
		
		return"redirect:/save";
	}
	
	@PostMapping("/updateEmp")
	public String updateemp(@ModelAttribute employee emp, HttpSession session)
	{
		employee updateemp = empservice.savEemp(emp);
//		System.out.println(emp);
		if(updateemp!=null)
		{
			
			session.setAttribute("msg", "update successfully");
		}
		else
		{
			session.setAttribute("msg", "something went wrong!!");
		}
		
		return"redirect:/index";
	}
	
	
	
	
	@RequestMapping("edit/{id}")
	public String edit(@PathVariable int id, Model m)
	{
	employee empById = empservice.getEmpById(id); 
	m.addAttribute("emp", empById);
	return"edit";
	}
	
	@RequestMapping("deleteemp/{id}")
	public String deleteemp(@PathVariable int id, Model m,  HttpSession session)
	{
	boolean delById = empservice.delEmp(id) ;
	if(delById)
	{
		session.setAttribute("msg", "Delete succussfully");
	}
	else {
		session.setAttribute("msg", "something went wrong");
	}
	return"redirect:/index";
	}
}
