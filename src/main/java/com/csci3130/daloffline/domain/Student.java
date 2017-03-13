package com.csci3130.daloffline.domain;

import com.csci3130.daloffline.domain.User;
import java.io.Serializable;
import javax.persistence.*;

import java.util.HashSet;
import java.util.Set;

/**
 * Entity implementation class for Entity: Student
 *
 */
//@Entity
//@Inheritance
public class Student extends User implements Serializable {

	
	private static final long serialVersionUID = 1L;
	
	//@Id
	//@GeneratedValue
	private long Id;
	
	// hashsets dont sort
	//@ElementCollection
	//@Transient
	Set<Course> courses;	
	
	public Student() {
		super();
		courses = new HashSet<Course>();
	}
	
	public Student(UsernamePasswordPair usernamePasswordPair)
	{
		super(usernamePasswordPair);
		courses = new HashSet<Course>();
	}
	
	public Student(String firstName, String lastName)
	{
		super(firstName, lastName);
		courses = new HashSet<Course>();
	}
	
	public Student(String firstName, String lastName, UsernamePasswordPair usernamePasswordPair)
	{
		super(firstName, lastName, usernamePasswordPair);
		courses = new HashSet<Course>();
	}

	public Set<Course> getCourses() {
		return courses;
	}

	public void setCourses(Set<Course> courses) {
		this.courses = courses;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public long getId() {
		return Id;
	}
	
	
   
}
