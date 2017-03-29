package com.csci3130.daloffline.domain;

import java.io.Serializable;
import java.util.ArrayList;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity (name="FACULTY")
public class Faculty extends User implements Serializable{
private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue
	private long id;
	private ArrayList<Course> teachingList;
	public Faculty(String username, String password, String name, String major){
		super(username, password, name, major);
	}
	public Faculty(){
		super();
	}
	public void addCourse(Course course){
	teachingList.add(course);
	}
	public ArrayList<Course> getteachingList(){
		return teachingList;
	}
}
