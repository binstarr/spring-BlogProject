package com.demo.server2.api;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.demo.server2.dto.ResponseDto;
import com.demo.server2.dto.User;
import com.demo.server2.service.UserService;

@RestController
public class UserApiController {
	
	@Autowired
	private UserService userService;
	
	@Autowired
	HttpSession session;
	
	@Autowired
	private AuthenticationManager authenticationManager;
	
	@PostMapping("auth/joinProc")
	public ResponseDto<Integer> join(@RequestBody User user) {
		int result = userService.saveUser(user); 
		return new ResponseDto<Integer>(HttpStatus.OK, result);
	}
	
	@PutMapping("/api/user")
	public ResponseDto<Integer> update(@RequestBody User user){
		userService.update(user);
		
		Authentication authentication = authenticationManager
				.authenticate(new UsernamePasswordAuthenticationToken(user.getUsername(), 
							user.getPassword()));
		
		SecurityContextHolder.getContext().setAuthentication(authentication);
		return new ResponseDto<Integer>(HttpStatus.OK, 1);
	}
	
//	@PostMapping("api/login")
//	public ResponseDto<?> login(@RequestBody User user){
//		User principal = userService.login(user);
//		System.out.println("principal : " + principal);
//		if(principal != null) {
//			session.setAttribute("principal", principal);
//		}
//		return new ResponseDto<>(HttpStatus.OK, principal);
	//7EF8C6379DC7791941E90E80CC43CBA9
	//7EF8C6379DC7791941E90E80CC43CBA9
//	}

}
