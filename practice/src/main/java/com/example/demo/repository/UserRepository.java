package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.demo.dto.User;

// <> <-- 뒤에는 PK 데이터 타입
public interface UserRepository extends JpaRepository<User, Integer>{
	
	
	// 없는 함수는 직접 함수를 만들거나 또는 spring JAP 네이밍 전략
	// SELECT * FROM user where username = '?'1 and password = '?'2
	// SELECT * FROM user where username = 'teco' and password = 'asd1234'
	User findByUsernameAndPassword(String username, String password);
	
	// 두번째 방법 네이티브 쿼리 만들기
	@Query(value = "SELECT *"
			+ "FROM user"
			+ " where username = ?1"
			+ " and password = ?2", nativeQuery = true)
	User login(String username, String password);


}
