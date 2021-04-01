package com.example.demo.configuration.security;

import java.util.Arrays;

import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;

import com.example.demo.model.Account;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString(callSuper = true)

// dto같은거
public class CustomUserDetails extends User {

	private static final long serialVersionUID = -4060025389549004968L;
	private final Account account;

	public CustomUserDetails(Account account) {
		super(account.getId(), account.getPw(), Arrays.asList(new SimpleGrantedAuthority("ROLE_USER")));
		this.account = account;
	}

}
