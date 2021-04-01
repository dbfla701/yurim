package com.example.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import com.example.demo.DAO.UserDao;
import com.example.demo.model.Account;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class AccountService {
	
	@Autowired
	private UserDao dao;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	// count(*) ADMIN권한의 user확인
	public int count() {
		int count = dao.count();
		log.info("count {}", count);
		return count;
	}
	
	// admin 계정만 가져오는거
	public List<Account> adminId() {
		log.info("admin 계정만 가져오기");
		return dao.adminId();
	}
	
	public Account selectId() {
		log.info("username값이 있는지 없는지");
		return dao.selectId();
	}
	
	// 회원가입
	public boolean signUp(String username, String password) {
		log.info("signUp {}", username, password);
		return dao.signUp(username, password);
	}
	
	// 로그인
	public Account selectById(String username) {
		log.info("selectById {}", username);
		return dao.selectById(username);
	}
	
	public void save(String username, String password) {
		password = passwordEncoder.encode(password);
		dao.signUp(username, password);
	}
	
	public void saveAdmin(String username, String password) {
		password = passwordEncoder.encode(password);
		dao.signUpAdmin(username, password, "ROLE_ADMIN");
	}

}
