package com.demo.client1.service;

import java.net.URI;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import com.demo.client1.dto.UserResponse;

@Service
public class RestTemplateService {

	// 여기서 hello 라는 메서드를 만들어서 다른 서버에 접근해서 결과를 받아 오기.
	public UserResponse 반가워() {
		
		URI uri = UriComponentsBuilder
				.fromUriString("http://localhost:8090")
				.path("/api/server/hello/{userId}/name/{username}")
//				.queryParam("name", "홍길동")
//				.queryParam("age", "100")
				.encode() // %3B
				.build()
				.expand(100, "Mike")
				.toUri();
		System.out.println("주소 확인 " + uri.toString());
		
		RestTemplate restTemplate = new RestTemplate();
		//String result = restTemplate.getForObject(uri, String.class);
		ResponseEntity<UserResponse> result = restTemplate.getForEntity(uri, UserResponse.class);
		System.out.println(result.getStatusCodeValue());
		System.out.println(result.getBody());
		
		
		
		return result.getBody();
	}
	
}
