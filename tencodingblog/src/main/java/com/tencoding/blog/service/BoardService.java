package com.tencoding.blog.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tencoding.blog.dto.Board;
import com.tencoding.blog.dto.Reply;
import com.tencoding.blog.dto.User;
import com.tencoding.blog.repository.BoardRepository;
import com.tencoding.blog.repository.ReplyRepository;

import net.bytebuddy.asm.Advice.Return;

@Service
public class BoardService {

	@Autowired
	private BoardRepository boardRepository;

	@Autowired
	private ReplyRepository replyRepository;

	public void write(Board board, User user) {

		// 가독성을 위해 한번 더 지정
		board.setCount(0);
		board.setUser(user);
		boardRepository.save(board);

	}

	// select만 하는 녀석인걸 명시한다.
	@Transactional(readOnly = true)
	public Page<Board> getBoardList(Pageable pageable) {
		return boardRepository.findAll(pageable);
	}

	public Board boardDetail(int id) {
		return boardRepository.findById(id).orElseThrow(() -> {
			return new IllegalArgumentException("해당 글을 찾을 수 없습니다.");
		});

	}

	@Transactional
	public void deleteById(int id) {
		boardRepository.deleteById(id);
	}

	@Transactional
	public int modifyBoard(int boardId, Board board) {
		// 영속화 되었다.
		Board boardEntity = boardRepository.findById(boardId).orElseThrow(() -> {
			return new IllegalArgumentException("해당 글을 찾을 수 없네요");
		});
		boardEntity.setTitle(board.getTitle());
		boardEntity.setContent(board.getContent());
		// 해당 함수 종료 시점에 트랜잭션이 종료가 되고 더티 체킹해서 commit 처리를 한다.
		return 1;
	}

	@Transactional
	public Reply writeReply(int boardId, Reply requestReply, User user) {

		// 영속화 되었다.
		Board board = boardRepository.findById(boardId).orElseThrow(() -> {
			return new IllegalArgumentException("댓글 쓰기 실패 : 게시글이 존재하지 않습니다.");
		});

		requestReply.setUser(user);
		requestReply.setBoard(board);
		Reply replyEntity =  replyRepository.save(requestReply);
		return replyEntity;

	}

	@Transactional
	public void deleteReplyById(int replyId, int requsetUserId) {
		System.out.println("requsetUserId : " + requsetUserId);

		// 댓글 단 사용자의 id 가져와서 삭제 요청 누른 사람이랑 같은지 비교
		Reply replyEntity = replyRepository.findById(replyId).orElseThrow(() -> {
			return new IllegalArgumentException("해당 글을 찾을 수 없음");
		});

		try {
			int dbWriter = replyEntity.getUser().getId();
			int principalId = requsetUserId;

			if (dbWriter == principalId) {
				replyRepository.deleteById(replyId);
			} else {
				throw new IllegalArgumentException("해당 글을 찾을 수 없음");
			}

		} catch (Exception e) {
		}
//		System.out.println(">>>>>>>>>>>>>>>" + replyEntity.getUser().getId());

	}
	
	@Transactional
	public Page<Board> searchBoard(String q, Pageable pageable){
		return boardRepository.findBytitleContaining(q, pageable);
	}

}
