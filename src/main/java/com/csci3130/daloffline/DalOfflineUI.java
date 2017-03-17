package com.csci3130.daloffline;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;

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
    protected static final String MAINVIEW = "main";
    protected static final String STUDENTLIST = "student_list";
    protected static final String USERPROFILE = "profile";
    protected static final String COURSELIST = "course_list";
    private String user ="";

	/**
	 * This function changes the view based on a VaadinRequest sent by some action
	 * 
	 * @param VaadinRequest
	 * @return Nothing
	 */
    @Override
    protected void init(VaadinRequest request) {
    	
    	factory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT);
    	DatabaseInitializer.generateUsers(factory);
    	
    	// Create a navigator to control the views
		navigator = new Navigator(this, this);

        // Create and register the views
		navigator.addView("", new LoginView(DalOfflineUI.this));
		
        
    }

    public void setUser(String user){
    	this.user=user;
    }
    public String getUser(){
    	return this.user;
    }
    @WebServlet(urlPatterns = "/*")
    @VaadinServletConfiguration(ui = DalOfflineUI.class, productionMode = false)
    public static class MyUIServlet extends VaadinServlet{

    }



}