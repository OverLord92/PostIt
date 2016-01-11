package com.postit.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Service
public class UserDAO {
	
	@Autowired
	private SessionFactory sessionFactory;
	
	public Session session(){
		return sessionFactory.getCurrentSession();
	}
	
	@Autowired 
	private PasswordEncoder encoder;

	public void createUser(User user) {
		String encodedPassword = encoder.encode(user.getPassword());
		user.setEncodedPassword(encodedPassword);
		session().save(user);
	}
	
	public User getuser(String username){
		return (User)session().get(User.class, username);
	}
	
	public boolean userExists(String username){
		return session().get(User.class, username) != null;
	}
}
