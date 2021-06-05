//IT19014128
//A.M.W.W.R.L. Wataketiya

package com.rhna.conference.dal.model;

import org.bson.types.Binary;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document("researchpaper")
public class ResearchPaperModel {
	
	@Id
	private String id;
	private String username;
	private String email;
	private String title;
	private String status;
	private Binary researchPaper;
	
	
	public ResearchPaperModel() {
		
	}
	
	public ResearchPaperModel(String id, String username, String email, String title, String status,
			Binary researchPaper) {
		super();
		this.id = id;
		this.username = username;
		this.email = email;
		this.title = title;
		this.status = status;
		this.researchPaper = researchPaper;
	}

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

	public Binary getResearchPaper() {
		return researchPaper;
	}

	public void setResearchPaper(Binary researchPaper) {
		this.researchPaper = researchPaper;
	}
	
	
	
	

}
