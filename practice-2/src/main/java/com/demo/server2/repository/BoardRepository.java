package com.demo.server2.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.demo.server2.dto.Board;

public interface BoardRepository extends JpaRepository<Board, Integer> {

	Page<Board> findByTitleContaining(String searchValue, Pageable pageable);


}
