package com.csci3130.daloffline.views;

import com.vaadin.navigator.*;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.Label;
import com.vaadin.ui.Notification;
import com.vaadin.ui.VerticalLayout;

//Main Menu
public class ProfileView extends VerticalLayout implements View {
    public ProfileView() {
        setSizeFull();

        Label text = new Label(":^)");
        Button backButton = new Button("Go Back");
        backButton.addClickListener(e -> getUI().getNavigator().navigateTo("main"));
        
        addComponents(text, backButton);
        setComponentAlignment(text, Alignment.MIDDLE_CENTER);
        setComponentAlignment(backButton, Alignment.MIDDLE_CENTER);
    }


    @Override
    public void enter(ViewChangeEvent event) {
        Notification.show("Work in progress.");
    }
}