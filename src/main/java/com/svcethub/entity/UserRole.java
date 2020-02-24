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
import javax.persistence.ManyToMany;
import javax.persistence.Table;

@Entity
@Table(name="user_role")
public class UserRole {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="user_role_id")
	private Long userRoleId;
	private String userRole;
	
	/*
	 * @ManyToMany(fetch = FetchType.LAZY , cascade = {CascadeType.PERSIST ,
	 * CascadeType.MERGE} , mappedBy="userRoles") private Set<User> user = new
	 * HashSet<User>();
	 */

	public Long getUserRoleId() {
		return userRoleId;
	}

	public void setUserRoleId(Long userRoleId) {
		this.userRoleId = userRoleId;
	}

	public String getUserRole() {
		return userRole;
	}

	public void setUserRole(String userRole) {
		this.userRole = userRole;
	}

	/*
	 * public Set<User> getUser() { return user; }
	 * 
	 * public void setUser(Set<User> user) { this.user = user; }
	 */
}
