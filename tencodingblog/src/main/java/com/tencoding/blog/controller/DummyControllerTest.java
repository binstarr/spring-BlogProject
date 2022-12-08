package com.tencoding.blog.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tencoding.blog.controller.repository.UserRepository;
import com.tencoding.blog.dto.User;
import com.tencoding.blog.model.RoleType;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/dummy")
public class DummyControllerTest {
	
	@Autowired // 순환 참조 조심 !!! 
	private UserRepository userRepository;
	
	@GetMapping("/user/{id}")
	public User detail(@PathVariable int id) {
		
//		User user = userRepository.findById(id).orElseGet(() -> {
//			//커스텀 한 데이터
//			return new User();
//		});
		
		// 예외 처리
		User user = userRepository.findById(id).orElseThrow(() -> {
			
			return new IllegalArgumentException("해당 유저는 없습니다 : " + id);
		});
		
		return user;
	}
	
	// http://localhost:9090/blog/dummy/signup
	@PostMapping("/signup")
	public String signUp(@RequestBody User user) {
	
		log.info(">>> User : {}", user);
		
		user.setRole(RoleType.USER);
		userRepository.save(user);
		
		return "회원가입이 완료 되었습니다.";
	}

}
