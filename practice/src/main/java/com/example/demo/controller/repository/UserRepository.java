package com.example.demo.controller.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.dto.User;

// <> <-- 뒤에는 PK 데이터 타입
public interface UserRepository extends JpaRepository<User, Integer>{

}
