package com.csci3130.daloffline.domain;

import java.io.Serializable;
import javax.persistence.*;

/**
 * Entity implementation class for Entity: User
 *
 */
//@Entity
public class User implements Serializable {

	
	private static final long serialVersionUID = 1L;
	
	//@Id
	//@GeneratedValue
	private long id;

	String username;
	
	//@OneToOne
	//@JoinColumn(name="username_pw_id", referencedColumnName="username")
	@Transient
	private UsernamePasswordPair usernamePasswordPair;
	
	private String firstName;
	private String lastName;

	public User() {
		super();
	}
	
	public User(UsernamePasswordPair usernamePasswordPair) {
		this.usernamePasswordPair = usernamePasswordPair;
	}
	
	public User(String firstName, String lastName)
	{
		this.firstName = firstName;
		this.lastName = lastName;
		
	}
	
	public User(String firstName, String lastName, UsernamePasswordPair usernamePasswordPair)
	{
		this.firstName = firstName;
		this.lastName = lastName;
		this.usernamePasswordPair = usernamePasswordPair;
	}

	public UsernamePasswordPair getUsernamePasswordPair() {
		return usernamePasswordPair;
	}

	public void setUsernamePasswordPair(UsernamePasswordPair usernamePasswordPair) {
		this.usernamePasswordPair = usernamePasswordPair;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public long getId() {
		return id;
	}

}
