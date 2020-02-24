package com.svcethub.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.svcethub.entity.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
	
	boolean existsByUserId(Long userId);
	boolean existsByEmail(String email);
	
	User findByEmail(String email);
}
