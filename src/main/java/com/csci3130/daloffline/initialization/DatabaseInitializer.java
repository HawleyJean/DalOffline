package com.csci3130.daloffline.initialization;

import com.csci3130.daloffline.DalOfflineUI;
import com.csci3130.daloffline.authentication.Authenticator;
import com.csci3130.daloffline.domain.*;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.*;
//

/**
 * Static methods to initialize and clean up  the database for testing
 * 
 * @author Jesse MacLeod (For users)
 * @author Connor Foran (For courses/sections)
 */
public class DatabaseInitializer {

	public static ArrayList<User> users = new ArrayList<User>();

	//public static ArrayList<Course> courses = new ArrayList<Course>();
	
	/**
	 * Fills the specified persistence unit (database) with user and password pairs for testing.
	 * @param persistenceUnitName
	 */
	public static void generateUsers(EntityManagerFactory factory) {

		EntityManager em = factory.createEntityManager();
		Student Hawley = new Student("Hawley", Authenticator.hash("pass"), "Hawley Jean", "Music");
		Course randomCourse = new Course("Mechanical Engineering", "Computer Science", "CSCI3130", "Dr. Ashraf Abusharekh");
		users.add(new User("user", Authenticator.hash("pass"), "Jimmy McStudentFace", "Computer Science"));
		users.add(new User("jesse", Authenticator.hash("1234"), "Jesse McLeod", "Computer Science"));
		users.add(new Student("student", Authenticator.hash("pass"), "student class", "Computer Science"));
		Faculty Nauzer = new Faculty("nauzer", Authenticator.hash("isthatclear"), "Nauzer Kalywani", "Computer Science");
		//users.add(new User("xrd", Authenticator.hash("mmspos")));
		//users.add(new User("Bobethy", Authenticator.hash("Collective")));
	
		Section sec = new Section("Killam 312", "Juliano", 8, 30, 90, new int[]{4,6}, randomCourse, true, 60);
		em.persist(randomCourse);
		em.persist(sec);
		Hawley.addCompletedCourse(randomCourse);
		Nauzer.addCourse(sec);
		users.add(Hawley);
		users.add(Nauzer);
		
		
		try {
			em.getTransaction().begin();
			
			for(User u : users) {
				em.persist(u);
				System.out.println("Adding username: "+u.getUsername()+" with password: "+u.getPassword());
			}
			em.getTransaction().commit();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		try {
			em.getTransaction().begin();
			List<User> allUsers = em.createQuery("Select a from USERS a", User.class).getResultList();
			em.getTransaction().commit();
			
			for(User u : allUsers){System.out.println("Successfully added username: "+u.getUsername()+" with password: "+u.getPassword());}
		} catch (Exception e) {
			e.printStackTrace();
		}
			
		em.close();
	}
	
	/**
	 * Fills the specified persistence unit (database) with courses for testing.
	 * @param persistenceUnitName
	 */
	public static void generateCourses(EntityManagerFactory factory) {
		
		EntityManager em = factory.createEntityManager();
		
		//Student student = (Student)DatabaseUtilities.getUserByUserName("Hawley", factory);
		em.getTransaction().begin(); //Begin a transaction
		
		Course newCourse = new Course("Software Engineering", "Computer Science", "CSCI3130", "Dr. Ashraf Abusharekh"); //Create a course object with params
		Section newSection = new Section("Killam 312", "Juliano", 8, 30, 90, new int[]{4,6}, newCourse, true, 60); //Create a section with params
		newSection = new Section("Psych. 415", 16, 0, 90, new int[]{3,5}, newCourse, false, 0);
		em.persist(newCourse); //Persist the course (will automatically persist the added sections)
		
		newCourse = new Course("Principles of Programming Languages", "Computer Science", "CSCI3136", "Dr. Nauzer Kalyaniwalla");
		newSection = new Section("CS 127", 13, 30, 60, new int[]{2,4,6}, newCourse, false, 30);
		newSection = new Section("CS 127", 11, 30, 60, new int[]{2,4,6}, newCourse, false, 30);
		em.persist(newCourse);
		
		newCourse = new Course("Operating Systems", "Computer Science", "CSCI3120", "Khurram Aziz");
		newSection = new Section("CS 127", 10, 0, 90, new int[]{4,6}, newCourse, false, 40);
		newSection = new Section("CS 127", 11, 30, 60, new int[]{2,4,6}, newCourse, false, 40);
		em.persist(newCourse);
		newCourse = new Course("Network Computing", "Computer Science", "CSCI3171", "Dr. Nur Zincir-Heywood");
		newSection = new Section("LSC 300", 13, 30, 60, new int[]{2,3,5}, newCourse, false, 50);
		newSection = new Section("CS Teaching Lab 2", "Abdulhadi Alqarni", 13, 30, 60, new int[]{4}, newCourse, true, 25);
		newSection = new Section("CS Teaching Lab 2", "Saurabh Dey", 13, 30, 60, new int[]{6}, newCourse, true, 25);
		em.persist(newCourse);
		//student.addCompletedCourse(newCourse);
		Course musicCourse = new Course("Introdoction to Jazz Hands", "Music", "MUSC1223", "Dr. Douglas Reach"); //Create a course object with params
		//System.out.println("The course size is "+student.getCompletedCourses().size()+"\n\n");
		newSection = new Section("RC 112", 13, 30, 60, new int[]{2, 3, 5},musicCourse,true, 15);
		em.persist(musicCourse);
		
		
		
		
		em.getTransaction().commit();
		em.close();
	}
	
	/**
	 * Clears the users generated by DatabaseInitializer.gernerateUsers() from the specified persistence unit.
	 * @param persistenceUnitName
	 */
	

	
	public static void clearUsers(EntityManagerFactory factory) {

		EntityManager em = factory.createEntityManager();
		try {
			em.getTransaction().begin();
			
			for(User u : users) {

				em.remove(u);
				System.out.println("removing username: "+u.getUsername()+" with password: "+u.getPassword());

			}
			em.getTransaction().commit();
		} catch (Exception e) {
			e.printStackTrace();
		}
		em.close();
		
	}
}
