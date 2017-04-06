package com.csci3130.daloffline.views;
import com.csci3130.daloffline.domain.Section;
import com.csci3130.daloffline.domain.Student;
import com.csci3130.daloffline.views.designs.MainDesign;

import java.util.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.List;

import javax.persistence.EntityManager;

import com.csci3130.daloffline.DalOfflineUI;
import com.vaadin.navigator.*;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.Notification;
import com.vaadin.ui.Panel;
import com.vaadin.ui.TextArea;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.v7.ui.Calendar;
import com.vaadin.v7.ui.components.calendar.event.BasicEvent;
import com.vaadin.v7.ui.components.calendar.handler.BasicDateClickHandler;
import com.vaadin.v7.ui.components.calendar.handler.BasicEventMoveHandler;
import com.vaadin.v7.ui.components.calendar.handler.BasicEventResizeHandler;

/**
 * Main menu view. Displays buttons to the user that allows them to navigate to the other interfaces.
 * 
 * @author Connor Foran
 * @author Jesse MacLeod
 */

public class MainView extends MainDesign implements View {
	/**
	 * Initializes and builds the main menu page
	 * 
	 * @param None
	 * @return Nothing
	 */
	private String username;
	
	public MainView(DalOfflineUI ui) {
    	username = (String)((Student) ui.getSession().getAttribute("student")).getUsername();
    	String role = ((Student) ui.getSession().getAttribute("student")).getClass().getSimpleName();
		
        
		/////////////////// Buttons ///////////////////

		//Button profileButton = new Button("View Your Profile And Schedule"); 	
        profileButton.addClickListener(e -> getUI().getNavigator().navigateTo(DalOfflineUI.USERPROFILE)); //Specify a view for this button to direct you to
		
		//Button courseListButton = new Button("View All Courses"); 
        courseListButton.addClickListener(e -> getUI().getNavigator().navigateTo(DalOfflineUI.COURSELIST));	
        
        //Button logoutButton = new Button("Logout");
        logoutButton.addClickListener(e -> logout());
		
		// Button studentListButton = new Button("View Student List");	
	    // studentListButton.addClickListener(e-> getUI().getNavigator().navigateTo(DalOfflineUI.STUDENTLIST));
		
		/////////////////// Schedule ///////////////////	

        schedule = new Calendar();
        schedule.setHandler((BasicEventMoveHandler)null);
        schedule.setHandler((BasicEventResizeHandler)null);
        schedule.setHandler((BasicDateClickHandler)null);
        schedule.setSizeFull();
        schedule.setFirstVisibleHourOfDay(7);
        schedule.setLastVisibleHourOfDay(18);
        
        /////////////////// Date ///////////////////	
        DateFormat dateFormat = new SimpleDateFormat("EEEE d MMMM YYYY");
        Date currentDate = new Date();
        infoArea.setValue(""+dateFormat.format(currentDate)+"\n\nHello, "+username +", you are logged in as a " +role);


        
    }

	public void logout(){
		getUI().getNavigator().navigateTo("");
		//DalOfflineUI.factory.close();
		getUI().getSession().close();

  }

    @Override
    public void enter(ViewChangeListener.ViewChangeEvent event) {
    	

    	Student user = (Student)getUI().getSession().getAttribute("student");

    	System.out.println(user.getFullName());
    	System.out.println(user.getUsername());
    	
    	contentSplitLeftLayout.removeComponent(schedule);
    	
        schedule = new Calendar();
        schedule.setHandler((BasicEventMoveHandler)null);
        schedule.setHandler((BasicEventResizeHandler)null);
        schedule.setHandler((BasicDateClickHandler)null);
        schedule.setSizeFull();
        schedule.setFirstVisibleHourOfDay(7);
        schedule.setLastVisibleHourOfDay(18);
        List<Section> sections = user.getEnrolledSections();

        for(Section sec : sections)
        {
        	
        	
        	//Get course code and course name from section's associated course
	        String ccode = sec.getCourse().getCourseCode();
	        String cname = sec.getCourse().getCourseName();
	        
	        System.out.println("ccode: "+ccode+", cname: "+cname);
	        
	        //Get startTimes and endTimes from section
	        ArrayList<GregorianCalendar> startTimes = sec.getStartTimes();
	        ArrayList<GregorianCalendar> endTimes = sec.getEndTimes();
	         
	        //Add each pair of start and end times to the schedule
	        for(int i=0; i<startTimes.size(); i++) {
	        	System.out.println("adding event "+i);
	        	schedule.addEvent(new BasicEvent(ccode, cname, startTimes.get(i).getTime(), endTimes.get(i).getTime()));
	        }
        }
        contentSplitLeftLayout.addComponent(schedule);
        
    }

}

