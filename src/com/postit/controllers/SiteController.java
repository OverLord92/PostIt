package com.postit.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.postit.dao.Postit;
import com.postit.dao.ServiceDAO;

@Controller
public class SiteController {

	@Autowired
	private ServiceDAO serviceDAO;
	
	@RequestMapping("/home")
	public String goHome(){	
		return "home";
	}
	
	@RequestMapping("/add")
	public String addAllPostits(@ModelAttribute Postit postit, Model model){	
		model.addAttribute("postits", serviceDAO.getAllPostits());
		return "add";
	}
	
}
