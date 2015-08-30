package com.dragan1982.movies.dao.impl;

import java.util.List;



import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.dragan1982.movies.dao.UserDao;
import com.dragan1982.movies.model.User;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;  

@Transactional
@Repository
public class UserDaoImpl implements UserDao {

	@Autowired
	private SessionFactory sessionFactory;

	public void saveUser(User user) {
		
		BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();  
		  String hashedPassword = passwordEncoder.encode(user.getPassword());  
		user.setPassword(hashedPassword);
		
		user.setRoles(user.getRoles());
		
		getSession().merge(user);
	}

	@SuppressWarnings("unchecked")
	public List<User> listUsers() {
		
		Criteria c = getSession().createCriteria(User.class);		
		c.addOrder(Order.desc("username"));
		
			
		return c.list();		
	}

	public User getUser(int id) {
		return (User) getSession().get(User.class, id);
	}

	public void deleteUser(int id) {

		User user = getUser(id);

		if (null != user) {
			getSession().delete(user);
		}

	}
	
	public void userVoted(int id, boolean set) {
		
		User user = getUser(id);

		if (null != user) {					
			
			user.setHasVoted(set);	
		}		
	}
	
	
	public User findUserByName(String username) {
		Criteria c = getSession().createCriteria(User.class);
		c.add(Restrictions.eq("username", username));
		return (User) c.uniqueResult();
	}

	private Session getSession() {
		Session sess = getSessionFactory().getCurrentSession();
		if (sess == null) {
			sess = getSessionFactory().openSession();
		}
		return sess;
	}

	private SessionFactory getSessionFactory() {
		return sessionFactory;
	}
		
}
