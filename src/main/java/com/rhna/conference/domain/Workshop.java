package com.rhna.conference.domain;

import org.bson.types.Binary;

public class Workshop {
    private String id;
    private String username;
    private String title;
    private String courseCode;
    private String venue;
    private String date;
    private String startingTime;
    private String endTime;
    private String description;
    private Binary documents;
    private Boolean isPublished;
    private Boolean hasDocuments;

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

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getCourseCode() {
        return courseCode;
    }

    public void setCourseCode(String courseCode) {
        this.courseCode = courseCode;
    }

    public String getVenue() {
        return venue;
    }

    public void setVenue(String venue) {
        this.venue = venue;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getStartingTime() {
        return startingTime;
    }

    public void setStartingTime(String startingTime) {
        this.startingTime = startingTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Binary getDocuments() {
        return documents;
    }

    public void setDocuments(Binary documents) {
        this.documents = documents;
    }

    public Boolean getIsPublished() {
        return isPublished;
    }

    public void setIsPublished(Boolean published) {
        isPublished = published;
    }

    public Boolean getHasDocuments() {
        return hasDocuments;
    }

    public void setHasDocuments(Boolean hasDocuments) {
        this.hasDocuments = hasDocuments;
    }

    @Override
    public String toString() {
        return "Workshop{" +
                "id='" + id + '\'' +
                ", username='" + username + '\'' +
                ", title='" + title + '\'' +
                ", courseCode='" + courseCode + '\'' +
                ", venue='" + venue + '\'' +
                ", date='" + date + '\'' +
                ", startingTime='" + startingTime + '\'' +
                ", endTime='" + endTime + '\'' +
                ", description='" + description + '\'' +
                ", documents=" + documents +
                ", isPublished=" + isPublished +
                ", hasDocuments=" + hasDocuments +
                '}';
    }
}
