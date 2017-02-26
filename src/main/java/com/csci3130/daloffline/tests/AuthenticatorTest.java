package com.csci3130.daloffline.tests;

import static org.junit.Assert.*;

import org.junit.Test;
import com.csci3130.daloffline.authentication.*;

public class AuthenticatorTest {

	@Test
	public void authenticateTest() {
		
		Authenticator.initializePlaceHolderData();
		
		assertEquals(true, Authenticator.authenticate("user", "pass"));
		
		assertNotEquals(true, Authenticator.authenticate("wrong", "pass"));
		
		assertNotEquals(true, Authenticator.authenticate("user", "passssssssssssssss"));
		
		assertNotEquals(true, Authenticator.authenticate("", ""));
	}

}
