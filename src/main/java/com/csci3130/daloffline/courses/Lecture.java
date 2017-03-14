package com.csci3130.daloffline.courses;

import java.io.Serializable;

import javax.persistence.*;

/**
 * Work in progress subclass for a course's lecture
 * 
 * @author Hawley Jean
 */


public class Lecture extends Period implements Serializable
{
	/**
	 *  initial version
	 */
	private static final long serialVersionUID = 1L;

	public Lecture(String loc, int CRN)
	{
		super(loc, CRN);
	}
}
