package com.csci3130.daloffline.views;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import com.csci3130.daloffline.CourseInfo;
import com.csci3130.daloffline.DalOfflineUI;
//import com.csci3130.daloffline.courses.Lab;
//import com.csci3130.daloffline.courses.Lecture;
//import com.csci3130.daloffline.courses.*;
import com.csci3130.daloffline.domain.Course;
import com.vaadin.navigator.*;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Notification;
import com.vaadin.ui.Panel;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.v7.data.Item;
import com.vaadin.v7.data.util.BeanItemContainer;
import com.vaadin.v7.data.util.IndexedContainer;
import com.vaadin.v7.event.ItemClickEvent;
import com.vaadin.v7.ui.Grid;
import com.vaadin.v7.ui.TextField;

/**
 * Course list: Displays a table to the user that lists all of the courses currently offered by the university.
 * Allows information about the courses to be viewed, and will allow the user to enroll in those courses.
 * 
 * @author Connor Foran
 */

public class CourseListView extends VerticalLayout implements View {
	Panel border = new Panel();
    TextField filter = new TextField();
    public Grid courseList = new Grid();
    Button backButton = new Button("Go Back");
    public CourseInfo courseInfo = new CourseInfo();
    
	/**
	 * Initializes the view
	 * 
	 * @param None
	 * @return Nothing
	 */
    public CourseListView() {
        backButton.addClickListener(e -> getUI().getNavigator().navigateTo("main"));

        filter.setInputPrompt("Type something here and imagine that filtering was implemented...");
        //filter.addTextChangeListener(e -> refreshList(e.getText()));
        
        courseList.addSelectionListener(e -> viewCourse((Course)courseList.getSelectedRow()));
        
        refreshList();
        buildLayout();
    }
    
	/**
	 * Builds part of the layout
	 * 
	 * @param None
	 * @return Nothing
	 */
    private void buildLayout() {
        HorizontalLayout actions = new HorizontalLayout(backButton, filter);
        actions.setWidth("100%");
        filter.setWidth("100%");
        actions.setExpandRatio(filter, 1);

        VerticalLayout left = new VerticalLayout(actions, courseList);
        left.setSizeFull();
        courseList.setSizeFull();
        left.setExpandRatio(courseList, 1);

        HorizontalLayout mainLayout = new HorizontalLayout(left, courseInfo);
        mainLayout.setSizeFull();
        mainLayout.setExpandRatio(left, 1);

        // Split and allow resizing
        border.setContent(mainLayout);
        addComponent(border);
        border.setWidth("80%");
        border.setHeight("90%");
        setComponentAlignment(border, Alignment.MIDDLE_CENTER);
        setSizeFull();
    }
    
	/**
	 * Display a course's information on the side panel and show it
	 * 
	 * @param Course
	 * @return Nothing
	 */
    private void viewCourse(Course course)
    {
    	if(course == null)
    		return;
    	courseInfo.setCourse(course);
    	courseInfo.setVisible(true);
    }
    
	/**
	 * Refresh/fill the table
	 * 
	 * @param None
	 * @return Nothing
	 */
    @SuppressWarnings("unchecked") //Don't worry about it
	void refreshList()
    {
    	EntityManager em = DalOfflineUI.factory.createEntityManager();
    	em.getTransaction().begin();
    	Query query = em.createQuery("SELECT c FROM COURSES c");
    	List<Course> courses = query.getResultList();
    	em.getTransaction().commit();
    	em.close();
    	
    	//Probably not the best way to do this, but it works for now, and theres nothing reeeeally wrong with it
    	IndexedContainer container = new IndexedContainer();
    	container.addContainerProperty("Course ID", Long.class, "");
    	container.addContainerProperty("Course Name", String.class, "");
    	container.addContainerProperty("Course Code", String.class, "");
    	container.addContainerProperty("Faculty", String.class, "");
    	container.addContainerProperty("Instructor", String.class, "");
    	
    	for (Course course : courses){
    		Item item = container.addItem(course);
    		item.getItemProperty("Course ID").setValue(course.getID());
    		item.getItemProperty("Course Name").setValue(course.getCourseName());
            item.getItemProperty("Course Code").setValue(course.getCourseCode());
            item.getItemProperty("Faculty").setValue(course.getFaculty());
            item.getItemProperty("Instructor").setValue(course.getInstructorName());
    	}
    	
    	courseList.setContainerDataSource(container);
    	courseInfo.setVisible(false);
    }

    //private void refreshList(String stringFilter) {} //Filtering coming ""soon""

    @Override
    public void enter(ViewChangeEvent event) {}
}