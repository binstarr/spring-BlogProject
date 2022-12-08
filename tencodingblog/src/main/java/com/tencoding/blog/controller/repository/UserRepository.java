package com.tencoding.blog.controller.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tencoding.blog.dto.User;

// DAO
// 여기서는 굳이 Bean으로 등록 요청을 하지 않아도 등록을 시켜 준다.     <> 안에 Integer는 PK의 데이터 타입이다.
public interface UserRepository extends JpaRepository<User, Integer> {

}
