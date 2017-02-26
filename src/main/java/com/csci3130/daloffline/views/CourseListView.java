package com.csci3130.daloffline.views;

import com.csci3130.daloffline.CourseInfo;
import com.csci3130.daloffline.Assignment2Thing.Student;
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

//Main Menu
public class CourseListView extends VerticalLayout implements View {
	Panel border = new Panel();
    TextField filter = new TextField();
    public Grid courseList = new Grid();
    Button backButton = new Button("Go Back");
    public CourseInfo courseInfo = new CourseInfo();
    
    public CourseListView() {
        backButton.addClickListener(e -> getUI().getNavigator().navigateTo("main"));

        filter.setInputPrompt("Filter students...");
        filter.addTextChangeListener(e -> refreshList(e.getText()));

        //courseList.setContainerDataSource(new BeanItemContainer<>(Student.class));
        
        courseList.addItemClickListener(new ItemClickEvent.ItemClickListener() {
            @Override
            public void itemClick(ItemClickEvent itemClickEvent) {
            	courseInfo.setVisible(true);
            }
        });
        
        courseList.addColumn("Course Name");
        courseList.addColumn("Course ID");
        courseList.addColumn("Department");
        courseList.addColumn("Available Slots");
        courseList.setSelectionMode(Grid.SelectionMode.SINGLE);
        
        courseList.addRow("Software Engineering", "CSCI3130", "Computer Science", "40");
        
        refreshList();
        buildLayout();
    }
    
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
    
    void refreshList() {
    	refreshList(filter.getValue());
    }

    private void refreshList(String stringFilter) {
    	//studentList.setContainerDataSource(new BeanItemContainer<>(Student.class, service.findAll(stringFilter)));
    	courseInfo.setVisible(false);
    }

    @Override
    public void enter(ViewChangeEvent event) {
        //Notification.show("YOU ARE LOGGED IN!");
    }
}