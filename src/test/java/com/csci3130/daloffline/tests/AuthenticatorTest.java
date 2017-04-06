package com.csci3130.daloffline.tests;

import static org.junit.Assert.*;

import java.util.HashMap;
import java.util.Map;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.junit.Test;

import com.csci3130.daloffline.authentication.*;
import com.csci3130.daloffline.initialization.DatabaseInitializer;

public class AuthenticatorTest {

	@Test
	public void authenticateTest() {

		EntityManagerFactory factory;
		String env = System.getenv("JDBC_DATABASE_URL");
    	if(env != null)
    	{
    		Map<String, Object> configOverrides = new HashMap<String, Object>();
    		configOverrides.put("hibernate.connection.url", env);
    		factory = Persistence.createEntityManagerFactory("postgres", configOverrides);
    	}
    	else
    		factory = Persistence.createEntityManagerFactory("local");

		//this will have to be changed later when we are no longer "generating users" but creating users
	    DatabaseInitializer.generateUsers(factory);
		assertEquals(true, Authenticator.authenticate("user", "pass", factory));
		assertNotEquals(true, Authenticator.authenticate("wrong", "pass", factory));
		assertNotEquals(true, Authenticator.authenticate("user", "wrong", factory));
		assertNotEquals(true, Authenticator.authenticate("", "", factory));
		
		assertEquals(true, true);

	}

}
