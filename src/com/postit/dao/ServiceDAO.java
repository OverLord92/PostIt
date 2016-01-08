package com.postit.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ServiceDAO {
	
	@Autowired
	private UserDAO userDAO;
	
	@Autowired
	private PostitDAO postitDAO;
	
	public void createUser(User user){
		userDAO.createUser(user);
	}
	
	public boolean userExists(String username){
		return userDAO.userExists(username);
	}
	
	public void createPostIt(Postit postit){
		postitDAO.create(postit);
	}

	public List<Postit> getAllPostits() {
		return postitDAO.getAllPostits();
	}
		
	public void markPostitAsDone(int postitId) {
		postitDAO.matkAsDone(postitId);
	}
}
