package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.demo.configuration.security.CustomUserDetails;
import com.example.demo.service.AccountService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequiredArgsConstructor
public class MainController {

	@Autowired
	private AccountService service;
	
	@GetMapping("/login_After")
	public String init() {
		CustomUserDetails domain =
				(CustomUserDetails) SecurityContextHolder // SecurityContextHolder = Authentication을 보관, Bean 에서 사용자 정보 얻기
				// Authentication(인터페이스)은 SecurityContext에 저장되며
				// SecurityContextHolder 를 통해 SecurityContext에 접근하고,
				// SecurityContext 를 통해 Authentication 에 접근
				.getContext()
				.getAuthentication()
				.getPrincipal();

//		log.info("{}", domain);
		return "login_After";
	}

	@GetMapping("/login")
	public String loginPage() {
		// 여기서 count가 0이면 계정생성 페이지로
		int n = service.count();
		if (n == 0) {
			// int n으로 dao.count값 받아서 if n == 0 이면 -> 회원가입페이지로 넘어가게
			return "redirect:/joinUser";
		} else {
			// n !==0 이라는거니까 login되는 페이지로 넘어가게 하기
			return "login";
		}
	}

	@GetMapping("/joinUser")
	public String joinUser() {
		return "joinUser";
	}

	@PostMapping("/joinUser")
	public String joinUser(String id, String pw, String pwConf) {
		if (!pw.equals(pwConf)) {
			return "joinUser";
		}

		service.save(id, pw);
		return "redirect:/login";
	}
}
