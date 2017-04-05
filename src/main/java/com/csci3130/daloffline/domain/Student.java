package com.csci3130.daloffline.domain;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
@Entity(name = "STUDENTS")
public class Student extends User implements Serializable{
private static final long serialVersionUID = 1L;
	

	@OneToMany
	private List<Course> CompletedCourses;
	
	public Student(){
		super();
	}
	public Student(String username, String pass, String name, String major){
		super(username, pass, name, major);
		CompletedCourses = new ArrayList<Course>();
	}
	public void addCompletedCourse(Course course){
		CompletedCourses.add(course);
	}
	public List<Course> getCompletedCourses(){
		return CompletedCourses;
	}
}
//