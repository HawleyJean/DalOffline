package com.csci3130.daloffline.tests;

import static org.junit.Assert.*;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.junit.Test;

import com.csci3130.daloffline.authentication.*;
import com.csci3130.daloffline.initialization.DatabaseInitializer;

public class AuthenticatorTest {

	@Test
	public void authenticateTest() {

		EntityManagerFactory factory = Persistence.createEntityManagerFactory("daloffline_db");

		//this will have to be changed later when we are no longer "generating users" but creating users
	    DatabaseInitializer.generateUsers(factory);
		assertEquals(true, Authenticator.authenticate("user", "pass", factory));
		assertNotEquals(true, Authenticator.authenticate("wrong", "pass", factory));
		assertNotEquals(true, Authenticator.authenticate("user", "wrong", factory));
		assertNotEquals(true, Authenticator.authenticate("", "", factory));
		
		assertEquals(true, true);

	}

}
