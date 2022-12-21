package com.demo.server2.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.demo.server2.dto.Board;
import com.demo.server2.dto.User;
import com.demo.server2.repository.BoardRepository;

@Service
public class BoardService {
	
	@Autowired
	private BoardRepository boardRepository;


	public void write(Board board, User user) {
		
		board.setUser(user);
		boardRepository.save(board);
	}

	@Transactional(readOnly = true)
	public Page<Board> getBoardList(Pageable pageable) {
		
		return boardRepository.findAll(pageable);
	}

	public Object boardDetail(int id) {
		return boardRepository.findById(id).orElseThrow(() ->{
			return new IllegalArgumentException("없습니다.");
		});
	}

	@Transactional
	public void deleteById(int id) {
		boardRepository.deleteById(id);
	}

	@Transactional
	public int updateBoard(int id, Board board) {
		Board boardEntity = boardRepository.findById(id).orElseThrow(() -> {
			return new IllegalArgumentException("없다");
		});
		boardEntity.setTitle(board.getTitle());
		boardEntity.setContent(board.getContent());
		return 1;
	}

	
	
}
