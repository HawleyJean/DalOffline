package com.csci3130.daloffline.courses;

import java.util.ArrayList;
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
	//Vectorss are garbage and we should use arraylists because they are not garbage. -Alex & Braden
		
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
	
	public Period checkConflicts(ArrayList<Period> array){
		
		for(int i = 0; i < array.size(); i++){
			for(int j = 0; j < array.get(i).getTimes().size(); j++){
				
			}
		}
		
		
		
		
		
		return null;
	}
	public Vector<DaySlot> getTimes() {
		return times;
	}
		
}
