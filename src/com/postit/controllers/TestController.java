package com.postit.controllers;

import java.security.Principal;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.postit.dao.Postit;
import com.postit.dao.ServiceDAO;
import com.postit.dao.User;

@Controller
public class TestController {
	
	@Autowired
	private ServiceDAO serviceDAO;

	@RequestMapping("/test")
	public void test(Principal principal){
		
		User user = new User("korisnik", "sifra", "encodedPassword", true, User.USER);
		user.setEncodedPassword("kodirana sifra");
		serviceDAO.createUser(user);
		
		Postit postit1 = new Postit("naslov1", "jedan", "12-02-2015", new Date(), true);
		serviceDAO.createPostIt(postit1);
		
		Postit postit2 = new Postit("naslov1", "jedan", "12-02-2015", new Date(), true);
		serviceDAO.createPostIt(postit2);
		
		Postit postit3 = new Postit("naslov1", "jedan", "12-02-2015", new Date(), true);
		serviceDAO.createPostIt(postit3);
		
		Postit postit4 = new Postit("naslov1", "jedan", "12-02-2015", new Date(), true);
		serviceDAO.createPostIt(postit4);
	}
}