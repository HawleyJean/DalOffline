package com.csci3130.daloffline;

import javax.servlet.annotation.WebServlet;

import com.csci3130.daloffline.Assignment2Thing.*;
import com.csci3130.daloffline.views.*;

import com.vaadin.annotations.Theme;
import com.vaadin.annotations.Title;
import com.vaadin.annotations.VaadinServletConfiguration;
import com.vaadin.annotations.Widgetset;
import com.vaadin.navigator.*;
import com.vaadin.server.VaadinRequest;
import com.vaadin.server.VaadinServlet;
import com.vaadin.ui.UI;


@Title("Dal Offline")
@Theme("valo")
@Widgetset("com.vaadin.v7.Vaadin7WidgetSet")

//UI Navigator; displays views
public class NavigatorUI extends UI {
	public Navigator navigator;
    protected static final String MAINVIEW = "main";
    protected static final String STUDENTLIST = "student_list";
    protected static final String USERPROFILE = "profile";

    @Override
    protected void init(VaadinRequest request) {
    	// Create a navigator to control the views
		navigator = new Navigator(this, this);

        // Create and register the views
        navigator.addView("", new LoginView());
        navigator.addView(STUDENTLIST, new StudentListUI());
        navigator.addView(MAINVIEW, new MainView());
        navigator.addView(USERPROFILE, new ProfileView());
    }
    
    @WebServlet(urlPatterns = "/*")
    @VaadinServletConfiguration(ui = NavigatorUI.class, productionMode = false)
    public static class MyUIServlet extends VaadinServlet{}

}