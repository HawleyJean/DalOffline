package com.csci3130.daloffline.views;

import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import com.csci3130.daloffline.DalOfflineUI;
import com.csci3130.daloffline.domain.Course;
import com.csci3130.daloffline.domain.Section;
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
 */

public class ProfileView extends VerticalLayout implements View {
	/**
	 * Initializes and builds the profile page
	 * 
	 * @param None
	 * @return Nothing
	 */
    public ProfileView(DalOfflineUI ui) {
    	EntityManager em = DalOfflineUI.factory.createEntityManager();
    	em.getTransaction().begin();
    	Query query = em.createQuery("SELECT s FROM Section s");
    	List<Section> sections = query.getResultList();
    	em.getTransaction().commit();
    	em.close();
        setSizeFull();
        
        VerticalLayout container = new VerticalLayout();
        Panel border = new Panel();
        
        TabSheet tabsheet = new TabSheet(); //Tabs
        
        Button backButton = new Button("Go Back"); //Button to go back to the main menu
        backButton.addClickListener(e -> getUI().getNavigator().navigateTo("main"));
        
        HorizontalLayout profile = new HorizontalLayout(); //Profile tab
        profile.setSizeFull();
        
        //Panel with three buttons inside it
        Panel actions = new Panel("Actions");
        Button button1 = new Button("Button1");
        button1.setStyleName("v-button-borderless");
        Button button2 = new Button("Button2");
        button2.setStyleName("v-button-borderless");
        Button button3 = new Button("Button3");
        button3.setStyleName("v-button-borderless");
        VerticalLayout content = new VerticalLayout();
        content.setSizeFull();
        content.addComponents(button1, button2, button3);
        actions.setContent(content);
        
        //Text in center part of profile
        Label profileInfo = new Label("<b>Name:</b> Jimmy McStudent<br><b>Banner ID:</b> B00XXXXXX<br><b>Major:</b> Computer Science");
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
        Calendar schedule = new Calendar();
        tabsheet.addTab(schedule, "Schedule");
        schedule.setHandler((BasicEventMoveHandler)null);
        schedule.setHandler((BasicEventResizeHandler)null);
        schedule.setHandler((BasicDateClickHandler)null);
        schedule.setSizeFull();
        schedule.setFirstVisibleHourOfDay(7);
        schedule.setLastVisibleHourOfDay(18);
        //populate schedule with items from database
        	for(Section sec :sections){
         	   String ccode = sec.getCourse().getCourseCode();
         		String cname = sec.getCourse().getCourseName();
             	ArrayList<GregorianCalendar> startTimes = sec.getStartTimes();
             	ArrayList<GregorianCalendar> endTimes = sec.getEndTimes();
             	for(int i=0; i<startTimes.size(); i++){
             		schedule.addEvent(new BasicEvent(ccode, cname, startTimes.get(i).getTime(), endTimes.get(i).getTime()));
             	}
             }
    
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
    public void enter(ViewChangeEvent event) {}
}