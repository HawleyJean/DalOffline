package com.csci3130.daloffline.views;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;

import com.csci3130.daloffline.authentication.Authenticator;
import com.vaadin.navigator.*;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.Label;
import com.vaadin.ui.Notification;
import com.vaadin.ui.PasswordField;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;

//Login Screen
public class LoginView extends VerticalLayout implements View {
   
	
	public LoginView() {
        setSizeFull();

        //Testing Placeholder
        Authenticator.initializePlaceHolderData();
        
        VerticalLayout content = new VerticalLayout();
        Label title = new Label("Welcome to DalOffline!");
        TextField username = new TextField("Username:"); //The string here sets a title for the field (text above it)
        PasswordField password = new PasswordField("Password:");
        Button button = new Button("Log In");
                     
        
        button.addClickListener(e -> login(username.getValue(), password.getValue()));

        content.addComponents(title, username, password, button);
        content.setComponentAlignment(username, Alignment.MIDDLE_CENTER);
        content.setComponentAlignment(password, Alignment.MIDDLE_CENTER);
        content.setComponentAlignment(button, Alignment.MIDDLE_CENTER);
        content.setComponentAlignment(title, Alignment.MIDDLE_CENTER);
        addComponents(content);
        setComponentAlignment(content, Alignment.MIDDLE_CENTER);
    }

	private void login(String username, String password) {
				
		//System.out.println("username: "+username+"\n"+"password: "+password);
		//System.out.println(auth.authenticator(username, password));
		
		if(Authenticator.authenticate(username, password)) {
			getUI().getNavigator().navigateTo("main");
		}
		else {
			 Notification.show("Invalid password or username. (For testing purposes use \"user\" and \"pass\")");
		}
		
	}

    @Override
    public void enter(ViewChangeEvent event) {
        Notification.show("hi");
    }
}