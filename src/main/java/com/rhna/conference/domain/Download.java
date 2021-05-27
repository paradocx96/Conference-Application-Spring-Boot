package com.rhna.conference.domain;

import java.time.LocalDateTime;

public class Download {

    private String id;
    private String name;
    private String type;
    private String status;
    private String user;
    private LocalDateTime datetime;

    public Download() {
    }

    public Download(String id, String name, String type, String status, String user, LocalDateTime datetime) {
        this.id = id;
        this.name = name;
        this.type = type;
        this.status = status;
        this.user = user;
        this.datetime = datetime;
    }

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

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
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
