package com.csci3130.daloffline.initialization;

import com.csci3130.daloffline.DalOfflineUI;
import com.csci3130.daloffline.authentication.Authenticator;
import com.csci3130.daloffline.domain.*;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceContextType;
import javax.persistence.*;




/**
 * Static methods to initialize and clean up  the database for testing
 * 
 * @author Jesse MacLeod
 *
 */
public class DatabaseInitializer {

	public static ArrayList<UsernamePasswordPair> usernamePasswordPairs = new ArrayList<UsernamePasswordPair>();
	
	/**
	 * Fills the specified persistence unit (database) with user and password pairs for testing.
	 * @param persistenceUnitName
	 */
	public static void generateUsers(EntityManagerFactory factory) {
		
		EntityManager em = factory.createEntityManager();
		
		usernamePasswordPairs.add(new UsernamePasswordPair("user", Authenticator.hash("pass")));
		usernamePasswordPairs.add(new UsernamePasswordPair("jesse", Authenticator.hash("1234")));
		usernamePasswordPairs.add(new UsernamePasswordPair("xrd", Authenticator.hash("mmspos")));
		usernamePasswordPairs.add(new UsernamePasswordPair("Bobethy", Authenticator.hash("Collective")));
		
		//factory = Persistence.createEntityManagerFactory(DalOfflineUI.PERSISTANCE_UNIT);
		//EntityManager em = factory.createEntityManager();
		
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
		
		try {
			em.getTransaction().begin();
			List<UsernamePasswordPair> allUsers = em.createQuery("Select a from UsernamePasswordPair a", UsernamePasswordPair.class).getResultList();
			em.getTransaction().commit();
			
			for(UsernamePasswordPair u : allUsers) {
				System.out.println("Successfully added username: "+u.getUsername()+" with password: "+u.getPassword());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
			
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
			
			for(UsernamePasswordPair u : usernamePasswordPairs) {

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