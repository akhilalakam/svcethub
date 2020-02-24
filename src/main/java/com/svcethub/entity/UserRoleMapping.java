package com.svcethub.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.ManyToAny;

@Entity
@Table(name="user_role_mappig")
public class UserRoleMapping {

	@Id
	@GeneratedValue(strategy =GenerationType.IDENTITY)
	private Long userRoleMappingId;
	
	@ManyToOne
	@JoinColumn(name= "user_id")
	private User user; 
	
	@ManyToOne
	@JoinColumn(name = "user_role_id")
	private UserRole userRole;

	public Long getUserRoleMappingId() {
		return userRoleMappingId;
	}

	public void setUserRoleMappingId(Long userRoleMappingId) {
		this.userRoleMappingId = userRoleMappingId;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public UserRole getUserRole() {
		return userRole;
	}

	public void setUserRole(UserRole userRole) {
		this.userRole = userRole;
	}
}
