package com.postit.controllers;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.postit.dao.Postit;
import com.postit.dao.ServiceDAO;
import com.postit.dao.User;

@Controller
public class SiteController {

	@Autowired
	private ServiceDAO serviceDAO;
	
	@RequestMapping("/home")
	public String goHome(){	
		return "home";
	}
	
	@RequestMapping("/add")
	public String addAllPostits(@ModelAttribute Postit postit, Model model, Principal principal){
		
		String username = principal.getName();
		User user = serviceDAO.getuser(username);
		model.addAttribute("postits", user.getPostits());
		return "add";
	}
	
	@RequestMapping("/done")
	public String showDonePostits(Model model, Principal principal){
		
		String username = principal.getName();
		User user = serviceDAO.getuser(username);
		model.addAttribute("postits", user.getPostits());
		return "done";
	}
	
	@RequestMapping("/search")
	public String showSearch(){
		return "search";
	}
	
}
