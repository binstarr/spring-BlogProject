package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@Controller
public class ApiController {

	@GetMapping({"","/"})
	public String get() {
		return "index";
	}
	
	@GetMapping("/qqq")
	public String joinForm() {
		return "user/join_form1";
	}
	
}
