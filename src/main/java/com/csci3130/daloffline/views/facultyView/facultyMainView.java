package com.csci3130.daloffline.views.facultyView;
import com.csci3130.daloffline.domain.User;
import com.csci3130.daloffline.domain.Faculty;
import com.csci3130.daloffline.DalOfflineUI;
import com.vaadin.navigator.*;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.Grid;
import com.vaadin.ui.Label;
import com.vaadin.ui.Notification;
import com.vaadin.ui.Panel;
import com.vaadin.ui.VerticalLayout;

/**
 * Main menu view. Displays buttons to the user that allows them to navigate to the other interfaces.
 * 
 * @author Hawley Jean
 */

public class facultyMainView extends VerticalLayout implements View {
	/**
	 * Initializes and builds the main menu page
	 * 
	 * @param None
	 * @return Nothing
	 */
	private String username;
	
    public facultyMainView(DalOfflineUI ui) {
    	username = (String)((Faculty) ui.getSession().getAttribute("faculty")).getUsername();
    	String role = ((Faculty) ui.getSession().getAttribute("faculty")).getClass().getSimpleName();
    	VerticalLayout container = new VerticalLayout();
    	Panel border = new Panel();
    	//
    	//
    	
        Button profileButton = new Button("View Your Profile And Schedule"); //A button
        profileButton.addClickListener(e -> getUI().getNavigator().navigateTo(DalOfflineUI.FACULTYPROFILE)); //Specify a view for this button to direct you to
        Label name = new Label("Hello, "+username +", you are logged in as " +role);
        //Button courseListButton = new Button("View Courses You are Teaching");
        //Button studentListButton = new Button("View Student List");
        //courseListButton.addClickListener(e -> getUI().getNavigator().navigateTo(DalOfflineUI.COURSESTEACHING));
        //studentListButton.addClickListener(e-> getUI().getNavigator().navigateTo(DalOfflineUI.STUDENTLIST));
        
        container.addComponents(name, profileButton); //Add buttons to the view
       // container.setComponentAlignment(studentListButton, Alignment.MIDDLE_CENTER);
        container.setComponentAlignment(profileButton, Alignment.MIDDLE_CENTER); //Set alignments
    //    container.setComponentAlignment(courseListBu Alignment.MIDDLE_CENTER);
        container.setComponentAlignment(name, Alignment.MIDDLE_CENTER);
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


    @Override
    public void enter(ViewChangeListener.ViewChangeEvent event) {
    	//This is getting in the way of testing, and seems unnecessary
//        Notification.show("Hello, "+username);
    }
}