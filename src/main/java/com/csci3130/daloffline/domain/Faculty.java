package com.csci3130.daloffline.domain;

import javax.persistence.*;

@Entity
public class Faculty {

	@Id
	@GeneratedValue
	long id;
	//Set<Employees> employees; //Uncomment this when we're actually dealing with faculties
	
}