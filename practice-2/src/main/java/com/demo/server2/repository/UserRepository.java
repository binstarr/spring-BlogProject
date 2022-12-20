package com.demo.server2.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.demo.server2.dto.User;

public interface UserRepository extends JpaRepository<User, Integer> {
	
	Optional<User> findByUsername(String username);

//	User findByusernameAndPassword(String username, String password);
	
//	@Query(value = "SELECT * "
//			+ "FROM user "
//			+ "where username = ?1 "
//			+ "and password = ?2 ", nativeQuery = true)
//	User login(String username, String password);
}
