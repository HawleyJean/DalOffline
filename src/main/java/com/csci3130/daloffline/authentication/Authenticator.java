package com.csci3130.daloffline.authentication;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Base64;
import java.util.Base64.Encoder;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceContextType;

import com.csci3130.daloffline.DalOfflineUI;
import com.csci3130.daloffline.domain.*;

 
/**
 * Static class that has methods to authenticates user and password pairs.
 * 
 * @author Jesse MacLeod
 * @author Alex Gordon
 */
public class Authenticator {
	
	/**
	 * 
	 * Returns true if username and password parameters match username/password pair
	 * in the database. Defaults to the main, persistant database set in Daloffline.PERSISTEBNCE_UNIT
	 * 
	 * TODO Update this to connect to a data source to get the usernames and passwords
	 * 		rather than some stupid arraylist. 
	 * 
	 * @param username
	 * @param password
	 * @return True is username/password pair is found in data source
	 */

	public static boolean authenticate(String username, String password, EntityManagerFactory factory) {
		
		EntityManager em = factory.createEntityManager(); 
		
		boolean out = false;
		
		try {
	
			// this is messy as hell but solves the problem of finding a single usernamepasswordpair by username
			// field rather than primary key.
			em.getTransaction().begin();
			List<User> test_result = em.createQuery("Select a from USERS a", User.class).getResultList();
			em.getTransaction().commit();
			
			System.out.println(test_result.size() + " RESULTS FOUND");
			
			for(User u : test_result) {
				System.out.println("FOUND: "+u.getUsername()+", PASSWORD: "+u.getPassword());
			}
			
			em.getTransaction().begin();
			User userpw = em.createQuery("SELECT user FROM USERS user WHERE user.username = :input_user", User.class)
														.setParameter("input_user", username).getSingleResult();
			em.getTransaction().commit();
			out = (userpw.getPassword().equals(Authenticator.hash(password)));
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		em.close();
		
		return out;
		
	}
	
	/**
	 * Returns a SHA-256 hashed query 
	 * 
	 * @param query
	 * @return SHA-256 hashed query 
	 * @throws NoSuchAlgorithmException
	 * @throws UnsupportedEncodingException
	 */
	public static String hash(String query) {
		
		MessageDigest md;
		byte[] result = null;
		
		try {
			md = MessageDigest.getInstance("SHA-256");
			Encoder b64 = Base64.getEncoder();
			result = md.digest(query.getBytes("UTF-8"));

			result = b64.encode(result);
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		
		//System.out.println(Arrays.toString(result));
		return Arrays.toString(result);
	}
	
}
