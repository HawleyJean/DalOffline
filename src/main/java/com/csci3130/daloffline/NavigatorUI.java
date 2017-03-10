package com.csci3130.daloffline;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import javax.servlet.annotation.WebServlet;
import com.csci3130.daloffline.views.*;

import com.vaadin.annotations.Theme;
import com.vaadin.annotations.Title;
import com.vaadin.annotations.VaadinServletConfiguration;
import com.vaadin.annotations.Widgetset;
import com.vaadin.navigator.*;
import com.vaadin.server.VaadinRequest;
import com.vaadin.server.VaadinServlet;
import com.vaadin.ui.UI;

/**
 * UI Navigation class. Controls the current view shown to the user.
 * 
 * @author Connor Foran
 */

@Title("Dal Offline")
@Theme("valo")
@Widgetset("com.vaadin.v7.Vaadin7WidgetSet")
public class NavigatorUI extends UI {
	public Navigator navigator;
	
    protected static final String MAINVIEW = "main";
    protected static final String STUDENTLIST = "student_list";
    protected static final String USERPROFILE = "profile";
    protected static final String COURSELIST = "course_list";
    public static final String PERSISTENCE_UNIT = "daloffline_db";

	/**
	 * This function changes the view based on a VaadinRequest sent by some action
	 * 
	 * @param VaadinRequest
	 * @return Nothing
	 */
    @Override
    protected void init(VaadinRequest request) {
    	// Create a navigator to control the views
		navigator = new Navigator(this, this);

        // Create and register the views
		navigator.addView("", new LoginView());
		
        navigator.addView(COURSELIST, new CourseListView());
        navigator.addView(MAINVIEW, new MainView());
        navigator.addView(USERPROFILE, new ProfileView());
    }
    
    @WebServlet(urlPatterns = "/*")
    @VaadinServletConfiguration(ui = NavigatorUI.class, productionMode = false)
    public static class MyUIServlet extends VaadinServlet{}

}