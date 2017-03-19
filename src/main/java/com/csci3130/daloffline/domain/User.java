package com.csci3130.daloffline.domain;

import java.io.Serializable;
import java.util.ArrayList;

import javax.persistence.*;

/**
 * Class for User objects to be stored in the database
 *
 * @author Jesse MacLeod
 * @author Eric Nguyen
 * @author Connor Foran
 */
@Entity(name = "USERS") 
public class User implements Serializable {

	
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue
	private long id;
	
	private String major;
	private String fullName;
	
	@Column(unique=true)
	private String username;
	private String password;
	
	@OneToMany
	private ArrayList<Section> enrolledSections;
	
	public User()
	{
		super();
	}
	
	public User(String username, String password, String name, String major)
	{
		this.username = username;
		this.password = password;
		this.setFullName(name);
		this.setMajor(major);
	}
	
	public String getBannerNumber()
	{
		return "B00"+String.format("%06d", id);
	}
	
	public boolean addSection(Section sec)
	{
		enrolledSections.add(sec);
		return true;
	}
	
	public ArrayList<Section> getEnrolledSections(){
		return enrolledSections;
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

	public String getMajor() {
		return major;
	}

	public void setMajor(String major) {
		this.major = major;
	}

	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}
	
	
   

}
