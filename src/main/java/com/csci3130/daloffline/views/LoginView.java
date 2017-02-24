package com.csci3130.daloffline.views;

import com.vaadin.navigator.*;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.Label;
import com.vaadin.ui.Notification;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;

//Login Screen
public class LoginView extends VerticalLayout implements View {
    public LoginView() {
        setSizeFull();

        VerticalLayout content = new VerticalLayout();
        Label title = new Label("Welcome to DalOffline!");
        TextField username = new TextField("Username:"); //The string here sets a title for the field (text above it)
        TextField password = new TextField("Password:");
        Button button = new Button("Log In");
        button.addClickListener(e -> getUI().getNavigator().navigateTo("main"));
        
        
        content.addComponents(title, username, password, button);
        content.setComponentAlignment(username, Alignment.MIDDLE_CENTER);
        content.setComponentAlignment(password, Alignment.MIDDLE_CENTER);
        content.setComponentAlignment(button, Alignment.MIDDLE_CENTER);
        content.setComponentAlignment(title, Alignment.MIDDLE_CENTER);
        addComponents(content);
        setComponentAlignment(content, Alignment.MIDDLE_CENTER);
    }


    @Override
    public void enter(ViewChangeEvent event) {
        Notification.show("hi");
    }
}