package com.dragan1982.movies.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.dragan1982.movies.dao.RoleDao;
import com.dragan1982.movies.model.Role;
import com.dragan1982.movies.service.RoleService;

@Service
public class RoleServiceImpl implements RoleService {

	@Autowired
	private RoleDao roleDao;

	@Transactional
	public void saveRole(Role role) {
		roleDao.saveRole(role);
	}

	@Transactional(readOnly = true)
	public List<Role> listRoles() {
		return roleDao.listRoles();
	}

	@Transactional(readOnly = true)
	public Role getRole(Long id) {
		return roleDao.getRole(id);
	}

	@Transactional
	public void deleteRole(Long id) {
		roleDao.deleteRole(id);
	}

}
