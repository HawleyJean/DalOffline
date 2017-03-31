package com.csci3130.daloffline.views;
import com.csci3130.daloffline.domain.*;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import com.csci3130.daloffline.CourseInfo;
import com.csci3130.daloffline.DalOfflineUI;
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
 * StudentListView is meant to display return of students per section based on a selected course
 * @author Connor Foran (this is mainly code he wrote for CourseListView)
 * @author Andrew Muise, Eric Nguyen
 *
 */

public class StudentListView extends VerticalLayout implements View {
	Panel border = new Panel();
    TextField filter = new TextField();
    public Grid students = new Grid();
    HorizontalLayout mainLayout;
    Button backButton = new Button("Go Back");
    //public CourseInfo courseInfo = new CourseInfo();
        
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

        VerticalLayout left = new VerticalLayout(actions, students);
        left.setSizeFull();
        students.setSizeFull();
        left.setExpandRatio(students, 1);

        mainLayout = new HorizontalLayout(left);
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
    	Query query = em.createQuery("SELECT s FROM USER s");
    	List<User> us = query.getResultList();
    	em.getTransaction().commit();
    	em.close();
    	
    	//Probably not the best way to do this, but it works for now, and theres nothing reeeeally wrong with it
    	IndexedContainer container = new IndexedContainer();
    	container.addContainerProperty("Course ID", Long.class, "");
    	container.addContainerProperty("Course Name", String.class, "");
    	container.addContainerProperty("Course Code", String.class, "");
    	container.addContainerProperty("Faculty", String.class, "");
    	container.addContainerProperty("Instructor", String.class, "");
    	
    	for (User st : us){
    		Item item = container.addItem(st);
    		item.getItemProperty("Name").setValue(st.getFullName());
    		item.getItemProperty("Department").setValue(st.getMajor());
    	}
    	
    	students.setContainerDataSource(container);
    	//courseInfo.setVisible(false);
    }
    
    /**
	 * Initializes the view
	 * 
	 * @param None
	 * @return Nothing
	 */
    public StudentListView(DalOfflineUI ui) {
        backButton.addClickListener(e -> getUI().getNavigator().navigateTo(DalOfflineUI.MAINVIEW));

        filter.setInputPrompt("Enter search...");
        //filter.addTextChangeListener(e -> refreshList(e.getText()));
        
        //courseList.addSelectionListener(e -> viewStudents((User)courseList.getSelectedRow()));
        
        //refreshList();
        buildLayout();
    }
	/**
	 * Something to do with viewing students
	 * 
	 * @param User
	 * @return Nothing
	 */
    private void viewStudents(User u)
    {
    	if(u == null)
    		return;
    	
    	mainLayout.removeComponent(students);
    	//courseInfo = new CourseInfo();
    	mainLayout.addComponent(students);
    	
    } 
    
    public void enter(ViewChangeEvent event) {}
}
