package com.csci3130.daloffline.domain;

import java.io.Serializable;
import java.util.ArrayList;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity (name="FACULTY")
public class Faculty extends User implements Serializable{
private static final long serialVersionUID = 1L;

	private ArrayList<Section> teachingList;
	public Faculty(String username, String password, String name, String major){
		super(username, password, name, major);
		teachingList = new ArrayList<Section>();
	}
	public Faculty(){
		super();
	}
	public void addCourse(Section section){
	teachingList.add(section);
	}
	public ArrayList<Section> getteachingList(){
		return teachingList;
	}
}