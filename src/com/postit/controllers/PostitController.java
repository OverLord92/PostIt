package com.postit.controllers;

import java.text.ParseException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.postit.dao.Postit;
import com.postit.util.UtilityMethods;

@Controller
public class PostitController {
	
	/** Add new postit 
	 * @throws ParseException */
	@RequestMapping(value="/addPost", method=RequestMethod.POST)
	public String addPostit(@Valid Postit postit, Errors errors) throws ParseException{
		
		System.out.println("pozvana metoda sa validacijom");
		if(errors.hasErrors()){
			System.out.println("fomr does not validate");
			return "add";
		}
//		String subject = (String)data.get("subject");
//		String text = (String)data.get("text");
//		String dateString = (String)data.get("date");
//		
//		Date date = UtilityMethods.convertStringToDate(dateString);
//
//		Postit postit = new Postit(subject, text, date, true);
//		serviceDAO.createPostIt(postit);
//		
//		Map<String, Object> response = new HashMap<String, Object>();
//		response.put("id", postit.getId());
//		response.put("subject", postit.getSubject());
//		response.put("text", postit.getText());
//		response.put("date", dateString);
		
		return "home";
	}

}
