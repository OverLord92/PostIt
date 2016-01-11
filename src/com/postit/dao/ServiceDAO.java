package com.postit.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ServiceDAO {

	@Autowired
	private UserDAO userDAO;

	@Autowired
	private PostitDAO postitDAO;

	public void createUser(User user) {
		userDAO.createUser(user);
	}

	public User getuser(String username) {
		return userDAO.getuser(username);
	}

	public boolean userExists(String username) {
		return userDAO.userExists(username);
	}

	public Postit getPostit(int postitId) {
		return postitDAO.getPostit(postitId);
	}

	public void createPostIt(Postit postit) {
		postitDAO.createPostit(postit);
	}
	
	public void createPostIt(Postit postit, String username) {
		postitDAO.addPostit(postit, username);
	}

	public void deletePostit(int postitId, String username) {
		postitDAO.deletePostit(postitId, username);
	}

	public void markPostitAsDone(int postitId) {
		postitDAO.matkAsDone(postitId);
	}

	public void updatePostit(Postit postit) {
		postitDAO.updatePostit(postit);
	}
}
