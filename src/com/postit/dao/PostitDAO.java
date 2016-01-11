package com.postit.dao;

import java.util.Iterator;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class PostitDAO {

	@Autowired 
	private SessionFactory sessionFactory;
	
	public Session session(){
		return sessionFactory.getCurrentSession();
	}
	
	public void createPostit(Postit postit){
		session().save(postit);
	}
	
	public void addPostit(Postit postit, String username) {
		User user = (User)session().get(User.class, username);
		List<Postit> postits = user.getPostits();
		
		session().save(postit);
		postits.add(postit);
		
		session().update(user);
	}
	
	public Postit getPostit(int postitId) {
		return (Postit)session().get(Postit.class, postitId);
	}
	
	public void matkAsDone(int postitId) {
		Postit postit = (Postit)session().get(Postit.class, postitId);
		postit.setActive(false);
		session().update(postit);
	}

	public void deletePostit(int postitId, String username) {
		User user = (User)session().get(User.class, username);
		List<Postit> postits = user.getPostits();
		
		Postit postit = (Postit)session().get(Postit.class, postitId);
		session().delete(postit);
		
		Postit currentPostit;
		
		for(Iterator<Postit> iterator = postits.listIterator(); iterator.hasNext(); ) {
			currentPostit = iterator.next();
			
			if(postit.equals(currentPostit)){
				iterator.remove();
				System.out.println("upalio itarator");
			}
		}
		
	}

	public void updatePostit(Postit postit) {
		session().update(postit);
	}

	
	
}
