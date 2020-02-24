package com.svcethub.entity;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

@Entity
@Table(name="user")
public class User {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="user_id")
	private Long userId;
	
	private String email;
	private String password;
	
	
	public User() {
		// TODO Auto-generated constructor stub
	}
	
	public User( String email, String password) {
		super();
		this.email = email;
		this.password = password;
	}

	@ManyToMany(fetch =FetchType.LAZY , cascade = {CascadeType.PERSIST , CascadeType.MERGE})
	@JoinTable(name="user_role_mappig", joinColumns= {@JoinColumn(name="user_id")}, inverseJoinColumns = {@JoinColumn(name="user_role_id")} )
	private Set<UserRole> userRoles = new HashSet<>();

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Set<UserRole> getUserRoles() {
		return userRoles;
	}

	public void setUserRoles(Set<UserRole> userRoles) {
		this.userRoles = userRoles;
	}
}
