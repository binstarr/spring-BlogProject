package com.demo.server2.auth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.demo.server2.dto.User;
import com.demo.server2.repository.UserRepository;

@Service
public class PrincipalDetailService implements UserDetailsService {

	@Autowired
	private UserRepository userRepository;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User principal = userRepository.findByUsername(username).orElseThrow(() -> {
			System.out.println("여기네");
			return new UsernameNotFoundException("해당 유저를 찾을 수 없습니다.");
		});
		System.out.println("★ principal: " + principal);
		return new PrincipalDetail(principal);
	}

}
