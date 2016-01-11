package com.postit.controllers;

import java.security.Principal;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.postit.dao.Postit;
import com.postit.dao.ServiceDAO;
import com.postit.dao.User;
import com.postit.util.UtilityMethods;

@Controller
public class TestController {
	
	@Autowired
	private ServiceDAO serviceDAO;

	@RequestMapping("/test")
	public void test(Principal principal) throws ParseException{
		
		Postit postit1 = new Postit("naslov1", "jedan", "09-01-2016", new Date(), true);
		postit1.setDate(UtilityMethods.convertStringToDate(postit1.getDateString()));
		serviceDAO.createPostIt(postit1);
		
		Postit postit2 = new Postit("naslov2", "dva", "10-01-2016", new Date(), true);
		postit2.setDate(UtilityMethods.convertStringToDate(postit2.getDateString()));
		serviceDAO.createPostIt(postit2);
		
		Postit postit3 = new Postit("naslov3", "tri", "12-01-2016", new Date(), true);
		postit3.setDate(UtilityMethods.convertStringToDate(postit3.getDateString()));
		serviceDAO.createPostIt(postit3);
		
		Postit postit4 = new Postit("naslov4", "cetiri", "11-01-2016", new Date(), true);
		postit4.setDate(UtilityMethods.convertStringToDate(postit4.getDateString()));
		serviceDAO.createPostIt(postit4);
		
		Postit postit5 = new Postit("naslov5", "pet", "12-02-2015", new Date(), false);
		postit5.setDate(UtilityMethods.convertStringToDate(postit5.getDateString()));
		serviceDAO.createPostIt(postit5);
		
		Postit postit6 = new Postit("naslov6", "sest", "17-01-2016", new Date(), false);
		postit6.setDate(UtilityMethods.convertStringToDate(postit6.getDateString()));
		serviceDAO.createPostIt(postit6);
		
		Postit postit7 = new Postit("naslov7", "sedam", "18-01-2016", new Date(), false);
		postit7.setDate(UtilityMethods.convertStringToDate(postit7.getDateString()));
		serviceDAO.createPostIt(postit7);
		
		Postit postit8 = new Postit("naslov8", "osam", "19-01-2016", new Date(), false);
		postit8.setDate(UtilityMethods.convertStringToDate(postit8.getDateString()));
		serviceDAO.createPostIt(postit8);
		
		List<Postit> postits = new ArrayList<>(Arrays.asList(new Postit[]{postit1, postit2, postit3, postit4, postit5, postit6, postit7, postit8}));
		
		User user = new User("korisnik2", "sifra", "encodedPassword", true, User.USER);
		user.setPostits(postits);
		serviceDAO.createUser(user);
	}
}