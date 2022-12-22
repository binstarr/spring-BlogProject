package com.demo.server2.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.demo.server2.dto.Reply;

public interface ReplyRepository extends JpaRepository<Reply, Integer> {

}
