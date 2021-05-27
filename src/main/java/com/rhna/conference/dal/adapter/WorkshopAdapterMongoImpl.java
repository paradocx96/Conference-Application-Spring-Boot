package com.rhna.conference.dal.adapter;

import com.rhna.conference.dal.model.WorkshopModel;
import com.rhna.conference.dal.repository.WorkshopMongoRepository;
import com.rhna.conference.domain.Workshop;
import com.rhna.conference.domain.WorkshopDataAdapter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
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
        List<WorkshopModel> workshopModelList = workshopMongoRepository.findAll();
        List<Workshop> workshops = new ArrayList<>();
        for (WorkshopModel workshopModel : workshopModelList) {
            Workshop workshop = new Workshop();
            workshop.setUsername(workshopModel.getUsername());
            workshop.setTitle(workshopModel.getTitle());
            workshop.setCourseCode(workshopModel.getCourseCode());
            workshop.setVenue(workshopModel.getVenue());
            workshop.setDate(workshopModel.getDate());
            workshop.setStartingTime(workshopModel.getStartingTime());
            workshop.setEndTime(workshopModel.getEndTime());
            workshop.setDescription(workshopModel.getDescription());
            workshop.setDocuments(workshopModel.getDocuments());
            workshops.add(workshop);
        }
        return workshops;
    }
}
