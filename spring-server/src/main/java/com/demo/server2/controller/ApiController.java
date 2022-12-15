package com.demo.server2.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.demo.server2.dto.User;

@RestController
@RequestMapping("/api/server")
public class ApiController {

	@GetMapping("/hello")
	public User serverHello() {
		User user = new User();
		user.setAge("10");
		user.setName("홍길동");
		
		return user;
	}
	
	@GetMapping("/hello/{userId}/name/{username}")
	public User serverHello(@PathVariable String userId, @PathVariable String username) {
		User user = new User();
		System.out.println("userId : " + userId);
		System.out.println("username : " + username);
		user.setAge("10");
		user.setName("홍길동");
		
		return user;
	}
	
}
