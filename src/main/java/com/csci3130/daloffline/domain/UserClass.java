package com.csci3130.daloffline.domain;
public class UserClass {
	//Base/abstracted class for other user types to inherit from
	//I have no idea what I'm doing I don't even know if I remember anything from Java II
	long id;
	String lastname;
	String firstname;
	String usertype; //May or may not actually be needed
	
	//boolean isThankfulforExisting = false;
	//int saltInAttitude = 0;
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getLastname() {
		return lastname;
	}
	public void setLastname(String lastname) {
		this.lastname = lastname;
	}
	public String getFirstname() {
		return firstname;
	}
	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}
	public String getUsertype() {
		return usertype;
	}
	public void setUsertype(String usertype) {
		this.usertype = usertype;
	}
	//No methods for passwords because that's unacceptable
	//public boolean isThankfulforExisting() {
		//return isThankfulforExisting;
	//}
	/*public int getSaltInAttitude() {
		return saltInAttitude;
	}
	public void addSalt() {
		saltInAttitude++;
	}*/
	@Override
	public String toString() {
		return "UserClass [id=" + id + ", surname=" + lastname + ", first name=" + firstname + 
				"]\n Please Kill Me";
	}
}
//Probably not gonna need most of these attributes or even any of them