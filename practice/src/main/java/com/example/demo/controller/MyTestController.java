package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.controller.repository.UserRepository;
import com.example.demo.dto.User;
import com.example.demo.model.RoleType;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/bins")
public class MyTestController {
	
	@Autowired // 순환 참조 조심!!
	private UserRepository userRepository;
	
	//search
	@GetMapping("/user/{id}")
	public User search(@PathVariable int id) {
		
		User user = userRepository.findById(id).orElseThrow(() -> {
			return new IllegalArgumentException("해당 유저는 없습니다 : " + id);
		});
		
		return user;
	}
	
	@PostMapping("/signin")
	public String signIn(@RequestBody User user) {
		user.setRole(RoleType.USER);
		userRepository.save(user);
		return "회원가입 성공!!";
	}

}
