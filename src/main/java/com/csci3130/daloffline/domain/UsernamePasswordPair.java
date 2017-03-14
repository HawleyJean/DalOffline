package com.csci3130.daloffline.domain;

import java.io.Serializable;
import javax.persistence.*;

/**
 * Entity implementation class for Entity: UserPasswordPair
 *
 */
@Entity

public class UsernamePasswordPair implements Serializable {

	
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue
	private long id;
	
	@Column(unique=true)
	private String username;
	private String password;
	
	public UsernamePasswordPair() {
		super();
	}
	
	public UsernamePasswordPair(String username, String password) {
		
		this.username = username;
		this.password = password;
		
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	
   

}
