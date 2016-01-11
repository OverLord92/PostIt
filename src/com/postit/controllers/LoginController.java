package com.postit.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.postit.dao.ServiceDAO;
import com.postit.dao.User;
import com.postit.validationGroups.FormValidationGroup;

@Controller
public class LoginController {
	
	@Autowired
	private ServiceDAO serviceDAO;
	
	@RequestMapping("/login")
	public String logIn(){
		return "login";
	}
	
	@RequestMapping("/register")
	public String showRegisterForm(@ModelAttribute User user){
		return "register";
	}
	
	@RequestMapping(value="/register", method=RequestMethod.POST)
	public String submitRegisterForm(@Validated(FormValidationGroup.class) User user, Errors errors){
		
		if(errors.hasErrors()) {
			return "register";
		}
		
		// username duplicate validation in case of Javascript is turned off
		if(serviceDAO.userExists(user.getUsername())){
			errors.reject("username", "DuplicateKey.user.username");
			return "register";
		}
		
		user.setAuthority("USER");
		user.setEnabled(true);
		serviceDAO.createUser(user);
		return "login";
	}
	
}
