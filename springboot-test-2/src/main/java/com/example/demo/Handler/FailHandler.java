package com.example.demo.Handler;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;

import lombok.extern.slf4j.Slf4j;

@Configuration
@Slf4j
public class FailHandler implements AuthenticationFailureHandler{
	

	@Override
	public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response,
			AuthenticationException exception) throws IOException, ServletException {
		
//		String ip = request.getParameter("ip");
		response.sendRedirect("signUp");
		log.info("실패");
	}

}
