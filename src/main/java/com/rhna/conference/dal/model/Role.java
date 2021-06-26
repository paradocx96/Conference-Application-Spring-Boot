//Name : Malwatta H.G.
//ID : IT19240848
//Group : REG_WE_03

package com.rhna.conference.dal.model;



import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

//Create new table called roles
@Document(collection = "roles")
public class Role {
	
	
	@Id
	private String id;
	
	private ERole name;
	
	//Default constructor 
	public Role() {

	}

	//Overloaded constructor
	public Role(ERole name) {
		this.name = name;
	}

	//Getters and setters
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public ERole getName() {
		return name;
	}

	public void setName(ERole name) {
		this.name = name;
	}
}