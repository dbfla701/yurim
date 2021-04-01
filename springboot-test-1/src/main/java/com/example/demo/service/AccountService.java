package com.example.demo.service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.demo.dao.UserDao;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class AccountService {

	@Autowired
	private UserDao mapper;

	@Autowired
	private PasswordEncoder passwordEncoder;
	
	public int count() {
//		log.info("게시물 총 갯수 : count");
		return mapper.count();
	}

	public void save(String id, String pw) {
		pw = passwordEncoder.encode(pw);
		mapper.save(id, pw);
	}
}
