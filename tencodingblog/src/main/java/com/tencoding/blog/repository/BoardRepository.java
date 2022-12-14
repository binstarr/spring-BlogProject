package com.tencoding.blog.repository;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.tencoding.blog.dto.Board;
import com.tencoding.blog.dto.Image;

public interface BoardRepository extends JpaRepository<Board, Integer> {

	// native 쿼리, naming 전략, .... QLRM ....
	// select * from board where title like '%q%';
	Page<Board> findBytitleContaining(String q, Pageable pageable);
	

	
}
