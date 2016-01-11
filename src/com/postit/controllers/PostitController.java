package com.postit.controllers;

import java.security.Principal;
import java.text.ParseException;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.postit.dao.Postit;
import com.postit.dao.ServiceDAO;
import com.postit.dao.User;
import com.postit.util.UtilityMethods;

@Controller
public class PostitController {
	
	@Autowired
	private ServiceDAO serviceDAO;
	
	/** Add new postit 
	 * @throws ParseException */
	@RequestMapping(value="/addPost", method=RequestMethod.POST)
	public String addPostit(@Valid Postit postit, Errors errors, Principal principal) throws ParseException{
		
		System.out.println("pozvana metoda sa validacijom");
		if(errors.hasErrors()){
			System.out.println("fomr does not validate");
			return "add";
		}
		
		String dateString = postit.getDateString();
		Date date = UtilityMethods.convertStringToDate(dateString);
		postit.setDate(date);
		
		String username = principal.getName();
		
		serviceDAO.createPostIt(postit, username);
		
		return "add";
	}
	
	@RequestMapping("/editPostit/{id}")
	public String editPostit(Model model, @PathVariable(value="id") int postitId,
			Principal principal, HttpServletRequest request) throws ParseException {
		System.out.println("pozvana metoda za edit");
		
		String subject = request.getParameter("subject");
		String text = request.getParameter("text");
		String dateString = request.getParameter("date");
		
		Postit postit = new Postit();
		postit.setId(postitId);
		postit.setSubject(subject);
		postit.setText(text);
		
		Date date = UtilityMethods.convertStringToDate(dateString);
		postit.setDate(date);
		postit.setActive(true);
		
		serviceDAO.updatePostit(postit);
		
		String username = principal.getName();
		User user = serviceDAO.getuser(username);
		model.addAttribute("postits", user.getPostits());
		return "add";
	}

}
