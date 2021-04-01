package com.example.demo;

import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

// SpringBootServletInitializer 상속 한다는 것은
// Spring Boot 애플리케이션 동작이 가능 하도록 웹 애플리케이션 컨텍스트(IoC 방식으로 Bean을 관리하는 컨테이너) 구성 한다는 의미
public class Initializer extends SpringBootServletInitializer {

	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		return application.sources(Application.class);
	}

}
