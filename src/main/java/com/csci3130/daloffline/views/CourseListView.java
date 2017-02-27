package com.csci3130.daloffline.views;

import java.util.ArrayList;

import com.csci3130.daloffline.CourseInfo;
import com.csci3130.daloffline.classes.*;

import com.vaadin.navigator.*;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Notification;
import com.vaadin.ui.Panel;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.v7.data.util.BeanItemContainer;
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
    ArrayList<Course> courses = new ArrayList<Course>();
    
	/**
	 * Initializes and builds the course list
	 * 
	 * @param None
	 * @return Nothing
	 */
    public CourseListView() {
        backButton.addClickListener(e -> getUI().getNavigator().navigateTo("main"));

        filter.setInputPrompt("Filter courses...");
        //filter.addTextChangeListener(e -> refreshList(e.getText()));

        Course newCourse = new Course("Software Engineering", "Computer Science", "CSCI3130", "Dr. Ashraf Abusharek");
        Lecture lec = new Lecture("LSC-PSYCHOLOGY P5260", 1234);
        lec.addTime(3, 16, 0, 90);
        lec.addTime(5, 16, 0, 90);
        Lab lab = new Lab("KILLAM 2600", 1234);
        lab.addTime(4, 8, 30, 90);
        newCourse.addLab(lab);
        newCourse.addLecture(lec);
        
        courses.add(newCourse);
        courses.add(new Course("Network Computing", "Computer Science", "CSCI3171", "Dr. Nur Zincir-Heywood"));
        courses.add(new Course("Principles of Programming Languages", "Computer Science", "CSCI3136", "Dr. Nauzer Kalyaniwalla"));
        
        courseList.addSelectionListener(e -> viewCourse((Course) courseList.getSelectedRow()));
        
        refreshList();
        buildLayout();
        courseList.setColumnOrder("name", "code", "department");
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
	 * Refresh the table and hide the side panel
	 * 
	 * @param None
	 * @return Nothing
	 */
    void refreshList() {
    	//refreshList(filter.getValue());
    	courseList.setContainerDataSource(new BeanItemContainer<>(Course.class, courses));
    	courseInfo.setVisible(false);
    }

    //private void refreshList(String stringFilter) {}

    @Override
    public void enter(ViewChangeEvent event) {}
}