package com.tencoding.blog.service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tencoding.blog.dto.User;
import com.tencoding.blog.model.RoleType;
import com.tencoding.blog.repository.UserRepository;

// 스프링이 컴포넌트 스캔을 통해서 Bean으로 등록해 준다 (IoC)
@Service
public class UserService {

	/*
	 * 서비스를 만드는 이유 트랜잭션 관리를 하기 위해서
	 */

	@Autowired
	private UserRepository userRepository;

	// IOC관리하는 곳에서 Bean을 만들고  필요한 곳에서 autowired로 선언한다. <-- 스프링 사용법!!
	@Autowired
	private BCryptPasswordEncoder encoder;
	
	// 작업의 단위!
	// 하나의 기능 + 하나의 기능 둘을 묶어서 단위의 기능을 처리
	// DB 수정시 롤백 처리도 가능
	@Transactional
	public int saveUser(User user) {
		try {
			// 비밀번호를 넣을 때 여기서 암호화 처리 하고 DB 저장하기!!
			String rawPassword = user.getPassword();
			String encPassword = encoder.encode(rawPassword);
			System.out.println("encPassword : " + encPassword);
			user.setRole(RoleType.USER);
			user.setPassword(encPassword);
			userRepository.save(user);
			return 1;
		} catch (Exception e) {
			System.out.println("saveUSer 에러 발생");
			e.printStackTrace();
		}

		return -1;
	}
	
	@org.springframework.transaction.annotation.Transactional
	public void updateUser(User reqUser) {
		
		User userEntity = userRepository.findById(reqUser.getId())
				.orElseThrow(() -> {
					return new IllegalArgumentException("해당 유저를 찾을 수 없습니다.");
				}); 
		String rawPassword = reqUser.getPassword();
		String encPassword = encoder.encode(rawPassword); 
		
		userEntity.setUsername(reqUser.getUsername());
		userEntity.setPassword(encPassword);
		userEntity.setEmail(reqUser.getEmail());
		// 더티체킹 해서 업데이트 시킬 예정
//		userRepository.save(userEntity); 
		// 원래는 해야하지만 DB와 DAO 사이에 영속성 컨텍스트가 있어서 여기에 user라는 녀석을 select해서 영속성 컨텍스트에 user라는 정보가 들어가 있다.
		// dao에서 user에 대한 정보를 수정을 일으키면 더티체킹을 해서 commit 날려준다.
		// transactional 두개의 차이가 뭘까??
		// 더티체킹을 하면 save를 안해도 되는데 더티체킹이 어떻게 시작이 될까 ?
	}
	
	@Transactional
	public User searchUserName(String username) {
		
		return userRepository.findByUsername(username).orElseGet(() -> {
			return new User();
		});
	}
	
	

//	public User login(User user) {
//		// 기본 Repository에 필요한 함수가 없을 경우 직접 생성하면 된다.
//		// userRepository.get
////		User userEntity = userRepository.findByUsernameAndPassword(user.getUsername(), user.getPassword());
//		User userEntity = userRepository.login(user.getUsername(), user.getPassword());
//		return userEntity;
//	}

}
