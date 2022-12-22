package com.demo.server2.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.demo.server2.dto.Board;
import com.demo.server2.service.BoardService;

@Controller
public class BoardController {
	
	@Autowired
	private BoardService boardService;
	
	@GetMapping({"", "/"})
	public String index(Model model,
			@PageableDefault(size = 3, sort = "id", direction = Direction.DESC) Pageable pageable) {	
		Page<Board> boards = boardService.getBoardList(pageable);
		model.addAttribute("boards", boards);
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
