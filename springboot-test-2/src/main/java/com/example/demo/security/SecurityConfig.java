package com.example.demo.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.access.ExceptionTranslationFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import com.example.demo.Filter.AccessIpFilter;
import com.example.demo.Handler.FailHandler;
import com.example.demo.Handler.MyLoginSuccessHandler;

@Configuration
@EnableWebSecurity
// 이걸 명시함으로써 springSecurityFilterChain가 자동으로 포함되어짐
public class SecurityConfig extends WebSecurityConfigurerAdapter {
	
	@Autowired
	private CustomUserDetailsService userDetailsService;

	@Override
	public void configure(WebSecurity web) throws Exception {
		super.configure(web);
	}
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
	}
	
	@Autowired
	private MyLoginSuccessHandler successHandler;
	
	@Autowired
	private FailHandler failHandler;
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http
		.authorizeRequests()
		.mvcMatchers("/","/login", "/signUp").permitAll()
		.anyRequest().authenticated();
		
		http.addFilterBefore(new AccessIpFilter(), ExceptionTranslationFilter.class);
		
		http.formLogin()
		.loginPage("/login")
//		.usernameParameter("username")
//		.passwordParameter("password")
		.loginProcessingUrl("/login_proc")
		.defaultSuccessUrl("/loginAfter")
		.successHandler(successHandler)
		.failureHandler(failHandler)
//		.successForwardUrl("/loginAfter")
		.failureUrl("/signUp");
		
		http.logout()
		.logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
		.logoutSuccessUrl("/")
		.invalidateHttpSession(true);
	}
	
	@Bean
	public PasswordEncoder passwordEncoder() {
		return PasswordEncoderFactories.createDelegatingPasswordEncoder();
	}
}
