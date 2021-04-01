package com.example.demo.security;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import com.example.demo.model.Account;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Getter
@Setter
@ToString
public class CustomUserDetails extends User {

	private static final long serialVersionUID = 1L;
	private final Account account;
	
	@SuppressWarnings("unused")
	private static Collection<? extends GrantedAuthority> authorities(Account account){
		List<GrantedAuthority> authorities = new ArrayList<>();
		if(account.getRole()=="ROLE_ADMIN") {
			authorities.add(new SimpleGrantedAuthority("ROLE_ADMIN"));
		}else {
			authorities.add(new SimpleGrantedAuthority("ROLE_USER"));
		}
		return authorities;
	}

	public CustomUserDetails(Account account) {
		super(account.getUsername(), account.getPassword(),
				Arrays.asList(new SimpleGrantedAuthority(account.getRole())));
		this.account = account;
		log.info("CustomUserDetails 클래스");
	}
	}
