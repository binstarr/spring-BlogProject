package com.demo.server2.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.demo.server2.dto.Board;
import com.demo.server2.dto.Reply;
import com.demo.server2.dto.User;
import com.demo.server2.repository.BoardRepository;
import com.demo.server2.repository.ReplyRepository;

@Service
public class BoardService {
	
	@Autowired
	private BoardRepository boardRepository;
	
	@Autowired
	private ReplyRepository replyRepository;


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

	@Transactional
	public void writeReply(int boardId, Reply reqReply, User user) {
		
		Board board = boardRepository.findById(boardId).orElseThrow(() -> {
			return new IllegalArgumentException("댓글 쓰기 실패 : 게시글이 존재하지 않습니다.");
		});
		
		reqReply.setUser(user);
		reqReply.setBoard(board);
		replyRepository.save(reqReply);
		
		
	}

	@Transactional
	public void deleteReply(int replyId, int reqId) {
		
		Reply replyEntity = replyRepository.findById(replyId).orElseThrow(() -> {
			return new IllegalArgumentException("댓글 삭제 실패 : 댓글이 존재하지 않습니다.");
		});
		
		int foundIdByReply = replyEntity.getUser().getId();
		int principalId = reqId;
		
		if(foundIdByReply == principalId) {
			replyRepository.deleteById(replyId);
		}else {
			throw new IllegalArgumentException("댓글 삭제 실패 : 댓글이 존재하지 않습니다.");
		}
	}

	
	
	
}
