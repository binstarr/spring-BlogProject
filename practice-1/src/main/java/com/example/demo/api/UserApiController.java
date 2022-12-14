package com.example.demo.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dto.ResponseDto;
import com.example.demo.dto.User;
import com.example.demo.service.UserService;

@RestController
public class UserApiController {
	
	@Autowired
	private UserService userService;

	@PostMapping("api/user")
	public ResponseDto<?> saveInfo(@RequestBody User user){
		int result = userService.saveUser(user);
		return new ResponseDto<Integer>(HttpStatus.OK, result);
	}
}
