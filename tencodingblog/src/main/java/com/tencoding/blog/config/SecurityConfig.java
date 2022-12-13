package com.tencoding.blog.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration // IoC 관리
@EnableWebSecurity // 시큐리티 필터로 등록이 된다. (필터 커스텀)
@EnableGlobalMethodSecurity(prePostEnabled = true) // 특정 주소로 접근하면 권한 및 인증 처리를 미리 체크 하겠다.
public class SecurityConfig extends WebSecurityConfigurerAdapter{
	
	// /auth/login_form, auth/join_form  --> /auth/**
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		
		http.csrf().disable(); // 개발 완료전 테스트 시 사용(실 서비스시 사용 안함 권장)
		
		http
			.authorizeHttpRequests() // 모든 요청에
			.antMatchers("/auth/**", "/", "/js/**", "/image/**", "/css/**") // 이 주소로 오는 모든걸 허용하겠다
			.permitAll() // 모든 권한들
			.anyRequest() // 어떠한 요청들
			.authenticated()
		.and() // 아닌 주소는
			.formLogin()
			.loginPage("/auth/login_form");
		
	}

}
