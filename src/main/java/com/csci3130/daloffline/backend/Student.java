package com.csci3130.daloffline.backend;

import org.apache.commons.beanutils.BeanUtils;

import java.io.Serializable;
import java.util.Date;

/**
 * A simple DTO for the address book example.
 *
 * Serializable and cloneable Java Object that are typically persisted
 * in the database and can also be easily converted to different formats like JSON.
 */
// Backend DTO class. This is just a typical Java backend implementation
// class and nothing Vaadin specific.
public class Student implements Serializable, Cloneable {

    private Long id;

    private String firstName = "";
    private String lastName = "";
    private String studentID = "";
    private String email = "";
    private String major = "";

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getStudentID() {
        return studentID;
    }

    public void setStudentID(String studentID) {
        this.studentID = studentID;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
    
    public String getMajor() {
        return major;
    }

    public void setMajor(String major) {
        this.major = major;
    }
    
    /*public String getTaskDescription() {
    	return taskDescription;
    }
    
    public void setTaskDescription(String taskDescription){
    	this.taskDescription = taskDescription;
    }
    
    public Date getStartDate() {
    	return startDate;
    }
    
    public void setStartDate(Date startDate){
    	this.startDate = startDate;
    }
    
    public Date getExpectedEndDate() {
    	return expectedEndDate;
    }
    
    public void setExpectedEndDate(Date expectedEndDate){
    	this.expectedEndDate = expectedEndDate;
    }*/

    @Override
    public Student clone() throws CloneNotSupportedException {
        try {
            return (Student) BeanUtils.cloneBean(this);
        } catch (Exception ex) {
            throw new CloneNotSupportedException();
        }
    }

    @Override
    public String toString() {
        return "Student{" + "id=" + id + ", firstName=" + firstName + ", lastName=" + lastName 
        		+ ", studentID=" + studentID + ", email=" + email + ", major=" + major + '}';
    }

}
