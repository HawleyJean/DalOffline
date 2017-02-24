package com.csci3130.daloffline.views;

import com.vaadin.navigator.*;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.Notification;
import com.vaadin.ui.VerticalLayout;

//Main Menu
public class MainView extends VerticalLayout implements View {
    public MainView() {
        setSizeFull();

        Button profileButton = new Button("User Profile"); //A button
        profileButton.addClickListener(e -> getUI().getNavigator().navigateTo("profile")); //Specify a view for this button to direct you to
        
        Button studentListButton = new Button("Open that assignment 2 thing");
        studentListButton.addClickListener(e -> getUI().getNavigator().navigateTo("student_list"));
        
        addComponents(profileButton, studentListButton); //Add buttons to the view
        setComponentAlignment(profileButton, Alignment.MIDDLE_CENTER); //Set alignments
        setComponentAlignment(studentListButton, Alignment.MIDDLE_CENTER);
    }


    @Override
    public void enter(ViewChangeEvent event) {
        Notification.show("YOU ARE LOGGED IN!");
    }
}