package com.csci3130.daloffline.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.GregorianCalendar;

import javax.persistence.*;

/**
 * Work in progress class for a course's lecture or lab period
 * 
 * @author Hawley Jean
 * @author Connor Foran
 */

@Entity
public class Section implements Serializable, Cloneable {
		
	/**
	 *  init version
	 */
	private static final long serialVersionUID = 1L;
	
    @Id
    @Column(name = "SECTION_ID", nullable = false)
    @GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	
	private String location;
	private int CRN;
	private String instructorName;
	
	//Time attributes
	private ArrayList<Integer> daysOfWeek;
	private int startHour;
	private int startMinute;
	private int durationMinutes;
	
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name="COURSE_ID")
    private Course course;

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
	}
	public Section(String loc, int CRN, String instructor)
	{
		location = loc;
		this.CRN = CRN;
		instructorName = instructor;
		daysOfWeek = new ArrayList<Integer>();
		startHour = 8;
		startMinute = 35;
		durationMinutes = 50;
	}
	public Section(String loc, int CRN, String instructor, int hours, int minutes, int dur)
	{
		location = loc;
		this.CRN = CRN;
		instructorName = instructor;
		daysOfWeek = new ArrayList<Integer>();
		startHour = hours;
		startMinute = minutes;
		durationMinutes = dur;
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
		
}
