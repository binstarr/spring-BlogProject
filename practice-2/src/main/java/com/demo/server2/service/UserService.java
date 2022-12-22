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

	public void update(User user) {
		User userEntity = userRepository.findById(user.getId()).orElseThrow(() -> {
			return new IllegalArgumentException("해당 유저를 찾을 수 없습니다.");
		});
		userEntity.setUsername(user.getUsername());
		String unEncodedPassword = user.getPassword();
		String encodedPassword = encoder.encode(unEncodedPassword);
		userEntity.setPassword(encodedPassword);
		userEntity.setEmail(user.getEmail());
		
		userRepository.save(userEntity);
	}

//	public User login(User user) {
//		User userEntity = userRepository.login(user.getUsername(), user.getPassword());
//		return userEntity;
//	}
}
