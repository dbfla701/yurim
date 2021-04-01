package com.example.demo.security;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.example.demo.DAO.UserDao;
import com.example.demo.model.Account;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class CustomUserDetailsService implements UserDetailsService{

	@Autowired
	private UserDao dao;

	// Database에 접근해서 사용자 정보를 가져오는 역할을 함
	@Override
	public UserDetails loadUserByUsername(String username)
			throws UsernameNotFoundException {
		if(username==null) {
			log.info("null임");
		}else {
			log.info("null 아님");
		}
		
		Account account = dao.selectById(username);
		log.info("CustomUserDetailsService { }", username);
		if(account == null) {
			log.info("account가 null임");
			throw new UsernameNotFoundException("User not authorized.");
		}else {
			log.info("account가 null이 아님");
		}
		
		GrantedAuthority authority = new SimpleGrantedAuthority(account.getRole());
		
		UserDetails userDetails = new User(username, account.getPassword(), Arrays.asList(authority));
		
		return userDetails;
	}
}
