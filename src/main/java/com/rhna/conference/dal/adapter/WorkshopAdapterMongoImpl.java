package com.rhna.conference.dal.adapter;

import com.rhna.conference.dal.model.WorkshopModel;
import com.rhna.conference.dal.repository.WorkshopMongoRepository;
import com.rhna.conference.domain.Workshop;
import com.rhna.conference.domain.WorkshopDataAdapter;
import org.bson.types.Binary;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.http.ContentDisposition;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
public class WorkshopAdapterMongoImpl implements WorkshopDataAdapter {
    private final WorkshopMongoRepository workshopMongoRepository;
    private final MongoTemplate mongoTemplate;

    @Autowired
    public WorkshopAdapterMongoImpl(WorkshopMongoRepository workshopMongoRepository, MongoTemplate mongoTemplate) {
        this.workshopMongoRepository = workshopMongoRepository;
        this.mongoTemplate = mongoTemplate;
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
            workshop.setDocuments(null);  // Do not send documents. only pass has or hasn't status.
            workshop.setIsPublished(workshopModel.getPublished());
            workshop.setHasDocuments(workshopModel.getHasDocuments());
            workshops.add(workshop);
        }
        return workshops;
    }

    @Override
    public HttpEntity<byte[]> getFileByUsername(String id) {
        Optional<WorkshopModel> workshopModel;
        workshopModel = workshopMongoRepository.findById(id);
        Binary documents = workshopModel.get().getDocuments();

        String fileName = workshopModel.get().getTitle();  //Pass workshop title as file name

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
        Query query = new Query();
        query.addCriteria(Criteria.where("_id").is(workshop.getId()));
        Update update = new Update();
        System.out.println(update);
        update.set("username", workshop.getUsername());
        update.set("title", workshop.getTitle());
        update.set("courseCode", workshop.getCourseCode());
        update.set("venue", workshop.getVenue());
        update.set("date", workshop.getDate());
        update.set("startingTime", workshop.getStartingTime());
        update.set("endTime", workshop.getEndTime());
        update.set("description", workshop.getDescription());
        update.set("documents", workshop.getDocuments());
        update.set("isPublished", false); // After update it can't publish without review.
        if (workshop.getDocuments() != null) {
            update.set("hasDocuments", true);
        }
        mongoTemplate.findAndModify(query, update, WorkshopModel.class);


        return workshop;
    }
}
