package com.demo.client1.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.demo.client1.service.RestTemplateService;

@RestController
@RequestMapping("/api/client")
public class ApiController {
	
	
	private final RestTemplateService service;

	// autowired 랑 같다.
	public ApiController(RestTemplateService service) {
		this.service = service; 
	}
	
	@GetMapping("/get-hello")
	public String getHello() {
		
		return service.반가워().toString();
	}
	
}
