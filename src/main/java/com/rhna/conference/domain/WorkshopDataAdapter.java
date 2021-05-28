package com.rhna.conference.domain;

import org.springframework.http.HttpEntity;

import java.util.List;

public interface WorkshopDataAdapter {
    Workshop save(Workshop workshop);

    List<Workshop> getAll();

    HttpEntity<byte[]> getFileByUsername(String username);
}
