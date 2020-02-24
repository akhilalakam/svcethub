package com.svcethub.rest;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.svcethub.constant.MessageConstants;
import com.svcethub.constant.StatusConstants;
import com.svcethub.excepton.SvcethubException;
import com.svcethub.request.UserRegistrationRequest;
import com.svcethub.response.ResponseModel;
import com.svcethub.service.UserService;

@RestController
@RequestMapping(value ="s1/user")
public class UserController {
	
	@Autowired
	private UserService userService;
	
	@PostMapping(value ="/userRegistration")
	public ResponseModel userRegistration(@RequestBody UserRegistrationRequest userRegistrationRequest) throws SvcethubException {
		userService.userRegistration(userRegistrationRequest);
	return new ResponseModel(StatusConstants.SUCCESS_STATUS, MessageConstants.MSG_USER_REG) ; 
	}
}
