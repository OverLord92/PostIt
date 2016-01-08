package com.postit.dao;

import java.util.List;

import org.hibernate.Criteria;
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
	
	public void create(Postit postit) {
		session().save(postit);
	}
	
	@SuppressWarnings("unchecked")
	public List<Postit> getAllPostits(){
		Criteria criteria = session().createCriteria(Postit.class);
		return criteria.list();
	}

	public void matkAsDone(int postitId) {
		Postit postit = (Postit)session().get(Postit.class, postitId);
		postit.setActive(false);
		session().update(postit);
	}

	
	
}
