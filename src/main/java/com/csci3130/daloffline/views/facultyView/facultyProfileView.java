package com.csci3130.daloffline.views.facultyView;
import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import com.csci3130.daloffline.domain.*;
import com.csci3130.daloffline.DalOfflineUI;
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

import com.vaadin.v7.ui.Grid;

import com.vaadin.ui.VerticalLayout;
import com.vaadin.v7.data.util.BeanItemContainer;
import com.vaadin.v7.ui.Calendar;
import com.vaadin.v7.ui.components.calendar.event.BasicEvent;
import com.vaadin.v7.ui.components.calendar.handler.*;
import com.vaadin.shared.ui.label.ContentMode;

public class facultyProfileView extends VerticalLayout implements View {

	private HorizontalLayout coursesTeachingLayout = new HorizontalLayout();
	private Grid courseGrid = new Grid();
	private HorizontalLayout profile = new HorizontalLayout(); //Profile tab
	private TabSheet tabsheet = new TabSheet();
	private Calendar schedule;
	
	public facultyProfileView(DalOfflineUI ui)
    {
    	//Get the current user
    	EntityManager em = DalOfflineUI.factory.createEntityManager();
    	em.getTransaction().begin();
    	//currentUsername = (String) ((User) ui.getSession().getAttribute("user")).getUsername();
        //User user = ((User) ui.getSession().getAttribute("user"));
        Faculty user = ((Faculty) ui.getSession().getAttribute("faculty"));
    	em.getTransaction().commit();
        em.close();
     
        //Build UI
        setSizeFull();
        VerticalLayout container = new VerticalLayout();
        Panel border = new Panel();
        
        Button backButton = new Button("Go Back"); //Button to go back to the main menu
        backButton.addClickListener(e -> getUI().getNavigator().navigateTo(DalOfflineUI.FACULTYMAINVIEW));

        profile.setSizeFull();
        
        //Panel with three buttons inside it
        Panel actions = new Panel("Actions");
        
        
      
        VerticalLayout content = new VerticalLayout();
        content.setSizeFull();
        content.addComponents(/*, button2, button3*/);
        actions.setContent(content);
        
        //Add functionality for course history button
        
        //User information text in center part of profile
        Label profileInfo = new Label("<b>Name: </b>"+user.getFullName()+"<br><b>Faculty: </b>"+user.getMajor());
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
        tabsheet.addTab(coursesTeachingLayout, "Courses Teaching");
        //Create schedule
        
        //create TeachingList
        List<Section> listOfCourses = user.getteachingList();
        
        
       
        coursesTeachingLayout.addComponent(courseGrid);
        
        //endCreateTeachingList

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
	
	public void enter(ViewChangeEvent event){
    	//Get the current user
		EntityManager em = DalOfflineUI.factory.createEntityManager();
    	em.getTransaction().begin();
    	Faculty user = (Faculty)getUI().getSession().getAttribute("faculty");
        em.getTransaction().commit();
        em.close();
        
        tabsheet.removeAllComponents();
        tabsheet.addTab(profile, "Profile");
        schedule = new Calendar();
        tabsheet.addTab(schedule, "Schedule");
        tabsheet.addTab(coursesTeachingLayout, "Courses Teaching");
        schedule.setHandler((BasicEventMoveHandler)null);
        schedule.setHandler((BasicEventResizeHandler)null);
        schedule.setHandler((BasicDateClickHandler)null);
        schedule.setSizeFull();
        schedule.setFirstVisibleHourOfDay(7);
        schedule.setLastVisibleHourOfDay(18);
        List<Section> sections;
  
        sections = ((Faculty)user).getteachingList();
        
        for(Section sec :sections)
        {
          String ccode = sec.getCourse().getCourseCode();
          String cname = sec.getCourse().getCourseName();
          ArrayList<GregorianCalendar> startTimes = sec.getStartTimes();
          ArrayList<GregorianCalendar> endTimes = sec.getEndTimes();
          for(int i=0; i<startTimes.size(); i++)
              schedule.addEvent(new BasicEvent(ccode, cname, startTimes.get(i).getTime(), endTimes.get(i).getTime()));
        }
    }
}
	

