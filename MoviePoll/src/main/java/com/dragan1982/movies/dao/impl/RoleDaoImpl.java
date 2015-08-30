package com.dragan1982.movies.dao.impl;

import java.util.List;


import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.dragan1982.movies.dao.RoleDao;
import com.dragan1982.movies.model.Role;

@Transactional
@Repository
public class RoleDaoImpl implements RoleDao {

	@Autowired
	private SessionFactory sessionFactory;

	public void saveRole(Role role) {

		getSession().merge(role);
	}

	@SuppressWarnings("unchecked")
	public List<Role> listRoles() {
		
		Criteria c = getSession().createCriteria(Role.class);		
		c.addOrder(Order.desc("roleName"));
		
			
		return c.list();		
	}

	public Role getRole(Long id) {
		return (Role) getSession().get(Role.class, id);
	}

	public void deleteRole(Long id) {

		Role role = getRole(id);

		if (null != role) {
			getSession().delete(role);
		}

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
