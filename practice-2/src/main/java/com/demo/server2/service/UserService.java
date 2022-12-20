package com.demo.server2.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.demo.server2.dto.User;
import com.demo.server2.model.RoleType;
import com.demo.server2.repository.UserRepository;

@Service
public class UserService {
	
	@Autowired
	private BCryptPasswordEncoder encoder;
	
	@Autowired
	private UserRepository userRepository;

	public int saveUser(User user) {
		try {
			String unEncodedPassword = user.getPassword();
			String encodedPassword = encoder.encode(unEncodedPassword);
			user.setPassword(encodedPassword);
			user.setRole(RoleType.USER);
			userRepository.save(user);
			return 1;
		} catch (Exception e) {
		}
		return -1;
	}

//	public User login(User user) {
//		User userEntity = userRepository.login(user.getUsername(), user.getPassword());
//		return userEntity;
//	}
}
