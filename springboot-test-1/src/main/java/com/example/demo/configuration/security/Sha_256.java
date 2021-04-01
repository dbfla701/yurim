package com.example.demo.configuration.security;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class Sha_256 implements PasswordEncoder{

	@Override
	public String encode(CharSequence rawPassword) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean matches(CharSequence rawPassword, String encodedPassword) {
		// TODO Auto-generated method stub
		return false;
	}
	
	public String encrypt(String pw) {
		return pw;
		
	}


}
