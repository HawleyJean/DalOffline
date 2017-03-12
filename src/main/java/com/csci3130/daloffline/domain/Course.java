package com.csci3130.daloffline.domain;

import java.io.Serializable;
import java.util.*;

import javax.persistence.*;

import com.csci3130.daloffline.courses.Lab;
import com.csci3130.daloffline.courses.Lecture;

/**
 * Class that represents a university course
 * 
 * @author Hawley Jean
 */

@Entity
public class Course implements Serializable, Cloneable {
	
	@Id
	@GeneratedValue
	private long id;
	
	private String courseName;
	private String faculty;
	private String courseCode;
	private String instructorName;
	
	//@ElementCollection
	@Transient // remove this when working
	private Set<Lecture> lectures = new HashSet<Lecture>();
	
	//@ElementCollection
	@Transient // remove this when working
	private Set<Lab> labs = new HashSet<Lab>();
	
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
	 * @param courseName - The name of the course
	 * @param faculty - The faculty that the course belongs to
	 * @param code - The course code
	 * @param prof - The name of the instructor
	 * @category Constructor
	 */
	public Course(String courseName, String faculty, String courseCode, String instructorName)
	{	
		this.courseName = courseName;
		this.faculty = faculty;
		this.courseCode = courseCode;
		this.instructorName = instructorName;
	}
	
	/**
	 * Adds a lecture to the course
	 * 
	 * @param lec - A Lecture object
	 * @return Nothing
	 */
	public void addLecture(Lecture lec)
	{
		lectures.add(lec);
	}
	/**
	 * Adds a lab to LabTimes
	 * 
	 * @param lab - A Lab object
	 * @return Nothing
	 */
	public void addLab(Lab lab)
	{
		labs.add(lab);
	}
	/**
	 * Returns the course name
	 * 
	 * @return String
	 */
	public String getCourseName()
	{
		return courseName;
	}
	/**
	 * Returns the department name
	 * 
	 * @return String
	 */
	public String getFaculty()
	{
		return faculty;
	}
	/**
	 * Returns the course code
	 * 
	 * @return String
	 */
	public String getCourseCode()
	{
		return courseCode;
	}
	/**
	 * Returns the professor's name
	 * 
	 * @return String
	 */
	public String getInstructorName() {
		return instructorName;
	}
}
	
