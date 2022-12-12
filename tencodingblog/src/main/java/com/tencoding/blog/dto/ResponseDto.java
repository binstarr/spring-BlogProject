package com.tencoding.blog.dto;

import org.springframework.http.HttpStatus;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

// Generic을 사용하는 이유
// 같은 변수 이름으로 데이터 타입을 다르게 사용해야 할 때 사용해야 한다
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ResponseDto<T> {

	HttpStatus status;//
	// 보낼 데이터의 타입을 지정해야 한다.
	T body;
}
