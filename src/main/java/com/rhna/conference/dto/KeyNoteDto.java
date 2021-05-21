package com.rhna.conference.dto;

public class KeyNoteDto {

    private String speakername;
    private String speakertype;
    private String organization;
    private String description;
    private String user;

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

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }
}
