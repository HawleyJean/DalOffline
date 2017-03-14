package com.csci3130.daloffline.courses;

import java.util.Vector;

/**
 * Work in progress class for a course's lecture or lab period
 * 
 * @author Hawley Jean
 */

public class Period {
		
	String location;
	private int CRN;
	private Vector<DaySlot> times = new Vector<DaySlot>();
		
	public Period()
	{
		location = "Unknown";
		CRN = 0;
	}
	public Period(String loc, int CRN)
	{
		location = loc;
		this.CRN = CRN;
	}
		
	public void addTime(int day, int hours, int minutes, int dur)
	{
		DaySlot newDay = new DaySlot(day, hours, minutes, dur);
		times.add(newDay);
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
		
}
