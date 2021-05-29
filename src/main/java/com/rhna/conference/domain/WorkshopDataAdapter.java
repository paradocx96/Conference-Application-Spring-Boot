package com.rhna.conference.domain;

import com.rhna.conference.dal.model.WorkshopModel;
import org.springframework.http.HttpEntity;

import java.util.List;

public interface WorkshopDataAdapter {
    Workshop save(Workshop workshop);

    List<Workshop> getAll();

    HttpEntity<byte[]> getFileByUsername(String username);

    Workshop update(Workshop workshop);

    List<Workshop> getAllPending();

    List<Workshop> getAllScheduled();

    String delete(String id);

    String approve(String id);
}
