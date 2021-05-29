package com.rhna.conference.dal.adapter;

import com.rhna.conference.dal.model.WorkshopModel;
import com.rhna.conference.dal.repository.WorkshopMongoRepository;
import com.rhna.conference.domain.Workshop;
import com.rhna.conference.domain.WorkshopDataAdapter;
import org.bson.types.Binary;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ContentDisposition;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
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
            workshop.setId(workshopModel.getId());
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

    @Override
    public HttpEntity<byte[]> getFileByUsername(String username) {
        WorkshopModel workshopModel;
        workshopModel = workshopMongoRepository.findByUsername(username);
        Binary documents = workshopModel.getDocuments();

        String fileName = workshopModel.getTitle();

        if (documents != null) {
            HttpHeaders headers = new HttpHeaders();
            //set the content type to octet stream
            headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);

            //set content disposition
            ContentDisposition contentDisposition = ContentDisposition.builder("inline")
                    .filename(fileName).build();

            //set the content disposition to headers
            headers.setContentDisposition(contentDisposition);

            //return the byte array as an http entity
            return new HttpEntity<byte[]>(documents.getData(), headers);
        } else {
            return null;
        }
    }

    @Override
    public Workshop update(Workshop workshop) {
        WorkshopModel workshopModel;
        workshopModel = workshopMongoRepository.findByTitle(workshop.getId());
        workshopModel.setUsername(workshop.getUsername());
        workshopModel.setTitle(workshop.getTitle());
        workshopModel.setCourseCode(workshop.getCourseCode());
        workshopModel.setVenue(workshop.getVenue());
        workshopModel.setDate(workshop.getDate());
        workshopModel.setStartingTime(workshop.getStartingTime());
        workshopModel.setEndTime(workshop.getEndTime());
        workshopModel.setDescription(workshop.getDescription());
        if (workshop.getDocuments() != null) {
            workshopModel.setDocuments(workshop.getDocuments());
        }
        workshopMongoRepository.save(workshopModel);

        return workshop;
    }
}
