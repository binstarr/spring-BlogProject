package com.demo.server2.controller;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import com.demo.server2.dto.Board;
import com.demo.server2.service.BoardService;

@Controller
public class BoardController {
	
	@Autowired
	private BoardService boardService;
	
	@GetMapping({"", "/", "board/search"})
	public String index(@RequestParam(required = false) String searchValue, Model model,
			@PageableDefault(size = 3, sort = "id", direction = Direction.DESC) Pageable pageable) {
		String searchTitle = searchValue == null ? "" : searchValue;
		Page<Board> boards = boardService.searchBoard(searchTitle.replace("//", ""), pageable);
		
		
		int nowPage = boards.getPageable().getPageNumber() + 1;
		// 밑에 번호가 앞 뒤로 2칸씩 보이게 만든다
		int PAGENATION_BLOCK_COUNT = 3;
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
		model.addAttribute("searchValue", searchTitle);
		
		return "index";
	}
	
	@GetMapping("/board/save_form")
	public String saveForm() {
		return "/board/save_form";
	}
	
	@GetMapping("/board/{id}")
	public String boardDetail(@PathVariable int id, Model model){
		model.addAttribute("board", boardService.boardDetail(id));
		return "/board/detail";
	}
	
	@GetMapping("/board/{id}/update_form")
	public String updateForm(@PathVariable int id, Model model) {
		model.addAttribute("board",boardService.boardDetail(id));
		return "/board/update_form";
	}
	


}
