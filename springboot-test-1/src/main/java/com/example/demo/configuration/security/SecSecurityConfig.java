package com.example.demo.configuration.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import com.example.demo.Handle.FailHandler;
import com.example.demo.Handle.MyLoginSuccessHandler;

@Configuration
@EnableWebSecurity
//@ComponentScan
public class SecSecurityConfig extends WebSecurityConfigurerAdapter{
	
	@Autowired
	private CustomUserDetailsService userDetailsService;
	
	@Autowired
	private MyLoginSuccessHandler successHandler;
	
	@Autowired
	private FailHandler failHandler;
	
	@Autowired
	private CustomAuthenticationProvider provider;
	
	@Override
	public void configure(WebSecurity web) throws Exception {
	}
	
	// AuthenticationManagerBuilder = AuthenticationProviders를 쉽게 추가 할 수있게하여 인증 메커니즘을 설정
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.authenticationProvider(provider)
		.userDetailsService(userDetailsService)
		.passwordEncoder(passwordEncoder());
	}
	
	// URL에 대한 권한 설정
	// /* URL에 대해 ROLE_USER 권한을 갖는 사용자만 접근가능하도록 하라
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		// 인가
		http.mvcMatcher("/**")
		.authorizeRequests() // HttpServletRequest 요청 URL에 따라 접근 권한을 설정
		.mvcMatchers("/login", "/joinUser").permitAll()
//		.mvcMatchers("/").hasRole("USER")
		.anyRequest().authenticated() // permit을 준 요청 이외 어떤 요청이든 인증이 필요
		;
		
		http.formLogin() // 기본적인 로그인 form을 사용하겠다
		.loginPage("/login")
		.loginProcessingUrl("/login_proc") // login성공하는 과정 url, 이것도 form에서 action값 설정해준거
//		.defaultSuccessUrl("/login_After")
		.successHandler(successHandler)
		.failureHandler(failHandler)
		;
//		.failureUrl("/login?error=true");
		
		http.logout()
		.logoutRequestMatcher(new AntPathRequestMatcher("/logout")) // logout성공하는 과정 url
		.logoutSuccessUrl("/")
		.invalidateHttpSession(true); // 로그아웃시 인증정보를 지우고 세션을 무효화 시키는 설정
	}
	
	@Bean
	public PasswordEncoder passwordEncoder() {
		return PasswordEncoderFactories.createDelegatingPasswordEncoder();
	}
}
