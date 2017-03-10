package com.csci3130.daloffline.domain;

import java.util.Set;
import javax.persistence.*;

@Entity
public class Student extends UserClass {
	@Id
	@GeneratedValue
	long id;
	
	//String lastname;
	//String firstname;
	
	//String username;
	//String password;
	
	@ManyToMany
	Set<Course> courses;
	
	@ManyToOne
	Faculty faculty;

	@OneToOne
	UserPasswordPair userPasswordPair;
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getLastname() {
		return lastname;
	}
	public void setLastname(String lastname) {
		this.lastname = lastname;
	}
	public String getFirstname() {
		return firstname;
	}
	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}
	public void setUserPasswordPair(UserPasswordPair upp) {
		this.userPasswordPair = upp;
	}
	
	
}
