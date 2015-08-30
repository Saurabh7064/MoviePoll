package com.dragan1982.movies.dao;

import java.util.List;

import com.dragan1982.movies.model.Role;

public interface RoleDao {

	public void saveRole (Role role); 

	public List<Role> listRoles();
	public Role getRole(Long id);

	public void deleteRole(Long id);
	
}
