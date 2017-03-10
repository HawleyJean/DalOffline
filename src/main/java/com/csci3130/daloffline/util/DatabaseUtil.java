package com.csci3130.daloffline.util;

import java.util.ArrayList;

import com.csci3130.daloffline.NavigatorUI;
import com.csci3130.daloffline.authentication.Authenticator;
import com.csci3130.daloffline.domain.UserPasswordPair;
import com.vaadin.addon.jpacontainer.JPAContainer;
import com.vaadin.addon.jpacontainer.JPAContainerFactory;

public class DatabaseUtil {
	
	public static void generateUsernamePasswordPairs() {
		
		JPAContainer<UserPasswordPair> usernamePasswordPairs = 
				JPAContainerFactory.make(UserPasswordPair.class, NavigatorUI.PERSISTENCE_UNIT);
		
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
		
		for(int i = 0; i < usernames.size(); i++) {
			System.out.println("Adding user: "+usernames.get(i)+", password: "+passwords.get(i)+", hashed: "+Authenticator.hash(passwords.get(i)));
			usernamePasswordPairs.addEntity(new UserPasswordPair(usernames.get(i), Authenticator.hash(passwords.get(i))));
			System.out.println("Added user: "+usernamePasswordPairs.getItem(usernames.get(i)).getEntity().getUsername()+".");
			usernamePasswordPairs.commit();
		}
	}
	
//	public static void main(String args[])
//	{
//		generateUsernamePasswordPairs();
//		
//	}
	

}