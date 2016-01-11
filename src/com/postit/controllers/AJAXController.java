package com.postit.controllers;

import java.security.Principal;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.postit.dao.Postit;
import com.postit.dao.ServiceDAO;
import com.postit.dao.User;
import com.postit.util.UtilityMethods;

@Controller
public class AJAXController {

	@Autowired
	private ServiceDAO serviceDAO;

	/** Checking user availability using AJAX */
	@RequestMapping("checkIfUserExists")
	public @ResponseBody String doesUserExist(HttpServletRequest request) {

		String username = request.getParameter("username");
		boolean userExists = serviceDAO.userExists(username);

		if (userExists)
			return "false";
		else
			return "true";

	}

	/**
	 * Add new postit
	 * 
	 * @throws ParseException
	 */
	@RequestMapping(value = "addPostit", method = RequestMethod.POST, consumes = "application/json", produces = "application/json")
	public @ResponseBody Map<String, Object> addPostit(@RequestBody Map<String, Object> data, Principal principal)
			throws ParseException {

		String subject = (String) data.get("subject");
		String text = (String) data.get("text");
		String dateString = (String) data.get("date");

		Date date = UtilityMethods.convertStringToDate(dateString);

		Postit postit = new Postit(subject, text, dateString, date, true);
		String username = principal.getName();

		serviceDAO.createPostIt(postit, username);

		Map<String, Object> response = new HashMap<String, Object>();
		response.put("id", postit.getId());
		response.put("subject", postit.getSubject());
		response.put("text", postit.getText());
		response.put("date", dateString);

		return response;
	}

	/** Mark postit as done */
	@RequestMapping("markAsDone")
	public @ResponseBody String markPostItAsDone(HttpServletRequest request) {

		int postitId = Integer.parseInt(request.getParameter("postitId"));
		serviceDAO.markPostitAsDone(postitId);
		return "iz kontrolera" + postitId;
	}

	/** Delete postit */
	@RequestMapping("deletePostit")
	public @ResponseBody String deletePostit(HttpServletRequest request, Principal principal) {

		int postitId = Integer.parseInt(request.getParameter("postitId"));

		System.out.println("delete postit" + postitId);

		serviceDAO.deletePostit(postitId, principal.getName());
		return "iz kontrolera" + postitId;
	}

	/**
	 * Add new postit
	 * 
	 * @throws ParseException
	 */
	@RequestMapping(value = "searchPostits", method = RequestMethod.POST, consumes = "application/json", produces = "application/json")
	public @ResponseBody Map<String, Object> searchPostit(@RequestBody Map<String, Object> data, Principal principal)
			throws ParseException {

		String dateString = (String) data.get("date");
		Date date = UtilityMethods.convertStringToDate(dateString);

		String username = principal.getName();
		User user = (User) serviceDAO.getuser(username);
		List<Postit> allUserPostits = user.getPostits();
		
		List<Postit> filteredPostits;

		String searchType = (String) data.get("searchType");
		switch (searchType) {
			case "byDay": {
				filteredPostits = UtilityMethods.filterByDay(allUserPostits, date);
				break;
			}
			case "byWeek": {
				Date firstDayOfWeek = UtilityMethods.getFirstDayOfWeek(date);
				Date lastDayOfWeek = UtilityMethods.getLastDayOfWeek(date);
				filteredPostits = UtilityMethods.filterByDateInterval(allUserPostits, firstDayOfWeek, lastDayOfWeek);
				break;
			}
			case "byMonth": {
				Date firstDayOfMonth = UtilityMethods.getFirstDayOfMonth(date);
				Date lastDayOfMonth = UtilityMethods.getLastDayOfMonth(date);
				filteredPostits = UtilityMethods.filterByDateInterval(allUserPostits, firstDayOfMonth, lastDayOfMonth);
				break;
			}
			default: {
				filteredPostits = new ArrayList<>();
			}
		}

		Map<String, Object> response = new HashMap<String, Object>();
		response.put("filteredPostits", filteredPostits);
		return response;
	}

}
