package com.csci3130.daloffline.courses;

/**
 * Class to represent a time period for a class's lecture or lab
 * 
 * @author Hawley Jean
 */

public class DaySlot {

	private int dayOfWeek;
	private int startHour;
	private int startMinute;
	private int durationMinutes;

	/**
	 * Basic constructor
	 * 
	 * @param None
	 * @category Constructor
	 */
	public DaySlot(){}
	
	/**
	 * Constructor that sets values
	 * 
	 * @param day - integer representing the day of the week
	 * @param hours - integer representing the hour of the time
	 * @param minutes - integer representing the minutes of the time
	 * @param dur - integer that represents the number of minutes that the period lasts for (usually 60 or 90 minutes)
	 * @category Constructor
	 */
	public DaySlot(int day, int hours, int minutes, int dur)
	{
		dayOfWeek = day;
		startHour = hours;
		startMinute = minutes;
		durationMinutes = dur;
	}

	/**
	 * Get the day
	 * 
	 * @return int
	 */
	public int getDay() {
		return dayOfWeek;
	}
	/**
	 * Set the day
	 * 
	 * @param day: integer
	 */
	public void setDay(int day) {
		dayOfWeek = day;
	}
	/**
	 * Get the hour
	 * 
	 * @return int
	 */
	public int getStartHour() {
		return startHour;
	}
	/**
	 * Set the hour
	 * 
	 * @param hour: integer
	 */
	public void setStartHour(int hour) {
		startHour = hour;
	}
	/**
	 * Get the minutes
	 * 
	 * @return int
	 */
	public int getStartMinute() {
		return startMinute;
	}
	/**
	 * Set the minutes
	 * 
	 * @param min: integer
	 */
	public void setStartMinute(int min) {
		startMinute = min;
	}
	/**
	 * Get the duration
	 * 
	 * @return int
	 */
	public int getDuration() {
		return durationMinutes;
	}
	/**
	 * Set the duration
	 * 
	 * @param dur: integer
	 */
	public void setDuration(int dur) {
		durationMinutes = dur;
	}
	
	/**
	 * Returns a string representation of the date represented by this object (Work in progress)
	 * 
	 * @return String
	 */
	public String toString()
	{
		String out = "";
		int hour = startHour;
		boolean PM = false;
		
		if(dayOfWeek == 1)
			out += "Sunday";
		else if(dayOfWeek == 2)
			out += "Monday";
		else if(dayOfWeek == 3)
			out += "Tuesday";
		else if(dayOfWeek == 4)
			out += "Wednesday";
		else if(dayOfWeek == 5)
			out += "Thursday";
		else if(dayOfWeek == 6)
			out += "Friday";
		else if(dayOfWeek == 7)
			out += "Saturday";
		
		if(hour > 12){
			hour -= 12;
			PM = true;
		}
		
		out += " @ "+hour+":"+startMinute;
		if(PM) out += " PM";
		else out += " AM";
		out += " (Duration: "+durationMinutes+" minutes)";
		
		return out;
	}
	
}

