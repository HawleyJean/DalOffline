package com.csci3130.daloffline.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.LinkedList;
import java.util.List;

import javax.persistence.*;

/**
 * Work in progress class for a course's lecture or lab period
 * 
 * @author Hawley Jean
 * @author Connor Foran
 * @author Braden Oickle
 * 
 */

@Entity(name = "SECTIONS") 
public class Section implements Serializable, Cloneable {
		//
	/**
	 *  init version
	 */
	private static final long serialVersionUID = 1L;
	
    @Id
    @Column(name = "SECTION_ID", nullable = false)
    @GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	
	private String location;
	
	@Column(name = "CRN")
	private int CRN;
	private String instructorName;
	//added in to cap student reg.; add to wait list
	private int sectionSize;
	private boolean isLab;
	
	
	//list of wait list students and
	@OneToMany
	private List<User> waitList;
	@OneToMany
	private List<User> currentStudents;

	//Time attributes
	private ArrayList<Integer> daysOfWeek;
	private int startHour;
	private int startMinute;
	private int durationMinutes;
	
	
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name="COURSE_ID")
    private Course course;
	
	@OneToMany
	private List<User> students;

	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name="USER_ID")
	private Faculty faculty;

	//Constructors
	public Section()
	{
		location = "Unknown";
		CRN = 0;
		instructorName = "Unknown";
		daysOfWeek = new ArrayList<Integer>();
		startHour = 8;
		startMinute = 35;
		durationMinutes = 50;
		sectionSize = 0;
		waitList = new LinkedList<User>();
		currentStudents = new ArrayList<User>();
	}
	public Section(String loc, String instructor, int hours, int minutes, int dur, int[] days, Course course, boolean isLab, int size)
	{
		location = loc;
		instructorName = instructor;
		daysOfWeek = new ArrayList<Integer>();
		startHour = hours;
		startMinute = minutes;
		durationMinutes = dur;
		addDays(days);
		this.course = course;
		sectionSize = size;
		this.isLab = isLab;
		if(isLab)
			course.addLab(this);
		else
			course.addLecture(this);
	}
	public Section(String loc, int hours, int minutes, int dur, int[] days, Course course, boolean isLab, int size)
	{
		location = loc;
		instructorName = course.getInstructorName();
		daysOfWeek = new ArrayList<Integer>();
		startHour = hours;
		startMinute = minutes;
		durationMinutes = dur;
		addDays(days);
		this.course = course;
		sectionSize = size;
		this.isLab=isLab;
		if(isLab)
			course.addLab(this);
		else
			course.addLecture(this);
	}
	
	public long getID()
	{
		return id;
	}
	
	//Set the time
	public void setTime(int hours, int minutes, int dur)
	{
		startHour = hours;
		startMinute = minutes;
		durationMinutes = dur;
	}
	//Add a day
	public void addDay(int day)
	{
		if(!daysOfWeek.contains(day) && day >= 1 && day <= 7)
			daysOfWeek.add(day);
	}
	public void addDays(int[] days)
	{
		for(int day : days){
			if(!daysOfWeek.contains(day) && day >= 1 && day <= 7)
				daysOfWeek.add(day);
		}
	}
	//Remove a day
	public void removeDay(int day)
	{
		int index = daysOfWeek.indexOf(day);
		if(index > -1)
			daysOfWeek.remove(index);
	}
	//Get the start times as a list of calendar objects (For adding to calendar)
	public ArrayList<GregorianCalendar> getStartTimes()
	{
		ArrayList<GregorianCalendar> startTimes = new ArrayList<GregorianCalendar>();
		for(int day : daysOfWeek)
		{
			GregorianCalendar newTime = new GregorianCalendar();
			newTime.set(GregorianCalendar.DAY_OF_WEEK, day);
			newTime.set(GregorianCalendar.HOUR_OF_DAY, startHour);
			newTime.set(GregorianCalendar.MINUTE, startMinute);
			newTime.set(GregorianCalendar.SECOND, 0);
			startTimes.add(newTime);
		}
		return startTimes;
	}
	//Get the end times as a list of calendar objects (For adding to calendar)
	public ArrayList<GregorianCalendar> getEndTimes()
	{
		ArrayList<GregorianCalendar> endTimes = new ArrayList<GregorianCalendar>();
		for(int day : daysOfWeek)
		{
			GregorianCalendar newTime = new GregorianCalendar();
			newTime.set(GregorianCalendar.DAY_OF_WEEK, day);
			newTime.set(GregorianCalendar.HOUR_OF_DAY, startHour);
			newTime.set(GregorianCalendar.MINUTE, startMinute+durationMinutes);
			newTime.set(GregorianCalendar.SECOND, 0);
			endTimes.add(newTime);
		}
		return endTimes;
	}
	
	//would be instantiated after a course could not be added to currentStudents
	public void addToWaitList(User user){
		waitList.add(user);
	}
	
	//adds the head of the wait list to the list of students
	//we don't have to implement this right now
	public void waitListPush(){
		if(waitList.size() != 0){
			//currentStudents.add(waitList.remove());
		}
	}
	//if there's space in the class, we'll let a student be added
	public boolean hasSpace(){
		if(sectionSize > currentStudents.size())
			return true;
		return false;
	}
	public int getWaitListSize(){
		return waitList.size();
	}
	public boolean onWaitList(User user){
		boolean onList = false;
		for(int i=0;i < waitList.size();i++){
			if(user.getUsername().equals(waitList.get(i).getUsername()))
				onList = true;
		}
		return onList;
	}
	//Get and set methods
	
	public Course getCourse(){
		return course;
	}
	public void setCourse(Course newCourse){
		course = newCourse;
	}

	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}

	public int getCRN() {
		return CRN;
	}
	public void setCRN(int CRN) {
		this.CRN = CRN;
	}
	
	public String getInstructor() {
		return instructorName;
	}
	
	public void setInstructor(String instructorName) {
		this.instructorName = instructorName;
	}
	public void setSectionSize(int sectionSize){
		this.sectionSize = sectionSize;
	}
	public int getSectionSize(){
		return sectionSize;
	}

	public boolean isLab(){
		return isLab;
	}
	
	public void addStudent(User student) {
//	public void addStudent(User student, EntityManagerFactory factory) {
//		EntityManager em = factory.createEntityManager();
//		em.getTransaction().begin();
		students.add(student);
//		em.getTransaction().commit();
//		em.close();
	}
	
	public List<User> getAllStudents() {
		return students;
	}

	public void setFaculty(Faculty faculty){
		this.faculty = faculty;
		instructorName=faculty.getFullName();
		//faculty.addCourse(this);
	}
	public Faculty getFaculty(){
		return faculty;
	}
}