package com.tencoding.blog.controller;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import com.tencoding.blog.auth.PrincipalDetail;
import com.tencoding.blog.dto.Board;
import com.tencoding.blog.dto.Reply;
import com.tencoding.blog.dto.ResponseDto;
import com.tencoding.blog.service.BoardService;

@Controller
public class BoardController {

	/*
	 * 로그인이 인증되면 컨트롤러에서 어떻게 세션을 찾을까요 ?
	 */

	@Autowired
	private BoardService boardService;

	// ?page =2
	@GetMapping({ "", "/", "/board/search" })
	public String index(@RequestParam(required = false) String q, Model model,
			@PageableDefault(size = 3, sort = "id", direction = Direction.DESC) Pageable pageable) {

		// 검색 요청 값을 받아서 처리
		String searchTitle = q == null ? "" : q;
		System.out.println("searchTitle : " + searchTitle);
		Page<Board> boards = boardService.searchBoard(searchTitle.replace("//", "") ,pageable);
		
//		Page<Board> boards = boardService.getBoardList(pageable);
		
		
		int PAGENATION_BLOCK_COUNT = 3;
		// jsp 파일에서 model 추상객체를 이용해서 컨트롤러에서 내려 준 데이터에 접근이 가능하다.
		
		// 1. 현재 페이지 앞 뒤로 2칸씩 보이기
		// 2. 현재 페이지 active 처리 하기
		// 3. 페이지 숫자를 눌렀을 경우 해당 페이지 화면 이동하기
		// 마지막에 보여야 하는 페이지를 잘 확인하자!
		
		// 총 게시물에서 화면에 보여줄 게시물을 계산을 하면 총 몇 페이지가 나오는지 알 수 있다. 
		System.out.println(">>>>>화면에 보여줄 게시글의 갯수이다." + boards.getSize());
		System.out.println(">>>>>전체 페이지 크기" + boards.getTotalPages());
		System.out.println(">>>>>현재 페이지 번호" + boards.getPageable().getPageNumber());
		
		int nowPage = boards.getPageable().getPageNumber() + 1;
		// 밑에 번호가 앞 뒤로 2칸씩 보이게 만든다
		int startPageNumber = Math.max(nowPage - PAGENATION_BLOCK_COUNT, 1);  //nowPage - 2;
		int endPageNumber = Math.min(nowPage + PAGENATION_BLOCK_COUNT, boards.getTotalPages()); //nowPage + 2;
		System.out.println("시작해야 하는 번호 : " + startPageNumber);
		System.out.println("마지막에 보여야 하는 번호 : " + endPageNumber);
		// 블럭 숫자에는 - 값을 보여 주면 안된다.
		
		// 자료구조를 만들어서 반복문을 돌린다.
		ArrayList<Integer> pageNumbers = new ArrayList<>();
		for(int i = startPageNumber; i<= endPageNumber; i++) {
			pageNumbers.add(i);
		}
		
		model.addAttribute("boards", boards);
		model.addAttribute("nowPage", nowPage);
		model.addAttribute("startPage", startPageNumber);
		model.addAttribute("endPage", endPageNumber);
		model.addAttribute("pageNumbers", pageNumbers);
		model.addAttribute("q", searchTitle);


		return "index";
	}

	@GetMapping("/board/save_form")
	public String saveForm() {
		return "/board/save_form";
	}
	

	@GetMapping("/board/{id}")
	public String showDetial(@PathVariable int id, Model model) {
		model.addAttribute("board", boardService.boardDetail(id));
		return "/board/detail";
	}

	@GetMapping("/board/{id}/update_form")
	public String updateForm(@PathVariable(name = "id") int boardId, Model model) {
		model.addAttribute("board", boardService.boardDetail(boardId));
		return "/board/update_form";
	}
	
	@GetMapping("/board/{id}/reply_update_form")
	public String updateReply(@PathVariable int id, Model model) {
		model.addAttribute("board", boardService.boardDetail(id));
		return "/board/reply_update_form";
	}
	
	// form 태그 submit 버튼으로 글 작성
	@PostMapping("/api/board")
	public String save(Board board, 
			@AuthenticationPrincipal PrincipalDetail detail) {

		System.out.println(board);
		boardService.write(board, detail.getUser());

		return "redirect:/";

	}



}
