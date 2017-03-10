package com.csci3130.daloffline.domain;

import javax.persistence.*;

@Entity
public class UserPasswordPair {

	//@Id
	//@GeneratedValue
	//long id;
	
	@Id
	String username;

	String password;
	
	public UserPasswordPair(){}
	
	public UserPasswordPair(String username, String password) {
		this.username = username;
		this.password = password;
	}

	public String getUsername() {
		return username;
	}

	public String getPassword() {
		return password;
	}
	
}

