package com.tencoding.blog.controller;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.tencoding.blog.auth.PrincipalDetail;
import com.tencoding.blog.dto.Image;
import com.tencoding.blog.dto.RequestFileDto;
import com.tencoding.blog.service.StoryService;

// Ioc해주고 DI 해준다
@Controller
@RequestMapping("/story") // 로그인 해야 볼 수 있음 , 모두가 보려면 앞에 auth가 필요함
public class StoryController {
	
	@Autowired
	private StoryService storyService;

	 //프로토콜://도메인주소:포트번호/주소
	// http://localhost:9090/story/home
	@GetMapping("/home")
	public String storyHome(Model model, @PageableDefault(size = 4, sort = "id", direction = Direction.DESC) Pageable pageable) {
		
		Page<Image> imagePage = storyService.getImageList(pageable);
		
		model.addAttribute("imagePage", imagePage);
		
		return "story/home";
	}
	
	@GetMapping("/upload")
	public String storyUpload() {
		return "story/upload";
	}
	
	// 여기서는 데이터를 전달 받고 처리 해야 한다.
	// MultipartFile 다루는 1단계
	@PostMapping("/image/upload")
	public String storyImageUpload(RequestFileDto fileDto, @AuthenticationPrincipal PrincipalDetail principalDetail) {
		storyService.imageUpload(fileDto, principalDetail);
		return "redirect:/";
	}
}
