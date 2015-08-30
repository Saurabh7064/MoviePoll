package com.dragan1982.movies.model;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.hibernate.validator.constraints.NotEmpty;

@Entity
@Table(name="user")
@XmlRootElement
public class User {
	
	private int id;	
	private String username;	
	private String password;	
	private List <Role> roles;	
	private UserStatus status;	
	private boolean hasVoted;
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	public int getId() {
		return id;
	}
	
	@NotEmpty
	@Pattern(regexp="^[A-Za-z0-9]*$")
	@Column(nullable=false)
	@XmlAttribute
	public String getUsername() {
		return username;
	}
	
	@NotEmpty
	@Column(nullable=false)
	public String getPassword() {
		return password;
	}

	@ManyToMany(fetch=FetchType.EAGER) 
	@Fetch(FetchMode.SUBSELECT)
	@JoinTable(name="UsersAndRoles", joinColumns=@JoinColumn(name="user_id"), inverseJoinColumns=@JoinColumn(name="role_id"))
	public List<Role> getRoles() {
		return roles;
	}
	
	@NotNull
	@Enumerated(EnumType.STRING)
	public UserStatus getStatus() {
		return status;
	}
	
	@Column
	public boolean isHasVoted() {
		return hasVoted;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
	public void setRoles(List<Role> roles) {
		this.roles = roles;
	}
	public void setStatus(UserStatus status) {
		this.status = status;
	}	
	public void setHasVoted(boolean hasVoted) {
		this.hasVoted = hasVoted;
	}
}
