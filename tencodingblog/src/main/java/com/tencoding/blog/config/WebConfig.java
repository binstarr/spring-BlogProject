package com.tencoding.blog.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.resource.PathResourceResolver;

@Configuration
public class WebConfig implements WebMvcConfigurer {

	@Value("${file.path}") 
	private String uploadFolder;
	
	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		WebMvcConfigurer.super.addResourceHandlers(registry);
		// --> /upload/** 이런 경로로 들어오면 낚아 채서 간다. 
		registry.addResourceHandler("/upload/**")
		// 실제 파일 서버 물리적인 경로
		.addResourceLocations("file:///" + uploadFolder)
		// 캐시 지속 시간 설정 (초 단위)
		.setCachePeriod(60 * 10 * 6)
		// 리소스 찾는 것을 최적화 하기 위해 설정
		.resourceChain(true)
		.addResolver(new PathResourceResolver());
	}

	
}