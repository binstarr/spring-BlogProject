package com.tencoding.blog.controller;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tencoding.blog.controller.repository.UserRepository;
import com.tencoding.blog.dto.User;
import com.tencoding.blog.model.RoleType;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/dummy")
public class DummyControllerTest {
	
	@Autowired // 순환 참조 조심 !!! 
	private UserRepository userRepository;
	
	// select all
	@GetMapping("/user")
	public List<User> list(){
		return userRepository.findAll();
	}
	
	// select all 인데 페이지까지 처리하는 방법
	@GetMapping("/user2")
	public Page<User> pageList(@PageableDefault(size = 5, sort = "id", direction = Direction.DESC) Pageable pageable ){
		
		Page<User> userPage = userRepository.findAll(pageable);
		
		List<User> users = userRepository.findAll(pageable).getContent();
		return userPage;
	}
	
	@GetMapping("/user/{id}")
	public User detail(@PathVariable int id) {
		
//		User user = userRepository.findById(id).orElseGet(() -> {
//			//커스텀 한 데이터
//			return new User();
//		});
		
		// 예외 처리
		User user = userRepository.findById(id).orElseThrow(() -> {
			
			return new IllegalArgumentException("해당 유저는 없습니다 : " + id);
		});
		
		return user;
	}
	
	// http://localhost:9090/blog/dummy/signup
	@PostMapping("/signup")
	public String signUp(@RequestBody User user) {
	
		log.info(">>> User : {}", user);
		
		user.setRole(RoleType.USER);
		userRepository.save(user);
		
		return "회원가입이 완료 되었습니다.";
	}
	
	// 회원 수정
	@Transactional // 영속화할 때 걸어준다. & 트랜잭션 기본 의미 : 함수 종료시에 더티 체킹을 하고 수정된 데이터가 있다면 commit이 된다.
	@PutMapping("/user/{id}")
	public User updateUser(@PathVariable int id, @RequestBody User reqUser) {
		
		log.info(">>>> id : {}, >>>>pw : {}, >>>>email : {}", id, reqUser.getPassword(), reqUser.getEmail());
		
		// 사용자 여부를 먼저 확인 (select)
		// 사용자 있다면 넘겨 받은 데이터를 가공해서  -> 저장
		// 사용자가 없다면 클라이언트에게 잘못된 요청, 없는 사용자 입니다.
		
		User user = userRepository.findById(id).orElseThrow(() -> {
			return new IllegalArgumentException("수정에 실패 하였습니다.");
		});
		user.setPassword(reqUser.getPassword());
		user.setEmail(reqUser.getEmail());
//		userRepository.save(user);
		
		return null;
	}
	
	
	

}
