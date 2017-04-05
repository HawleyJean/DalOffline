package com.csci3130.daloffline;

import org.hibernate.*;
import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.net.URISyntaxException;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;

import com.csci3130.daloffline.domain.User;
import com.csci3130.daloffline.initialization.DatabaseInitializer;
import com.csci3130.daloffline.views.*;
import com.vaadin.annotations.PreserveOnRefresh;
import com.vaadin.annotations.Theme;
import com.vaadin.annotations.Title;
import com.vaadin.annotations.VaadinServletConfiguration;
import com.vaadin.annotations.Widgetset;
import com.vaadin.navigator.*;
import com.vaadin.server.ServiceException;
import com.vaadin.server.SessionDestroyEvent;
import com.vaadin.server.SessionDestroyListener;
import com.vaadin.server.SessionInitEvent;
import com.vaadin.server.SessionInitListener;
import com.vaadin.server.VaadinRequest;
import com.vaadin.server.VaadinServlet;
import com.vaadin.ui.UI;

/**
 * UI Navigation class. Controls the current view shown to the user.
 * 
 * @author Connor Foran
 * @author Jesse MacLeod
 */

@Title("Dal Offline")
@Theme("valo")
@Widgetset("com.vaadin.v7.Vaadin7WidgetSet")
@PreserveOnRefresh
public class DalOfflineUI extends UI {
	
	// the name of the database as defined in persistance.xml
	public static final String PERSISTENCE_UNIT = "daloffline_db";
	
	public Navigator navigator;
	
	// creating this once
	public static EntityManagerFactory factory;
	
	// View Names
	public static final String MAINVIEW = "main";
    public static final String USERPROFILE = "profile";
    public static final String STUDENTLIST = "studentlist";
    public static final String COURSELIST = "courselist";
    public static final String COMPLETEDCOURSES = "coursehistory";
    public static final String FACULTYMAINVIEW = "fmain";
    public static final String FACULTYPROFILE = "fprofile";
    public static final String COURSESTEACHING = "fteaching"; 
    private User user;
    
    
    
    private static Connection getConnection() throws URISyntaxException, SQLException {
        URI dbUri = new URI(System.getenv("DATABASE_URL"));

        String username = dbUri.getUserInfo().split(":")[0];
        String password = dbUri.getUserInfo().split(":")[1];
        String dbUrl = "jdbc:postgresql://" + dbUri.getHost() + ':' + dbUri.getPort() + dbUri.getPath();

        return DriverManager.getConnection(dbUrl, username, password);
    }
    
	/**
	 * This function changes the view based on a VaadinRequest sent by some action
	 * 
	 * @param VaadinRequest
	 * @return Nothing
	 */
    @Override
    protected void init(VaadinRequest request) {
    	String env = System.getenv("JDBC_DATABASE_URL");
    	
    	if(env != null)
    	{
    		Map<String, Object> configOverrides = new HashMap<String, Object>();
    		configOverrides.put("hibernate.connection.url", env);
    		factory = Persistence.createEntityManagerFactory("postgres", configOverrides);
    	}
    	else
    		factory = Persistence.createEntityManagerFactory("local");
		
    	
    	DatabaseInitializer.generateUsers(factory);
    	DatabaseInitializer.generateCourses(factory);
    	// Create a navigator to control the views
		navigator = new Navigator(this, this);

        // Create and register the views
		navigator.addView("", new LoginView(DalOfflineUI.this));
		//CourseInfo cs = new CourseInfo();
        
    }
    public void setUser(User user){
    	this.user = user;
    }
    public User getUser(){
    	return this.user;
    }
  
    @WebServlet(urlPatterns = "/*")
    @VaadinServletConfiguration(ui = DalOfflineUI.class, productionMode = false)
    public static class MyUIServlet extends VaadinServlet{}
}