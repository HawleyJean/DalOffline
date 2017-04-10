package com.csci3130.daloffline.domain;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.OneToMany;


/**
 * A class representing a user of type Student
 *
 */
@Entity(name = "STUDENTS")
public class Student extends User implements Serializable{
private static final long serialVersionUID = 1L;
	

	@OneToMany
	private List<Course> CompletedCourses;
	
	public Student(){
		super();
	}
	
	/**
	 * Constructor
	 * 
	 * @param username 
	 * @param pass
	 * @param name
	 * @param major 
	 */
	public Student(String username, String pass, String name, String major){
		super(username, pass, name, major);
		CompletedCourses = new ArrayList<Course>();
	}
	
	/**
	 * Add a course to the students list of completed courses.
	 * 
	 * @param course
	 */
	public void addCompletedCourse(Course course){
		CompletedCourses.add(course);
	}
	
	/** 
	 * @return A list of courses that the Student has completed
	 */
	public List<Course> getCompletedCourses(){
		return CompletedCourses;
	}
}
//