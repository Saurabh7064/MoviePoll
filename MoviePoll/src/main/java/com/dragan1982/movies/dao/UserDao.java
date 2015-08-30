package com.dragan1982.movies.dao;

import java.util.List;

import com.dragan1982.movies.model.User;

public interface UserDao {

	public void saveUser(User user); 

	public List<User> listUsers();
	public User getUser(int id);

	public void deleteUser(int id);

	public void userVoted(int id, boolean set);
	
	public User findUserByName(String username);
	
}
