package com.svcethub.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.svcethub.entity.UserRole;

@Repository
public interface UserRoleRepository extends JpaRepository<UserRole, Long> {
	
	boolean existsByUserRole(String userRole);
	UserRole findByUserRole(String userRole);

}
