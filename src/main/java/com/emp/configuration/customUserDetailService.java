package com.emp.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import com.emp.dao.empRepository;
import com.emp.entity.employee;
@Component
public class customUserDetailService  implements UserDetailsService{

	@Autowired
	private empRepository emprepo;
	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		employee byEmail = emprepo.findByEmail(email);
		if(byEmail==null)
		{
			throw new UsernameNotFoundException("user not available");
		} else
		{
			return new customUser(byEmail);
		}
		
		
		
	}

}
