package com.csci3130.daloffline;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
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
    public static final String COURSELIST = "course_list";
    private User user;
	/**
	 * This function changes the view based on a VaadinRequest sent by some action
	 * 
	 * @param VaadinRequest
	 * @return Nothing
	 */
    @Override
    protected void init(VaadinRequest request) {
    	String databaseUrl = System.getenv("DATABASE_URL");
    	if(databaseUrl != null)
    	{
	    	StringTokenizer st = new StringTokenizer(databaseUrl, ":@/");
	    	String dbVendor = st.nextToken(); //if DATABASE_URL is set
	    	String userName = st.nextToken();
	    	String password = st.nextToken();
	    	String host = st.nextToken();
	    	String port = st.nextToken();
	    	String databaseName = st.nextToken();
	    	String jdbcUrl = String.format("jdbc:postgresql://%s:%s/%s?ssl=true&sslfactory=org.postgresql.ssl.NonValidatingFactory", host, port, databaseName);
	    	Map<String, String> properties = new HashMap<String, String>();
	    	properties.put("javax.persistence.jdbc.url", databaseUrl );
	    	properties.put("javax.persistence.jdbc.user", userName );
	    	properties.put("javax.persistence.jdbc.password", password );
	    	properties.put("javax.persistence.jdbc.driver", "org.postgresql.Driver");
	    	properties.put("hibernate.dialect", "org.hibernate.dialect.PostgreSQLDialect");
	    	factory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT, properties);
    	}
    	else
    		factory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT);
    	
    	//Map<String, Object> configOverrides = new HashMap<String, Object>();
		//configOverrides.put("hibernate.connection.url", System.getenv("JDBC_DATABASE_URL"));
		//factory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT, configOverrides);
		
    	//factory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT);
    	DatabaseInitializer.generateUsers(factory);
    	DatabaseInitializer.generateCourses(factory);
    	
    	// Create a navigator to control the views
		navigator = new Navigator(this, this);

        // Create and register the views
		navigator.addView("", new LoginView(DalOfflineUI.this));
		
        
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