package com.example.demo.service;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.dto.User;
import com.example.demo.model.RoleType;
import com.example.demo.repository.UserRepository;

// 스프링이 컴포넌트 스캔을 통해서 Bean으로 등록해 준다. IOC
@Service
public class UserService {
	
	@Autowired
	private UserRepository userRepository;
	
	@Transactional
	public int saveUser(User user) {
		try {
			user.setRole(RoleType.USER);
			userRepository.save(user);
			return 1;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return -1;
	}
	
	public User login(User user){
		User userEntity = userRepository.login(user.getUsername(), user.getPassword());
		return userEntity;
	}

}
