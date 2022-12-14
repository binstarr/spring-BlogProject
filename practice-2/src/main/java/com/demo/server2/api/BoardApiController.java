package com.demo.server2.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.demo.server2.auth.PrincipalDetail;
import com.demo.server2.dto.Board;
import com.demo.server2.dto.Reply;
import com.demo.server2.dto.ResponseDto;
import com.demo.server2.service.BoardService;

@RestController
public class BoardApiController {
	
	@Autowired
	private BoardService boardService;
	
	@PostMapping("/api/board")
	public ResponseDto<?> save(@RequestBody Board board,
			@AuthenticationPrincipal PrincipalDetail principalDetail){
		boardService.write(board, principalDetail.getUser());
		return new ResponseDto<Integer>(HttpStatus.OK, 1);
	}
	
	@DeleteMapping("/api/board/{id}")
	public ResponseDto<Integer> delete(@PathVariable int id){
		boardService.deleteById(id);
		return new ResponseDto<Integer>(HttpStatus.OK, 1);
	}
	
	@PutMapping("/api/board/{id}")
	public ResponseDto<Integer> update(@PathVariable int id, @RequestBody Board board){
		boardService.updateBoard(id, board);
		return new ResponseDto<Integer>(HttpStatus.OK, 1);
	}
	
	@PostMapping("/api/board/{boardId}/reply")
	public ResponseDto<Integer> saveReply(@PathVariable int boardId, @RequestBody Reply  reqReply, @AuthenticationPrincipal PrincipalDetail principalDetail){
		boardService.writeReply(boardId, reqReply, principalDetail.getUser());
		return new ResponseDto<Integer>(HttpStatus.OK,1);
	}
	
	@DeleteMapping("/api/board/{boardId}/reply/{replyId}")
	public ResponseDto<?> deleteReply(@PathVariable int boardId, @PathVariable int replyId,
									@AuthenticationPrincipal PrincipalDetail principalDetail){
		try {
			boardService.deleteReply(replyId, principalDetail.getUser().getId());
		} catch (Exception e) {
			// TODO: handle exception
		}
		return new ResponseDto<>(HttpStatus.OK,1);
	}

}
