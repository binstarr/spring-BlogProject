package com.demo.server2.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class UserController {
	
	@Autowired
	private HttpSession session;

	@GetMapping("/")
	public String index() {
		return "index";
	}
	
	@GetMapping("/auth/login")
	public String login() {
		return "/user/login_form";
	}
	
	@GetMapping("/auth/join")
	public String join() {
		return "user/join_form";
	}
	
	@GetMapping("/logout")
	public String logout() {
		HttpSession httpSession = session;
		httpSession.invalidate();
		return "rediect:/";
	}
	
	@GetMapping("/user/update_form")
	public String update() {
		return "user/update_form";
	}
}
