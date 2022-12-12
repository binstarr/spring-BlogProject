package com.example.demo.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dto.ResponseDto;
import com.example.demo.dto.User;
import com.example.demo.service.UserService;

@RestController
@RequestMapping("/api")
public class UserApiController {
	
	//DI
	@Autowired
	private UserService userService;
	
	private HttpSession session;
	
	@PostMapping("/user")
	public ResponseDto<Integer> save(@RequestBody User user){
		
		int result = userService.saveUser(user);
		
		return new ResponseDto<Integer>(HttpStatus.OK, result);
	
	}
	
	@PostMapping("/user/login")
	public ResponseDto<Integer> login(@RequestBody User user){
		User principal = userService.login(user);
		if(principal != null) {
			session.setAttribute("principal", principal); // key value 저장!!
		}
		return new ResponseDto<Integer>(HttpStatus.OK, 1); // 자바 Object --> JSON 형식으로!
	}
	

}
