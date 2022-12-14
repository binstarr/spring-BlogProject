package com.example.demo.service;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.dto.User;
import com.example.demo.repository.UserRepository;

// 스프링이 컴포넌트를 통해 Bean으로 등록 해준다.
@Service
public class UserService {
	
	@Autowired
	private UserRepository userRepository;
	
	@Transactional
	public int saveUser(User user) {
		
		try {
			user.setUsername(user.getUsername());
			user.setPassword(user.getPassword());
			user.setEmail(user.getEmail());
			userRepository.save(user);
			return 1;
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return -1;
	}

}
