package com.csci3130.daloffline;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;

import com.csci3130.daloffline.domain.*;
import com.csci3130.daloffline.views.CourseListView;
import com.vaadin.shared.ui.label.ContentMode;
import com.vaadin.ui.Button;
import com.vaadin.ui.FormLayout;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.Notification;
import com.vaadin.ui.Notification.Type;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.v7.data.Property.ValueChangeListener;
import com.vaadin.v7.ui.NativeSelect;

/**
 * Course information side panel that appears when you click on the course in the course list table.
 * 
 * @author Connor Foran
 */

@SuppressWarnings("deprecation")
public class CourseInfo extends VerticalLayout {
	Button closeButton = new Button("Close");
	Button enrollButton = new Button("Enroll");
	Button removeButton = new Button("Leave this Course");
	
	NativeSelect  lectureList = new NativeSelect ("Lecture Sections");
	Section lectureChoice = null;
	NativeSelect  labList = new NativeSelect ("Lab Sections");
	Section labChoice = null;
	
	Course currentCourse = null;
	
	//ValueChangeListener lectureListener = e -> setLectureChoice(Long.parseLong((String)lectureList.getValue()));
	//ValueChangeListener labListener = e -> setLabChoice(Long.parseLong((String)labList.getValue()));
	
	Label courseInfo = new Label();
	Label lectureInfo = new Label();
	Label labInfo = new Label();
	
	/**
	 * Initializes the course info panel when the course list page loads
	 * 
	 * @param None
	 * @return Nothing
	 */
	public CourseInfo()
	{
		courseInfo.setContentMode(ContentMode.HTML);
		courseInfo.setCaptionAsHtml(true);
		lectureInfo.setContentMode(ContentMode.HTML);
		lectureInfo.setCaptionAsHtml(true);
		labInfo.setContentMode(ContentMode.HTML);
		labInfo.setCaptionAsHtml(true);
		
		closeButton.addClickListener(e -> this.getView().courseInfo.setVisible(false));
		enrollButton.addClickListener(e -> addSectionToStudent());
		//removeButton.addClickListener(e -> removeCourseFromStudent());
		setVisible(false); //Invisible by default
        setHeight("100%");
        setWidth((int)(UI.getCurrent().getPage().getBrowserWindowWidth()*0.2), UNITS_PIXELS);
        setMargin(true);
        
        lectureList.setWidth((int)(UI.getCurrent().getPage().getBrowserWindowWidth()*0.1), UNITS_PIXELS);
        labList.setWidth((int)(UI.getCurrent().getPage().getBrowserWindowWidth()*0.1), UNITS_PIXELS);
        lectureList.setNullSelectionAllowed(false);
        labList.setNullSelectionAllowed(false);


        addComponents(courseInfo, lectureList, labList, lectureInfo, labInfo, enrollButton, closeButton);
	}
	
	/**
	 * Adds the currently chosen sections to the user's account
	 * 
	 * @param None
	 * @return Nothing
	 */
	public void addSectionToStudent()
	{
		boolean lectureAlreadyAdded = false;
		boolean labAlreadyAdded = false;
		
		if(lectureChoice == null && labChoice == null)
			return;
		
		//Get the current user object
		EntityManager em = DalOfflineUI.factory.createEntityManager();
		em.getTransaction().begin();
		String username = (String) getUI().getSession().getAttribute("username");
		User user = em.createQuery("SELECT user FROM USERS user WHERE user.username = :input_user", User.class).setParameter("input_user", username).getSingleResult();
		
		ArrayList<Section> currentlyEnrolledSections = user.getEnrolledSections(); //Get the user's current sections
		
		for(Section sec : currentlyEnrolledSections) //Make sure the user isn't already enrolled into these sections
		{
			if(lectureChoice != null && sec.getID() == lectureChoice.getID())
				lectureAlreadyAdded = true;
			else if(labChoice != null && sec.getID() == labChoice.getID())
				labAlreadyAdded = true;
		}
		
		//Enroll the user into the sections, if applicable
		if(lectureChoice != null && !lectureAlreadyAdded)
			user.addSection(lectureChoice);
		if(labChoice != null && !labAlreadyAdded)
			user.addSection(labChoice);
		
		em.getTransaction().commit();
		em.close();
		
		setVisible(false); //Close this tab
		Notification.show("Section enrollment successful.","Total enrolled sections: "+user.getEnrolledSections().size(),Type.TRAY_NOTIFICATION);
	}
	
	//Remove course functionality not currently working.
	public void removeCourseFromStudent()
	{
		//Get the current user object
		EntityManager em = DalOfflineUI.factory.createEntityManager();
		em.getTransaction().begin();
		String username = (String) getUI().getSession().getAttribute("username");
		User user = em.createQuery("SELECT user FROM USERS user WHERE user.username = :input_user", User.class).setParameter("input_user", username).getSingleResult();
		
		boolean foundCourse = user.removeCourse(currentCourse);
		
		if(foundCourse)
			Notification.show("Removed course from student account.","New total enrolled sections: "+user.getEnrolledSections().size(),Type.TRAY_NOTIFICATION);
		else
			Notification.show("Remove course failed.","You are not enrolled into this course.",Type.TRAY_NOTIFICATION);
	}
	
	/**
	 * Displays the information of the given course in the panel
	 * 
	 * @param Course
	 * @return Nothing
	 */
	public void setCourse(Course course)
	{
		currentCourse = course;
		//Course Info
		String text = "<b>Course Name:</b> "+course.getCourseName()+"<br><b>Course Code:</b> "+course.getCourseCode()
        		      +"<br><b>Professor:</b> "+course.getInstructorName()+"<br><b>Department:</b> "+course.getFaculty();
		courseInfo.setCaption(text);
		
		//Create lectures dropdown list
		ArrayList<Section> lectures = course.getLectures();
		//lectureList.removeAllItems();
		for(Section lec : lectures)
			lectureList.addItem(""+lec.getID());
		lectureChoice = null;
		lectureList.addValueChangeListener(e -> setLectureChoice(Long.parseLong((String)lectureList.getValue())));
		
		//Create labs dropdown list
		ArrayList<Section> labs = course.getLabs();
		//labList.removeAllItems();
		for(Section lab : labs)
			labList.addItem(""+lab.getID());
		labChoice = null;
		labList.addValueChangeListener(e -> setLabChoice(Long.parseLong((String)labList.getValue())));
		
	}
	
	/**
	 * Stores a reference to the lecture Section chosen by the user in the drop-down menu
	 * 
	 * @param Long - ID number for the chosen section
	 */
	private void setLectureChoice(long id)
	{
		EntityManager em = DalOfflineUI.factory.createEntityManager();
		lectureChoice = em.find(Section.class, id);
		em.close();
		String text = "Lecture #"+lectureChoice.getID()+" at "+lectureChoice.getLocation()+" with "+lectureChoice.getInstructor()+"<br>";
		lectureInfo.setCaption(text);
	}
	
	/**
	 * Stores a reference to the lab Section chosen by the user in the drop-down menu
	 * 
	 * @param Long - ID number for the chosen section
	 */
	private void setLabChoice(long id)
	{
		EntityManager em = DalOfflineUI.factory.createEntityManager();
		labChoice = em.find(Section.class, id);
		em.close();
		String text = "Lab #"+labChoice.getID()+" at "+labChoice.getLocation()+" with "+labChoice.getInstructor()+"<br>";
		labInfo.setCaption(text);
	}
	
    public CourseListView getView() {
        return (CourseListView) getUI().getNavigator().getCurrentView();
    }
}