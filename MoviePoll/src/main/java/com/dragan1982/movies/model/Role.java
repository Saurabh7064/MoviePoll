package com.dragan1982.movies.model;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

@Entity
public class Role {
	
	private Long id;
	private String roleName;
	private List<User> users;
	
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	public Long getId() {
		return id;
	}
	@Column
	public String getRoleName() {
		return roleName;
	}
	@Column
	@ManyToMany(mappedBy = "roles")
	public List<User> getUsers() {
		return users;
	}	
	
	public void setId(Long id) {
		this.id = id;
	}
	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}
	public void setUsers(List<User> users) {
		this.users = users;
	}
	 
}
