package com.csci3130.daloffline.courses;

import java.io.Serializable;
import java.util.Vector;

import javax.persistence.*;

/**
 * Work in progress subclass for a course's lab
 * 
 * @author Hawley Jean
 */


public class Lab extends Period implements Serializable{
	
	private Vector<String> teachingAssistants = new Vector<String>();
	
	public Lab(String loc, int CRN)
	{
		super(loc, CRN);
	}
	
	public void addTA(String TA)
	{
		teachingAssistants.add(TA);
	}
	public Vector<String> getTAs()
	{
		return teachingAssistants;
	}

}
