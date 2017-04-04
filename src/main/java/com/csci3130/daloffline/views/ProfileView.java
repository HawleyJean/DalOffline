package com.csci3130.daloffline.views;

import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import com.csci3130.daloffline.domain.*;
import com.csci3130.daloffline.DalOfflineUI;
import com.csci3130.daloffline.domain.User;
import com.vaadin.navigator.*;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.server.ExternalResource;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.GridLayout;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Image;
import com.vaadin.ui.Label;
import com.vaadin.ui.Notification;
import com.vaadin.ui.Panel;
import com.vaadin.ui.TabSheet;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.v7.ui.Calendar;
import com.vaadin.v7.ui.components.calendar.event.BasicEvent;
import com.vaadin.v7.ui.components.calendar.handler.*;
import com.vaadin.shared.ui.label.ContentMode;

/**
 * User profile view. Shows the user their own profile, and allows them to view their current schedule.
 * 
 * @author Connor Foran
 * @author Brendan Chan
 */

public class ProfileView extends VerticalLayout implements View {
	//private String currentUsername;
	private Calendar schedule;
	private HorizontalLayout profile = new HorizontalLayout(); //Profile tab
	private TabSheet tabsheet = new TabSheet();
	/**
	 * Initializes and builds the profile page
	 * 
	 * @param None
	 * @return Nothing
	 */
    public ProfileView(DalOfflineUI ui)
    {
    	//Get the current user
    	EntityManager em = DalOfflineUI.factory.createEntityManager();
    	em.getTransaction().begin();
    	//currentUsername = (String) ((User) ui.getSession().getAttribute("user")).getUsername();
        User user = ((User) ui.getSession().getAttribute("user"));
        em.getTransaction().commit();
        em.close();
     
        //Build UI
        setSizeFull();
        VerticalLayout container = new VerticalLayout();
        Panel border = new Panel();
        
        Button backButton = new Button("Go Back"); //Button to go back to the main menu
        backButton.addClickListener(e -> getUI().getNavigator().navigateTo(DalOfflineUI.MAINVIEW));

        profile.setSizeFull();
        
        //Panel with three buttons inside it
        Panel actions = new Panel("Actions");
        Button button1 = new Button("View your course history");
        button1.setStyleName("v-button-borderless");
        //Other unnecessary buttons atm
/*        Button button2 = new Button("Button2");
        button2.setStyleName("v-button-borderless");
        Button button3 = new Button("Button3");
        button3.setStyleName("v-button-borderless");*/
        VerticalLayout content = new VerticalLayout();
        content.setSizeFull();
        content.addComponents(button1/*, button2, button3*/);
        actions.setContent(content);
        
        //Add functionality for course history button
        button1.addClickListener(e -> getUI().getNavigator().navigateTo(DalOfflineUI.COMPLETEDCOURSES));
        
        //User information text in center part of profile
        Label profileInfo = new Label("<b>Name: </b>"+user.getFullName()+"<br><b>Banner ID: </b>"+user.getBannerNumber()+"<br><b>Major: </b>"+user.getMajor());
        profileInfo.setContentMode(ContentMode.HTML);
        //Image on left part of profile
        Image image = new Image();
        image.setSource(new ExternalResource("http://demo.vaadin.com/sampler/VAADIN/themes/runo/icons/64/document.png"));
        
        //Build profile tab
        profile.addComponents(image, profileInfo, actions);
        profile.setComponentAlignment(image, Alignment.TOP_CENTER);
        profile.setComponentAlignment(profileInfo, Alignment.TOP_CENTER);
        profile.setComponentAlignment(actions, Alignment.TOP_CENTER);
        actions.setWidth("50%");
        profile.setMargin(true);
        tabsheet.addTab(profile, "Profile");
        
        //Create schedule
        schedule = new Calendar();
        tabsheet.addTab(schedule, "Schedule");
        schedule.setHandler((BasicEventMoveHandler)null);
        schedule.setHandler((BasicEventResizeHandler)null);
        schedule.setHandler((BasicDateClickHandler)null);
        schedule.setSizeFull();
        schedule.setFirstVisibleHourOfDay(7);
        schedule.setLastVisibleHourOfDay(18);

        //Build page
        container.addComponents(backButton, tabsheet);
        tabsheet.setSizeFull();
        container.setComponentAlignment(backButton, Alignment.TOP_LEFT);
        container.setComponentAlignment(tabsheet, Alignment.MIDDLE_CENTER);
        container.setWidth("100%");
        backButton.setHeight(35, UNITS_PIXELS);
        tabsheet.setHeight((int)(UI.getCurrent().getPage().getBrowserWindowHeight()*0.85), UNITS_PIXELS);
        border.setContent(container);
        
        addComponent(border);
        border.setWidth("80%");
        border.setHeight("90%");
        setComponentAlignment(border, Alignment.MIDDLE_CENTER);
    }


    @Override
    public void enter(ViewChangeEvent event){
    	//Get the current user
    	EntityManager em = DalOfflineUI.factory.createEntityManager();
    	em.getTransaction().begin();
    	User user = (User)getUI().getSession().getAttribute("user");
        em.getTransaction().commit();
        em.close();
        
        tabsheet.removeAllComponents();
        tabsheet.addTab(profile, "Profile");
        schedule = new Calendar();
        tabsheet.addTab(schedule, "Schedule");
        schedule.setHandler((BasicEventMoveHandler)null);
        schedule.setHandler((BasicEventResizeHandler)null);
        schedule.setHandler((BasicDateClickHandler)null);
        schedule.setSizeFull();
        schedule.setFirstVisibleHourOfDay(7);
        schedule.setLastVisibleHourOfDay(18);
        List<Section> sections;
    	//Populate schedule with the user's sections
        if(!user.getClass().getSimpleName().equals("Faculty"))
        	sections = user.getEnrolledSections(); //Get the user's current sections
        else{
        	sections = ((Faculty)user).getteachingList();
        }
        for(Section sec :sections)
        {
          //Get course code and course name from section's associated course
          String ccode = sec.getCourse().getCourseCode();
          String cname = sec.getCourse().getCourseName();
          //Get startTimes and endTimes from section
          ArrayList<GregorianCalendar> startTimes = sec.getStartTimes();
          ArrayList<GregorianCalendar> endTimes = sec.getEndTimes();
          //Add each pair of start and end times to the schedule
          for(int i=0; i<startTimes.size(); i++)
              schedule.addEvent(new BasicEvent(ccode, cname, startTimes.get(i).getTime(), endTimes.get(i).getTime()));
        }
    }
}