package com.csci3130.daloffline.user;

public class UserClass {
	//Base/abstracted class for other user types to inherit from
	//I have no idea what I'm doing I don't even know if I remember anything from Java II - EN 
	String id;
	String name;
	String password;
	boolean isThankfulforExisting = false;
	int saltInAttitude = 0;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	//No methods for passwords because that's unacceptable
	public boolean isThankfulforExisting() {
		return isThankfulforExisting;
	}
	public int getSaltInAttitude() {
		return saltInAttitude;
	}
	public void addSalt() {
		saltInAttitude++;
	}
	@Override
	public String toString() {
		return "UserClass [id=" + id + ", name=" + name + "]\n Please Kill Me";
	}
	//Probably not gonna need most of these attributes or even any of them
}

