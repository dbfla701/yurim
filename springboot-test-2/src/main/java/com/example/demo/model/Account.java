package com.example.demo.model;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@NoArgsConstructor
@AllArgsConstructor
public class Account{

	@EqualsAndHashCode.Include
	private String username;
	
	@EqualsAndHashCode.Include
	private String password;
	@EqualsAndHashCode.Include
	private String role;
	
	

}
