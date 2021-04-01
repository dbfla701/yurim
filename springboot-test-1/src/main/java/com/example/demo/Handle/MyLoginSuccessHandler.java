package com.example.demo.Handle;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import lombok.extern.slf4j.Slf4j;

@Configuration
@Slf4j
public class MyLoginSuccessHandler implements AuthenticationSuccessHandler{

	@Override
	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
			Authentication authentication) throws IOException, ServletException {
		
//		HttpSession session = request.getSession();
//		session.setAttribute("id", authentication.getName());
		
		response.sendRedirect("login_After");
		System.out.println("로그인 성공");
	}

}
