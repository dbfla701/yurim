package com.example.demo.configuration.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.example.demo.dao.UserDao;
import com.example.demo.model.Account;

// DB에서 유저 정보를 가져오는 역할을 한다.
@Service
public class CustomUserDetailsService implements UserDetailsService {

	@Autowired
	private UserDao mapper;

	// DB에서 유저정보를 불러오는 중요한 메소드
	@Override
	public UserDetails loadUserByUsername(String username)
			throws UsernameNotFoundException {

		Account account = mapper.selectById(username);
		
		if (account == null) {
			throw new UsernameNotFoundException(username);
		}
		CustomUserDetails user = new CustomUserDetails(account);
		return user;
	}
}
