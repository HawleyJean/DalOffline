package com.csci3130.daloffline.views;
import com.csci3130.daloffline.domain.Student;
import com.csci3130.daloffline.DalOfflineUI;
import com.vaadin.navigator.*;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.Label;
import com.vaadin.ui.Notification;
import com.vaadin.ui.Panel;
import com.vaadin.ui.VerticalLayout;

/**
 * Main menu view. Displays buttons to the user that allows them to navigate to the other interfaces.
 * 
 * @author Connor Foran
 */

public class MainView extends VerticalLayout implements View {
	/**
	 * Initializes and builds the main menu page
	 * 
	 * @param None
	 * @return Nothing
	 */
	private String username;
    public MainView(DalOfflineUI ui) {
    	username = (String)((Student) ui.getSession().getAttribute("student")).getUsername();
    	String role = ((Student) ui.getSession().getAttribute("student")).getClass().getSimpleName();
    	VerticalLayout container = new VerticalLayout();
    	Panel border = new Panel();

        Button profileButton = new Button("View Your Profile And Schedule"); //A button
        profileButton.addClickListener(e -> getUI().getNavigator().navigateTo(DalOfflineUI.USERPROFILE)); //Specify a view for this button to direct you to
        Label name = new Label("Hello, "+username +", you are logged in as a " +role);
        Button courseListButton = new Button("View All Courses");
        Button logoutButton = new Button("Logout");
       // Button studentListButton = new Button("View Student List");
        courseListButton.addClickListener(e -> getUI().getNavigator().navigateTo(DalOfflineUI.COURSELIST));
        //studentListButton.addClickListener(e-> getUI().getNavigator().navigateTo(DalOfflineUI.STUDENTLIST));
        logoutButton.addClickListener(e -> logout());
        container.addComponents(name, profileButton, courseListButton, logoutButton); //Add buttons to the view
        //container.setComponentAlignment(studentListButton, Alignment.MIDDLE_CENTER);
        container.setComponentAlignment(profileButton, Alignment.MIDDLE_CENTER); //Set alignments
        container.setComponentAlignment(courseListButton, Alignment.MIDDLE_CENTER);
        container.setComponentAlignment(name, Alignment.MIDDLE_CENTER);
        container.setComponentAlignment(logoutButton, Alignment.MIDDLE_CENTER);
        container.setHeight("40%");
        
        VerticalLayout container2 = new VerticalLayout();
        container2.addComponent(container);
        container2.setComponentAlignment(container, Alignment.MIDDLE_CENTER);
        container2.setSizeFull();
        border.setContent(container2);
        
        addComponent(border);
        border.setWidth("80%");
        border.setHeight("90%");
        setComponentAlignment(border, Alignment.MIDDLE_CENTER);
        setSizeFull();
    }
public void logout(){
	getUI().getSession().close();
	getUI().getNavigator().navigateTo("");
}

    @Override
    public void enter(ViewChangeListener.ViewChangeEvent event) {
    	//This is getting in the way of testing, and seems unnecessary
//        Notification.show("Hello, "+username);
    }
}