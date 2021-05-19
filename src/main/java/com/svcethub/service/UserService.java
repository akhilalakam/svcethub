package com.svcethub.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.svcethub.constant.MessageConstants;
import com.svcethub.constant.StatusConstants;
import com.svcethub.entity.User;
import com.svcethub.entity.UserProfile;
import com.svcethub.entity.UserRole;
import com.svcethub.excepton.SvcethubException;
import com.svcethub.repository.UserProfileRepository;
import com.svcethub.repository.UserRepository;
import com.svcethub.repository.UserRoleRepository;
import com.svcethub.request.UserRegistrationRequest;

@Service
public class UserService {
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private UserProfileRepository userProfileRepository;
	
	@Autowired
	private UserRoleRepository userRoleRepository;

	public void userRegistration(UserRegistrationRequest userRegistrationRequest) throws SvcethubException {
		
		if(userRepository.existsByEmail(userRegistrationRequest.getEmail())) {
			throw new SvcethubException(StatusConstants.ERROR_STATUS, MessageConstants.MSG_EXIST_EMAIL , null);
		}
		
		if(!userRoleRepository.existsByUserRole(userRegistrationRequest.getUserRole())) {
			throw new SvcethubException(StatusConstants.ERROR_STATUS, MessageConstants.MSG_NOT_EXIST_USER_ROLE , null);
		}
		
		UserRole userRole = userRoleRepository.findByUserRole(userRegistrationRequest.getUserRole());
		
		if(userRole == null) {
			throw new SvcethubException(StatusConstants.ERROR_STATUS, MessageConstants.MSG_NOT_EXIST_USER_ROLE , null);
		}
		
		
		User user = new User();
		UserProfile userProfile = new UserProfile();
		//this is for practice
		user.setEmail(userRegistrationRequest.getEmail());
		user.setPassword(userRegistrationRequest.getPassword());
		user.getUserRoles().add(userRole);
		//userRole.getUser().add(user);
		userProfile.setFirstName(userRegistrationRequest.getFirstName());
		userProfile.setLastName(userRegistrationRequest.getLastName());
		userProfile.setUser(user);
		userProfile.setDateBirth(userRegistrationRequest.getDateBirth());
		userProfile.setJoinDate(userRegistrationRequest.getJoinDate());
		userRepository.save(user);
		userProfileRepository.save(userProfile);
	}
}
