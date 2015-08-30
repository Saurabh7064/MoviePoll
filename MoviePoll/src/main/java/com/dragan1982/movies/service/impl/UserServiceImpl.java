package com.dragan1982.movies.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.dragan1982.movies.dao.UserDao;
import com.dragan1982.movies.model.User;
import com.dragan1982.movies.service.UserService;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserDao userDao;

	@Transactional
	public void saveUser(User user) {
		userDao.saveUser(user);
	}

	@Transactional(readOnly = true)
	public List<User> listUsers() {
		return userDao.listUsers();
	}

	@Transactional(readOnly = true)
	public User getUser(int id) {
		return userDao.getUser(id);
	}

	@Transactional
	public void deleteUser(int id) {
		userDao.deleteUser(id);
	}
	
	@Transactional
	public void userVoted(int id, boolean set) {
		userDao.userVoted(id, set);
	}
	

}
