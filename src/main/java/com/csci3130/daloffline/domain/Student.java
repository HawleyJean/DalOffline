package com.csci3130.daloffline.domain;

import com.csci3130.daloffline.domain.User;
import java.io.Serializable;
import javax.persistence.*;
import java.util.Set;

/**
 * Entity implementation class for Entity: Student
 *
 */
@Entity

public class Student extends User implements Serializable {

	
	private static final long serialVersionUID = 1L;
	
	Set<Course> courses;

	public Student() {
		super();
	}
	
	public Student(UsernamePasswordPair usernamePasswordPair)
	{
		super(usernamePasswordPair);
	}
	
	public Student(String firstName, String lastName)
	{
		super(firstName, lastName);
	}
	
	public Student(String firstName, String lastName, UsernamePasswordPair usernamePasswordPair)
	{
		super(firstName, lastName, usernamePasswordPair);
	}
   
}
