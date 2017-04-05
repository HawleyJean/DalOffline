package com.csci3130.daloffline.views;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

import com.csci3130.daloffline.views.facultyView.*;
import com.csci3130.daloffline.DalOfflineUI;
import com.csci3130.daloffline.authentication.Authenticator;
import com.csci3130.daloffline.initialization.DatabaseInitializer;
import com.csci3130.daloffline.domain.Faculty;
import com.csci3130.daloffline.domain.Student;
import com.csci3130.daloffline.domain.User;
import com.vaadin.navigator.*;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.Label;
import com.vaadin.ui.Notification;
import com.vaadin.ui.PasswordField;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;

/**
 * Login screen where the user enters their account name and password in order to access the system.
 * 
 * @author Connor Foran
 * @author Jesse MacLeod
 * @author Alex Gordon
 */

public class LoginView extends VerticalLayout implements View {
   
	private static final long serialVersionUID = 1L;

	/**
	 * Initializes and builds the login page
	 * 
	 * @param None
	 * @return Nothing
	 */
	public LoginView(DalOfflineUI ui) {
        setSizeFull();
        
        VerticalLayout content = new VerticalLayout();
        Label title = new Label("Welcome to DalOffline!");
        TextField username = new TextField("Username:"); //The string here sets a title for the field (text above it)
        PasswordField password = new PasswordField("Password:");
        Button button = new Button("Log In");
               
        button.addClickListener(e -> login(username.getValue(), password.getValue(), ui));

        content.addComponents(title, username, password, button);
        content.setComponentAlignment(username, Alignment.MIDDLE_CENTER);
        content.setComponentAlignment(password, Alignment.MIDDLE_CENTER);
        content.setComponentAlignment(button, Alignment.MIDDLE_CENTER);
        content.setComponentAlignment(title, Alignment.MIDDLE_CENTER);
        addComponents(content);
        setComponentAlignment(content, Alignment.MIDDLE_CENTER);
    }

	/**
	 * Function that attempts to log the user in
	 * 
	 * @param username
	 * @param password
	 * @return Nothing
	 */
	private void login(String username, String password, DalOfflineUI ui) {
		
		if(Authenticator.authenticate(username, password, DalOfflineUI.factory)) {
			EntityManager em = ui.factory.createEntityManager();
			User userpw = em.createQuery("SELECT user FROM USERS user WHERE user.username = :input_user", User.class)
					.setParameter("input_user", username).getSingleResult();
			em.close();
			
			if(userpw.getClass().equals(Student.class))
			{
				System.out.print("Student!\n");
				ui.getSession().setAttribute("student", userpw);
				ui.getSession().getSession().setMaxInactiveInterval(600);
				getUI().getNavigator().addView(DalOfflineUI.COURSELIST, new CourseListView(ui));
		        getUI().getNavigator().addView(DalOfflineUI.MAINVIEW, new MainView(ui));
		        getUI().getNavigator().addView(DalOfflineUI.USERPROFILE, new ProfileView(ui));
		        
				getUI().getNavigator().navigateTo("main");
			}
			else if(userpw.getClass().equals(Faculty.class))
			{
				System.out.print("Faculty!\n");

				ui.getSession().setAttribute("faculty", userpw);
				ui.getSession().getSession().setMaxInactiveInterval(600);
				getUI().getNavigator().addView(DalOfflineUI.FACULTYMAINVIEW, new facultyMainView(ui));
		        getUI().getNavigator().addView(DalOfflineUI.FACULTYPROFILE, new facultyProfileView(ui));
		        getUI().getNavigator().navigateTo("fmain");
			}
			else{
				System.out.print("Other!\n");

			}
				
			
		}
		else {
			 Notification.show("Invalid password or username. (For testing purposes use \"user\" and \"pass\")");
		}

	}

    @Override
    public void enter(ViewChangeEvent event) {}
}