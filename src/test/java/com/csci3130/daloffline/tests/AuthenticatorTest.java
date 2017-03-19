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
		
	    DatabaseInitializer.generateUsers(factory);
		
		assertEquals(true, Authenticator.authenticate("user", "pass", factory));
		assertNotEquals(true, Authenticator.authenticate("wrong", "pass", factory));
		assertNotEquals(true, Authenticator.authenticate("user", "passssssssssssssss", factory));
		assertNotEquals(true, Authenticator.authenticate("", "", factory));

		
	}

}
