package com.csci3130.daloffline.tests.initialization;

import com.csci3130.daloffline.DalOfflineUI;
import com.csci3130.daloffline.authentication.Authenticator;
import com.csci3130.daloffline.domain.*;

import java.util.ArrayList;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

/**
 * Static methods to initialize and clean up  the database for testing
 * 
 * @author Jesse MacLeod
 *
 */
public class DatabaseInitializer {

	public static ArrayList<UsernamePasswordPair> usernamePasswordPairs = new ArrayList<UsernamePasswordPair>();
	public static EntityManagerFactory factory;
	
	public static void generateUsers() {
		
		usernamePasswordPairs.add(new UsernamePasswordPair("user", Authenticator.hash("pass")));
		usernamePasswordPairs.add(new UsernamePasswordPair("jesse", Authenticator.hash("1234")));
		usernamePasswordPairs.add(new UsernamePasswordPair("xrd", Authenticator.hash("mmspos")));
		usernamePasswordPairs.add(new UsernamePasswordPair("Bobethy", Authenticator.hash("Collective")));
		
		factory = Persistence.createEntityManagerFactory(DalOfflineUI.PERSISTANCE_UNIT);
		EntityManager em = factory.createEntityManager();
		
		try {
			em.getTransaction().begin();
			for(UsernamePasswordPair u : usernamePasswordPairs) {
				em.persist(u);
				System.out.println("Adding username: "+u.getUsername()+" with password: "+u.getPassword());
			}
			em.getTransaction().commit();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		em.close();
		factory.close();
	}
	
	public static void clearUsers() {
		factory = Persistence.createEntityManagerFactory(DalOfflineUI.PERSISTANCE_UNIT);
		EntityManager em = factory.createEntityManager();
		
		try {
			em.getTransaction().begin();
			
			for(UsernamePasswordPair u : usernamePasswordPairs) {
				// this seems redundant
				//UsernamePasswordPair user = em.find(UsernamePasswordPair.class, u.getUsername());
				em.remove(u);
				System.out.println("removing username: "+u.getUsername()+" with password: "+u.getPassword());
			}
			em.getTransaction().commit();
		} catch (Exception e) {
			e.printStackTrace();
		}
		em.clear();
		factory.close();
		
	}
}
