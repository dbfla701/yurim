package com.example.demo.configuration.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import com.example.demo.model.Account;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class CustomAuthenticationProvider implements AuthenticationProvider{
	
	// AuthenticationProvider : 화면에서 입력한 로그인 정보와 DB에서 가져온 사용자의 정보를 비교해주는 인터페이스
	@Autowired
	private CustomUserDetailsService userService;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Autowired
	private Account account;
	
	@Autowired
	private Sha_256 sha256;
	
	// 화면에서 사용자가 입력한 로그인 정보를 담고 있다.
	@Override
	public Authentication authenticate(Authentication authentication) throws AuthenticationException {
		// Authentication = 인증
		// 인증과 인가를 위해 Principal 을 아이디로, Credential 을 비밀번호로 사용하는 Credential 기반의 인증 방식 을 사용
		String username = (String) authentication.getPrincipal();
		String password = (String) authentication.getCredentials();
		
		String rowUsername = authentication.getName();
		
		// loadUserByUsername() = DB에서 사용자 정보를 가져옴
		CustomUserDetails user = (CustomUserDetails) userService.loadUserByUsername(rowUsername);
		System.out.println("db 사용자 비번은 {}"+ user.getPassword());
		
		String rawPassword = account.getId() + (String) authentication.getCredentials(); // append한거
		String encryptSHA256;
		encryptSHA256 = sha256.encrypt(rawPassword);
		log.info("sha256 암호화 한거",encryptSHA256);
// Authentication에서 가져온 정보
// password encoder에서 sha256을 확장을 해서 가져오는 걸로
String savePassword = user.getPassword(); // db에서 가져온 정보

// Authentication 객체에서 화면에서 가져온 로그인 정보와 비교하면 된다.
if (!passwordEncoder.matches(encryptSHA256, savePassword)) {
		System.out.println("비번이 안맞으면 BadCredentialsException");
		throw new BadCredentialsException("BadCredentialsException");
}
		
		// AuthenticaionFilter에서 생성된 토큰으로부터 아이디와 비밀번호를 조회함
		// Spring Security는 credential기반의 인증을 취함 -> principal= id, credential=비번
		// Principal(접근 주체): 보호받는 Resource에 접근하는 대상, Credential(비밀번호): Resource에 접근하는 대상의 비밀번호
		return new UsernamePasswordAuthenticationToken(username, password, user.getAuthorities()); // username, password를 조합해서 token인스턴스를 만듦
	}

	// Authentication객체를 이 AuthenticationProvider가 인증가능한 클래스인지 확인
	@Override
	public boolean supports(Class<?> authentication) {
		return UsernamePasswordAuthenticationToken.class.equals(authentication);
	}
}
