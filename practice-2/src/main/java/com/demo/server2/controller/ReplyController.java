package com.demo.server2.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.demo.server2.dto.Board;
import com.demo.server2.repository.BoardRepository;

@RestController
public class ReplyController {
	
	@Autowired
	private BoardRepository boardRepository;
	
	@GetMapping("/test/board/{id}")
	public Board getBoard(@PathVariable int id) {
		
		return boardRepository.findById(id).get();
	}

}
