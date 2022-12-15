package com.tencoding.blog.api;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tencoding.blog.dto.ResponseDto;
import com.tencoding.blog.dto.User;
import com.tencoding.blog.service.UserService;

@RestController
public class UserApiController {
	
	//DI
	@Autowired
	private UserService userService;
	
	@Autowired
	private HttpSession session;
	

	@PostMapping("/auth/joinProc")
	public ResponseDto<Integer> save(@RequestBody User user) {
		System.out.println("UserApiController에서 호출 됨. user : " + user);
		
		int result = userService.saveUser(user);
		
		return new ResponseDto<Integer>(HttpStatus.OK, result); // 자바 OBject --> JSON 형식으로
	}
	
	@PutMapping("/api/user")
	public ResponseDto<?> update(@RequestBody User user){
		
		// validation 처리.. 안되어있으면 예외 잡아서 사용한테 떨궈주면 됨 !!
		userService.saveUser(user);
		
		return new ResponseDto<Integer>(HttpStatus.OK, 1);
	}
	
//	@PostMapping("/user/login")
//	public ResponseDto<Integer> login(@RequestBody User user) {
//		System.out.println("UserApiController에서 login 호출 됨: " + user);
//		User principal = userService.login(user);
//		if(principal != null) {
//			session.setAttribute("principal", principal); // key value 저장!!!!
//		}
//		
//		return new ResponseDto<Integer>(HttpStatus.OK, 1); // 자바 OBject --> JSON 형식으로
//	}
	
}
