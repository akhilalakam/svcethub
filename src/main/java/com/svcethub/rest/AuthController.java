package com.svcethub.rest;

import java.net.URI;
import java.util.Collections;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.svcethub.constant.MessageConstants;
import com.svcethub.constant.StatusConstants;
import com.svcethub.entity.User;
import com.svcethub.entity.UserProfile;
import com.svcethub.entity.UserRole;
import com.svcethub.excepton.SvcethubException;
import com.svcethub.repository.UserProfileRepository;
import com.svcethub.repository.UserRepository;
import com.svcethub.repository.UserRoleRepository;
import com.svcethub.request.LoginRequest;
import com.svcethub.request.SignUpRequest;
import com.svcethub.request.UserRegistrationRequest;
import com.svcethub.response.ApiResponse;
import com.svcethub.response.JwtAuthenticationResponse;
import com.svcethub.security.JwtTokenProvider;

@RestController
@RequestMapping("/api/auth")
public class AuthController {
	
	 @Autowired
	    AuthenticationManager authenticationManager;

	    @Autowired
	    UserRepository userRepository;

	    @Autowired
	    UserRoleRepository userRoleRepository;

	    @Autowired
	    PasswordEncoder passwordEncoder;

	    @Autowired
	    JwtTokenProvider tokenProvider;
	    
	    @Autowired
	    UserProfileRepository userProfileRepository;
	    
	    
	    @PostMapping("/signin")
	    public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {

	        Authentication authentication = authenticationManager.authenticate(
	                new UsernamePasswordAuthenticationToken(
	                        loginRequest.getEmail(),
	                        loginRequest.getPassword()
	                )
	        );
	        
	        System.out.println("1");

	        SecurityContextHolder.getContext().setAuthentication(authentication);
	        System.out.println("2");

	        String jwt = tokenProvider.generateToken(authentication);
	        System.out.println("3");
	        return ResponseEntity.ok(new JwtAuthenticationResponse(jwt));
	    }
	    
	    @PostMapping("/signup")
	    public ResponseEntity<?> registerUser(@Valid @RequestBody SignUpRequest signUpRequest) {
	           /* if(userRepository.existsByEmail(signUpRequest.getUsername())) {
	                return new ResponseEntity(new ApiResponse(false, "Username is already taken!"),
	                        HttpStatus.BAD_REQUEST);
	            }*/

	            if(userRepository.existsByEmail(signUpRequest.getEmail())) {
	                return new ResponseEntity(new ApiResponse(false, "Email Address already in use!"),
	                        HttpStatus.BAD_REQUEST);
	            }

	            // Creating user's account
	            User user = new User(signUpRequest.getEmail(), signUpRequest.getPassword());

	            user.setPassword(passwordEncoder.encode(user.getPassword()));

	            UserRole userRole = userRoleRepository.findByUserRole("Student");
	                  

	            user.setUserRoles(Collections.singleton(userRole));

	            User result = userRepository.save(user);

	            URI location = ServletUriComponentsBuilder
	                    .fromCurrentContextPath().path("/api/users/{username}")
	                    .buildAndExpand(result.getUserRoles()).toUri();

	            return ResponseEntity.created(location).body(new ApiResponse(true, "User registered successfully"));
	        
	    }

}
