package com.csci3130.daloffline.authentication;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Base64;
import java.util.Base64.Encoder;

import com.csci3130.daloffline.domain.UserPasswordPair;
import com.vaadin.addon.jpacontainer.JPAContainer;

 
/**
 * Static class that has methods to authenticates user and password pairs.
 * 
 * @author Jesse MacLeod
 * @author Alex Gordon
 */
public class Authenticator {

	// placeholder stuff to be replaced with database stuff 
	private static ArrayList<String[]> userPasswordPairs;
	
	
	/**
	 * 
	 * Returns true if username and password parameters match username/password pair
	 * in the placeholder arraylist.
	 * 
	 * TODO Update this to connect to a data source to get the usernames and passwords
	 * 		rather than some stupid arraylist. 
	 * 
	 * @param username
	 * @param password
	 * @return True is username/password pair is found in data source
	 * @throws NoSuchAlgorithmException
	 * @throws UnsupportedEncodingException
	 */
	public static boolean authenticate(String username, String password, JPAContainer<UserPasswordPair> userNamePasswordPairs) {
		
		try{
			String db_pass = userNamePasswordPairs.getItem(username).getEntity().getPassword();	
			
			//TODO link this with data source to get the usernames and passwords rather than this stupid arraylist
			if (db_pass.equals(hash(password)))
			{
					//System.out.println("s[0]: "+s[0]+"\n"+"s[1]: "+s[1]); <-- fuck you you're irrelevant now
					return true;
			
			}
		} catch(Exception e) {
			System.out.println(e.toString());
			e.printStackTrace();
			return false;
		}
		
		return false;
		
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
