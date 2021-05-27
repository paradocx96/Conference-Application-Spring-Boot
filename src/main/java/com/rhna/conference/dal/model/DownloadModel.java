package com.rhna.conference.dal.model;

import org.bson.types.Binary;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Document("download")
public class DownloadModel {

    @Id
    private String id;
    private String name;
    private String type;
    private Binary file;
    private String status;
    private String user;
    private LocalDateTime datetime;

    public DownloadModel() {
    }

    public DownloadModel(String id, String name, String type, Binary file, String status, String user, LocalDateTime datetime) {
        super();
        this.id = id;
        this.name = name;
        this.type = type;
        this.file = file;
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

    public Binary getFile() {
        return file;
    }

    public void setFile(Binary file) {
        this.file = file;
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
