package com.tencoding.blog.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.tencoding.blog.auth.PrincipalDetail;
import com.tencoding.blog.dto.Board;
import com.tencoding.blog.dto.Reply;
import com.tencoding.blog.dto.ResponseDto;
import com.tencoding.blog.service.BoardService;

import lombok.experimental.Delegate;

@RestController
public class BoardApiController {

	@Autowired
	private BoardService boardService;

	// @RequestBody mimetype -> application/json 이라서 없애서 
	// Content type 'application/x-www-form-urlencoded;charset=UTF-8' 만들었다
//	@PostMapping("/api/board")
//	public ResponseDto<Integer> save(Board board, 
//			@AuthenticationPrincipal PrincipalDetail detail) {
//
//		// 아작스 통신으로 넘겨받은 데이터 콘솔에 뿌려보기
//		// BoarderService
//		// 저장하기 만들기
//		System.out.println(board);
//		boardService.write(board, detail.getUser());
//
//		return new ResponseDto<Integer>(HttpStatus.OK, 1);
//
//	}

	@DeleteMapping("/api/board/{id}")
	public ResponseDto<Integer> deleteDetailById(@PathVariable int id) {
		boardService.deleteById(id);
		return new ResponseDto<>(HttpStatus.OK, 1);
	}

	@PutMapping("/api/board/{boardId}")
	public ResponseDto<Integer> update(@PathVariable int boardId, 
			@RequestBody Board board) {
		int result = boardService.modifyBoard(boardId, board);
		return new ResponseDto<Integer>(HttpStatus.OK, result);
	}
	
	@PostMapping("/api/board/{boardId}/reply")
	public ResponseDto<Reply> replySave(@PathVariable int boardId , @RequestBody Reply requestReply, 
			@AuthenticationPrincipal PrincipalDetail principalDetail ){
		Reply replyEntity = boardService.writeReply(boardId, requestReply, principalDetail.getUser());
		
		return new ResponseDto<Reply>(HttpStatus.OK, replyEntity);
	}
	
	@DeleteMapping("/api/board/{boardId}/reply/{replyId}")
	public ResponseDto<?> deleteReplyById(@PathVariable String boardId, @PathVariable int replyId,
			@AuthenticationPrincipal PrincipalDetail principalDetail){
//		System.out.println("boardId" + boardId);
//		System.out.println("replyId" + replyId);
		// 서비스 만들고 요청!
		// 검증 (현재 삭제 요청자, db저장된 사용자에 id 역시 비교해서 처리를 해 주어야 한다.)	
		try {
			System.out.println(principalDetail.getUser().getId());
			boardService.deleteReplyById(replyId, principalDetail.getUser().getId());			
		
		} catch (Exception e) {
			// TODO: handle exception
		}


		return new ResponseDto<Integer>(HttpStatus.OK,1);
	}
	
	@PutMapping("/api/board/{boardId}/reply_update/{replyId}")
	public ResponseDto<Integer> updateReply(@PathVariable int boardId, 
			@RequestBody Board board) {
		int result = boardService.modifyBoard(boardId, board);
		return new ResponseDto<Integer>(HttpStatus.OK, result);
	}
	
	// 스토리에 페이지 검색 만들기

}
