package com.csci3130.daloffline.initialization;

import com.csci3130.daloffline.DalOfflineUI;
import com.csci3130.daloffline.authentication.Authenticator;
import com.csci3130.daloffline.domain.*;

import java.util.ArrayList;
import java.util.List;

import java.util.*;

import javax.persistence.*;


public class DatabaseUtilities 
{
	
	public static Course createCourse(String courseName, String faculty, String courseCode, String instructorName, EntityManagerFactory factory)
	{
		EntityManager em = factory.createEntityManager();
		
		em.getTransaction().begin();
		
		Course newCourse = new Course(courseName, faculty, courseCode, instructorName);
		
		em.persist(newCourse);
	
		em.getTransaction().commit();
		
		em.close();
		return newCourse;
	}
	
	public static Section createLab(String loc, int CRN, String instructor, int sectionSize,int hours, int minutes, int dur,
									Course theCourse, EntityManagerFactory factory)
	{
		EntityManager em = factory.createEntityManager();
		
		em.getTransaction().begin();
	
		Section newSection = new Section(loc, CRN, instructor, hours, sectionSize, minutes, dur);
		
		theCourse.addLab(newSection);
	
		em.persist(theCourse);
		
		em.getTransaction().commit();
	
		em.close();
		
		return newSection;
	}
	
	public static Section createLecture(String loc, int CRN, String instructor, int sectionSize, int hours, int minutes, int dur,
										Course theCourse, EntityManagerFactory factory)
	{
		EntityManager em = factory.createEntityManager();
		
		Section newSection = new Section(loc, CRN, instructor, sectionSize, hours, minutes, dur);
		
		theCourse.addLecture(newSection);
		
		em.persist(theCourse);
		
		em.getTransaction().commit();
	
		em.close();
		
		return newSection;
	}
	
	public static boolean deleteCourse(String courseName ,EntityManagerFactory factory)
	{
		boolean worked = false;
		
		EntityManager em = factory.createEntityManager();
		
		em.getTransaction().begin();
		
		List<Course> allCourses = em.createQuery("SELECT courseName FROM COURSES courseName WHERE course.courseName = :input_user", Course.class).setParameter("input_user", courseName).getResultList();
		em.getTransaction().commit();
		em.close();
		
		return worked;
	}
	
	public static List<Course> getAllCourses(EntityManagerFactory factory)
	{
		EntityManager em = factory.createEntityManager();
		
		em.getTransaction().begin();
				
		List<Course> allCourses = em.createQuery("Select a from COURSES a", Course.class).getResultList();
		
		em.getTransaction().commit();
		
		em.close();
		
		return allCourses;
	}
	
	
	
}
