package com.csci3130.daloffline.util;

import java.util.ArrayList;

//import javax.persistence.CascadeType;
import javax.persistence.EntityManager;
//import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import com.csci3130.daloffline.NavigatorUI;
import com.csci3130.daloffline.authentication.Authenticator;
//import com.csci3130.daloffline.domain.Student;
import com.csci3130.daloffline.domain.UserPasswordPair;

public class DatabaseUtil {
	
	public static void generateUsernamePasswordPairs() {
		
		EntityManager em = Persistence.createEntityManagerFactory(NavigatorUI.PERSISTENCE_UNIT).createEntityManager();
		
		
		ArrayList<String> usernames = new ArrayList<String>();
		usernames.add("user");
		
		ArrayList<String> passwords = new ArrayList<String>();
		passwords.add("pass");
		
		em.getTransaction().begin();
		for(int i = 0; i < usernames.size(); i++) {
			
			System.out.println("Adding user: " + usernames.get(i) + ", password: "+ passwords.get(i) + ", hashed: " + Authenticator.hash(passwords.get(i)));
			UserPasswordPair u = new UserPasswordPair(usernames.get(i), Authenticator.hash(passwords.get(i)));
			em.persist(u);
	//		System.out.println("Added user: "+usernamePasswordPairs.getItem(usernames.get(i)).getEntity().getUsername()+".");	
		}
		
		em.getTransaction().commit();
	}
	
	public static void clearUsernamePasswordPairs() {
		EntityManager em = Persistence.createEntityManagerFactory(NavigatorUI.PERSISTENCE_UNIT).createEntityManager();
		em.clear();
		em.close();
	}
	
	public static void main(String args[]) {
		generateUsernamePasswordPairs();
		//clearUsernamePasswordPairs();
	}
	

}