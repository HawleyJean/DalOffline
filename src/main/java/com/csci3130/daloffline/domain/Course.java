package com.csci3130.daloffline.domain;

import java.io.Serializable;
import java.util.*;

import javax.persistence.*;

import com.csci3130.daloffline.classes.Lab;
import com.csci3130.daloffline.classes.Lecture;

/**
 * Class that represents a university course
 * 
 * @author Hawley Jean
 */

@Entity
public class Course implements Serializable, Cloneable {
	
	@Id
	@GeneratedValue
	long id;
	private String courseName;
	
	@ManyToMany
	private Set<Faculty> faculties;
	
	private String courseCode;
	private String professorName;
	
	private ArrayList<Lecture> LectureTimes = new ArrayList<Lecture>();
	private ArrayList<Lab> LabTimes = new ArrayList<Lab>();
	
	/**
	 * Base Constructor
	 * 
	 * @param None
	 * @category Constructor
	 */
	public Course()
	{	
		
	}
	/**
	 * Constructor that initializes values
	 * 
	 * @param cName - The name of the course
	 * @param depar - The course's department
	 * @param code - The course code
	 * @param prof - The name of the professor
	 * @category Constructor
	 */
	public Course(String cName, String depar, String code, String prof)
	{	
		courseName = cName;
		//faculties = depar;
		courseCode = code;
		professorName = prof;
	}
	
	/**
	 * Adds a lecture to LectureTimes
	 * 
	 * @param lec - A Lecture object
	 * @return Nothing
	 */
	public void addLecture(Lecture lec)
	{
		LectureTimes.add(lec);
	}
	/**
	 * Adds a lab to LabTimes
	 * 
	 * @param lab - A Lab object
	 * @return Nothing
	 */
	public void addLab(Lab lab)
	{
		LabTimes.add(lab);
	}
	/**
	 * Returns the course name
	 * 
	 * @return String
	 */
	public String getName()
	{
		return courseName;
	}
	/**
	 * Returns the department name
	 * 
	 * @return String
	 */
	public Set<Faculty> getFaculties()
	{
		return faculties;
	}
	/**
	 * Returns the course code
	 * 
	 * @return String
	 */
	public String getCode()
	{
		return courseCode;
	}
	/**
	 * Returns the professor's name
	 * 
	 * @return String
	 */
	public String getProfessorName() {
		return professorName;
	}
}
	
