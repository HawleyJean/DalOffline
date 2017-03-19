package com.csci3130.daloffline.domain;

import java.io.Serializable;
import java.util.ArrayList;

import javax.persistence.*;


/**
 * Class that represents a university course
 * 
 * @author Hawley Jean
 * @author Connor Foran
 */

@Entity(name = "COURSES") 
public class Course implements Serializable, Cloneable {
	
	/**
	 *  init version
	 */
	private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "COURSE_ID", nullable = false)
    @GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	
    //Attributes
	private String courseName;
	private String courseCode;
	private String faculty;
	private String instructorName;
	
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "course")
	private ArrayList<Section> lectures;
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "course")
	private ArrayList<Section> labs;
	
	
	/**
	 * Base Constructor
	 * 
	 * @param None
	 * @category Constructor
	 */
	public Course()
	{	
		courseName = "Unknown";
		faculty = "Unknown";
		courseCode = "XXXX0000";
		instructorName = "Unknown";
		lectures = new ArrayList<Section>();
		labs = new ArrayList<Section>();
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
		lectures = new ArrayList<Section>();
		labs = new ArrayList<Section>();
	}
	
	/**
	 * Adds a lecture to the course
	 * 
	 * @param lec - A Section object representing a lecture
	 * @return Nothing
	 */
	public void addLecture(Section lec)
	{
		lectures.add(lec);
	}
	/**
	 * Adds a lecture to the course
	 * 
	 * @param loc - a string representing the location
	 * @param CRN - an integer for the Course Registration Number
	 * @param Instructor - A string for the instructor's name
	 * @return Nothing
	 */
	public void addLecture(String loc, int CRN, String instructor)
	{
		lectures.add(new Section(loc, CRN, instructor));
	}
	/**
	 * Adds a lab to the course
	 * 
	 * @param lab - A Section object representing a lab
	 * @return Nothing
	 */
	public void addLab(Section lab)
	{
		labs.add(lab);
	}
	/**
	 * Adds a lab to the course
	 * 
	 * @param loc - a string representing the location
	 * @param CRN - an integer for the Course Registration Number
	 * @param Instructor - A string for the instructor's name
	 * @return Nothing
	 */
	public void addLab(String loc, int CRN, String instructor)
	{
		labs.add(new Section(loc, CRN, instructor));
	}
	
	/**
	 * Deletes all lectures and labs
	 * 
	 * @param None
	 * @return Nothing
	 */
	public void clearSections()
	{
		labs.clear();
		lectures.clear();
	}
	
	/**
	 * Returns an arraylist of lectures (Section objects)
	 * 
	 * @param lab - A Section object representing a lab
	 * @return ArrayList<Section>
	 */
	public ArrayList<Section> getLectures()
	{
		return lectures;
	}
	/**
	 * Returns an arraylist of labs (Section objects)
	 * 
	 * @param lab - A Section object representing a lab
	 * @return ArrayList<Section>
	 */
	public ArrayList<Section> getLabs()
	{
		return labs;
	}
	
	/**
	 * Returns the primary key
	 * 
	 * @return long
	 */
	public long getID()
	{
		return id;
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
	
