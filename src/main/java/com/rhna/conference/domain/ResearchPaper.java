package com.rhna.conference.domain;

public class ResearchPaper {
	
	
	//attributes
	private String id;
	private String username;
	private String email;
	private String title;
	private String status;
	
	
	//default constructor
	public ResearchPaper() {
		
	}
	
	//overloaded constructor
	
	public ResearchPaper(String id, String username, String email, String title, String status) {
		super();
		this.id = id;
		this.username = username;
		this.email = email;
		this.title = title;
		this.status = status;
	}
	
	//getters and setters

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	@Override
	public String toString() {
		return "ResearchPaper [id=" + id + ", username=" + username + ", email=" + email + ", title=" + title
				+ ", status=" + status + "]";
	}
	
	
	
	

}
