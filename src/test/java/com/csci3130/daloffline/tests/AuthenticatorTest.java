package com.csci3130.daloffline.tests;

import static org.junit.Assert.*;

import org.junit.Test;

import com.csci3130.daloffline.DalOfflineUI;
import com.csci3130.daloffline.authentication.*;
import com.csci3130.daloffline.initialization.DatabaseInitializer;

public class AuthenticatorTest {

	@Test
	public void authenticateTest() {
		
		String persistenceUnitName = "daloffline_db_testing";
		
	    DatabaseInitializer.generateUsers(persistenceUnitName);
		
		assertEquals(true, Authenticator.authenticate("user", "pass"));
		
		assertNotEquals(true, Authenticator.authenticate("wrong", "pass"));
		
		assertNotEquals(true, Authenticator.authenticate("user", "passssssssssssssss"));
		
		assertNotEquals(true, Authenticator.authenticate("", ""));
		
		DatabaseInitializer.clearUsers(persistenceUnitName);
		
	}

}
