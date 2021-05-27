package com.rhna.conference.dal.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Document("keynote")
public class KeyNoteModel {
	
	@Id
	private String id;
	private String speakername;
	private String speakertype;
	private String organization;
	private String description;
	private String status;
	private String user;
	private LocalDateTime datetime;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getSpeakername() {
		return speakername;
	}
	public void setSpeakername(String speakername) {
		this.speakername = speakername;
	}
	public String getSpeakertype() {
		return speakertype;
	}
	public void setSpeakertype(String speakertype) {
		this.speakertype = speakertype;
	}
	public String getOrganization() {
		return organization;
	}
	public void setOrganization(String organization) {
		this.organization = organization;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getUser() {
		return user;
	}
	public void setUser(String user) {
		this.user = user;
	}
	public LocalDateTime getDatetime() {
		return datetime;
	}
	public void setDatetime(LocalDateTime datetime) {
		this.datetime = datetime;
	}
}
