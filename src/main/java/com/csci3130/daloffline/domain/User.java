package com.csci3130.daloffline.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

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
    @Column(name = "USER_ID", nullable = false)
	private long id;
	
	private String major;
	private String fullName;
	
	@Column(unique=true)
	private String username;
	private String password;
	
	@OneToMany
	private List<Section> enrolledSections;
	
	// constructors
	
	public User()
	{
		super();
	}
	
	public User(String username, String password, String name, String major)
	{
		this.username = username;
		this.password = password;
		this.fullName = name;
		this.major = major;
		enrolledSections = new ArrayList<Section>();
	}
	
	
	/**
	 * @return Banner number
	 */
	public String getBannerNumber()
	{
		return "B00"+String.format("%06d", id);
	}
	
	
	/**
	 * @param sec - Section to add to the users enrolledSections list
	 * @return true if added properly, false otherwise.
	 */
	public boolean addSection(Section sec)
	{
		enrolledSections.add(sec);
		return true;
	}
	
	//
	//Not working
	public boolean removeCourse(Course c)
	{
		boolean courseFound = false;

		//ArrayList<Integer> foundIndexes = new ArrayList<Integer>();
		for(int i=0; i<enrolledSections.size(); i++)
		{
			if(enrolledSections.get(i).getCourse().getID() == c.getID())
			{
				courseFound = true;
				//foundIndexes.add(i);
				enrolledSections.remove(i);
				i--;
			}
		}
		
		return courseFound;
	}
	
	
	/**
	 * @return List of enrolled sections
	 */
	public List<Section> getEnrolledSections(){
		return enrolledSections;
	}

	/**
	 * @return the username of this user
	 */
	public String getUsername() {
		return username;
	}

	/**
	 * @param username
	 */
	public void setUsername(String username) {
		this.username = username;
	}

	/**
	 * @return The password of this user
	 */
	public String getPassword() {
		return password;
	}

	
	/**
	 * @param password
	 */
	public void setPassword(String password) {
		this.password = password;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	/**
	 * @return The major of this user
	 */
	public String getMajor() {
		return major;
	}

	/**
	 * @param major
	 */
	public void setMajor(String major) {
		this.major = major;
	}

	/**
	 * @return The full name of this user
	 */
	public String getFullName() {
		return fullName;
	}

	/**
	 * @param fullName
	 */
	public void setFullName(String fullName) {
		this.fullName = fullName;
	}
	
	
   

}
