package com.rhna.conference.dal.adapter;

import com.rhna.conference.dal.model.WorkshopModel;
import com.rhna.conference.dal.repository.WorkshopMongoRepository;
import com.rhna.conference.domain.Workshop;
import com.rhna.conference.domain.WorkshopDataAdapter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class WorkshopAdapterMongoImpl implements WorkshopDataAdapter {
    private final WorkshopMongoRepository workshopMongoRepository;

    @Autowired
    public WorkshopAdapterMongoImpl(WorkshopMongoRepository workshopMongoRepository) {
        this.workshopMongoRepository = workshopMongoRepository;
    }

    @Override
    public Workshop save(Workshop workshop) {
        WorkshopModel workshopModel = new WorkshopModel(
                workshop.getUsername(),
                workshop.getTitle(),
                workshop.getCourseCode(),
                workshop.getVenue(),
                workshop.getDate(),
                workshop.getStartingTime(),
                workshop.getEndTime(),
                workshop.getDescription(),
                workshop.getDocuments());
        workshopMongoRepository.save(workshopModel);
        return workshop;
    }

    @Override
    public List<Workshop> getAll() {
        return null;
    }
}
