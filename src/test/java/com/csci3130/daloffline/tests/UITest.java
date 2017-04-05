package com.csci3130.daloffline.tests;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.jetty.jetty.Server;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.phantomjs.PhantomJSDriver;

import com.vaadin.testbench.TestBenchTestCase;
import com.vaadin.testbench.elements.ButtonElement;
import com.vaadin.testbench.elements.LabelElement;
import com.vaadin.testbench.elements.PasswordFieldElement;
import com.vaadin.testbench.elements.TextFieldElement;

/**
 * Tests that all the buttons work and the display shows the expected results.
 * Server is required 
 * 
 * @author Andrew Muise
 */

public class UITest extends TestBenchTestCase {
	
//	public static Server server;
	
//	@BeforeClass
//	public static void startJetty() throws Exception {
////		server.start();
//	}
	
	@Before
	public void setUp() throws Exception {
		setDriver(new PhantomJSDriver());
		//use chrome driver for local testing
//		setDriver(new ChromeDriver());
	}
	
	String user = "student";
	String pass = "pass";
	@Test
	public void loginTest() {
		
		//is the server running
//		assertEquals(true, server.isStarted());
		getDriver().get("http://localhost:8080/");
		
		//check if we've loaded the correct page
//		assertEquals("Welcome to DalOffline!", getLoginScreenText());
		
		//log in
		setUsername(user);
		setPassword(pass);
		login();
//		assertEquals("Hello, student, you are logged in as a Student", getLoggedInText());
		
		//check list of classes
		viewCourseList();
		//there is nothing really testable here
		//TODO when search is implemented, the search can be checked here
		goBack();
//		assertEquals("Hello, student, you are logged in as a Student", getLoggedInText());
		
		//view profile
		viewProfileMain();
		//there is nothing that can really be checked here either, as it is hardcoded.
		//just checking if we can go to/leave the page
		//TODO check about viewing course information
//		viewProfileSchedule();
		//TODO when the profile is attached to a user check against database if information is right
//		viewProfileUser();
		goBack();
		assertEquals("Hello, student, you are logged in as a Student", getLoggedInText());
		
	}
	
	
	@After
	public void tearDown() throws Exception {
		getDriver().quit();
	}
	
//	@AfterClass
//	public static void stopJetty() throws Exception {
////		server.stop();
//	}
	
	private void viewProfileUser() {
		$(ButtonElement.class).caption("Profile").first().click();
	}
	
	private void viewProfileSchedule() {
		$(ButtonElement.class).caption("Schedule").first().click();
	}
	
	private void viewProfileMain() {
		$(ButtonElement.class).caption("View Your Profile And Schedule").first().click();
	}
	
	private void goBack() {
		$(ButtonElement.class).caption("Go Back").first().click();
	}
	
	private void viewCourseList() {
		$(ButtonElement.class).caption("View All Courses").first().click();
	}
	
	private String getLoggedInText() {
		return $(LabelElement.class).first().getText();
	}
	
	private void login() {
		$(ButtonElement.class).caption("Log In").first().click();
	}
	
	private void setPassword(String password) {
		$(PasswordFieldElement.class).caption("Password:").first().setValue(password);
	}
	
	private void setUsername(String username) {
		$(TextFieldElement.class).caption("Username:").first().setValue(username);
	}
	
	private String getLoginScreenText() {
		return $(LabelElement.class).first().getText();
	}	
	
}
