package com.csci3130.daloffline.domain;

import java.util.Set;
import javax.persistence.*;

@Entity
public class Student /*implements UserClass*/ {
	@Id
	@GeneratedValue
	long id;
	
	String lastname;
	String firstname;
	
	@ManyToMany
	Set<Course> courses;
	
	@ManyToOne
	Faculty faculty;
	
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
	
	
}
