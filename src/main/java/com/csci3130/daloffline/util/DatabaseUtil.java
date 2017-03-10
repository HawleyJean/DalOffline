package com.csci3130.daloffline.util;

import java.util.ArrayList;

import javax.persistence.EntityManager;
//import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import com.csci3130.daloffline.NavigatorUI;
import com.csci3130.daloffline.authentication.Authenticator;
import com.csci3130.daloffline.domain.Student;
import com.csci3130.daloffline.domain.UserPasswordPair;
//import com.vaadin.addon.jpacontainer.JPAContainer;
//import com.vaadin.addon.jpacontainer.JPAContainerFactory;

public class DatabaseUtil {
	
	public static void generateUsernamePasswordPairs() {
		
		EntityManager em = Persistence.
				createEntityManagerFactory(NavigatorUI.PERSISTENCE_UNIT).
				createEntityManager();
		
		ArrayList<String> usernames = new ArrayList<String>();
		usernames.add("xy5235265");
		usernames.add("ov9000");
		usernames.add("fn2817");
		usernames.add("pn1570007");
		usernames.add("user");
		usernames.add("user2");
		
		ArrayList<String> passwords = new ArrayList<String>();
		passwords.add("killplsme");
		passwords.add("meplskill");
		passwords.add("plskillme");
		passwords.add("killmepls");
		passwords.add("pass");
		passwords.add("pass2");
		
		em.getTransaction().begin();
		
		for(int i = 0; i < usernames.size(); i++) {
			
			System.out.println("Adding user: "+usernames.get(i) + ", password: "+ passwords.get(i) + ", hashed: " + Authenticator.hash(passwords.get(i)));
			Student u = new Student();
			u.setUserPasswordPair(new UserPasswordPair(usernames.get(i), Authenticator.hash(passwords.get(i))));
			em.persist(u);
			
	//		System.out.println("Added user: "+usernamePasswordPairs.getItem(usernames.get(i)).getEntity().getUsername()+".");
			
		}
		
		em.getTransaction().commit();
	}
	
//	public static void main(String args[])
//	{
//		generateUsernamePasswordPairs();
//		
//	}
	

}