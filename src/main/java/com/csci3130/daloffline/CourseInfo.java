package com.csci3130.daloffline;

import java.util.ArrayList;

import com.csci3130.daloffline.domain.*;
import com.csci3130.daloffline.views.CourseListView;
import com.vaadin.shared.ui.label.ContentMode;
import com.vaadin.ui.Button;
import com.vaadin.ui.FormLayout;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;

/**
 * Course information side panel that appears when you click on the course in the course list table.
 * 
 * @author Connor Foran
 */

public class CourseInfo extends VerticalLayout {
	Button closeButton = new Button("Close");
	Label info = new Label();
	
	/**
	 * Initializes the course info panel when the course list page loads
	 * 
	 * @param None
	 * @return Nothing
	 */
	public CourseInfo(){
		info.setContentMode(ContentMode.HTML);
		info.setCaptionAsHtml(true);
		closeButton.addClickListener(e -> this.getView().courseInfo.setVisible(false));
		setVisible(false); //Invisible by default
        setHeight("100%");
        setWidth((int)(UI.getCurrent().getPage().getBrowserWindowWidth()*0.2), UNITS_PIXELS);
        setMargin(true);

        //HorizontalLayout actions = new HorizontalLayout(closeButton);
        //actions.setSpacing(true);

        addComponents(info, closeButton);
	}
	
	/**
	 * Displays the information of the given course in the panel
	 * 
	 * @param Course
	 * @return Nothing
	 */
	public void setCourse(Course course) //This panel gets the course object from the table passed to it, so you can use this to add a ref to it in student, or something
	{
		String text = "<b>Course Name:</b> "+course.getCourseName()+"<br><b>Course Code:</b> "+course.getCourseCode()
        		      +"<br><b>Professor:</b> "+course.getInstructorName()+"<br><b>Department:</b> "+course.getFaculty();
		
		//very basic, just prints out info from the sections to prove that they exist. needs more work
		//Print out lectures
		ArrayList<Section> lectures = course.getLectures();
		text += "<br><br><b>Lecture Sections:</b>";
		for(Section lec : lectures)
		{
			text += "<br>-At "+lec.getLocation()+" with "+lec.getInstructor();
		}
		
		//Print out labs
		ArrayList<Section> labs = course.getLabs();
		text += "<br><br><b>Lab Sections:</b>";
		for(Section lab : labs)
		{
			text += "<br>-At "+lab.getLocation()+" with "+lab.getInstructor();
		}
		info.setCaption(text);
	}
	
    public CourseListView getView() {
        return (CourseListView) getUI().getNavigator().getCurrentView();
    }
}