package com.rhna.conference.dal.adapter;

import com.rhna.conference.dal.model.KeyNoteModel;
import com.rhna.conference.dal.repository.KeyNoteMongoRepository;
import com.rhna.conference.domain.KeyNote;
import com.rhna.conference.domain.KeyNoteDataAdapter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
public class KeyNoteAdapterMongoImpl implements KeyNoteDataAdapter {

    private final KeyNoteMongoRepository repository;
    private final MongoTemplate mongoTemplate;

    @Autowired
    public KeyNoteAdapterMongoImpl(KeyNoteMongoRepository repository, MongoTemplate mongoTemplate) {
        this.repository = repository;
        this.mongoTemplate = mongoTemplate;
    }

    @Override
    public KeyNote save(KeyNote keyNote) {

        // Creat keynote model object
        KeyNoteModel keyNoteModel = new KeyNoteModel();

        // Setting values to keynote model object
        keyNoteModel.setSpeakername(keyNote.getSpeakername());
        keyNoteModel.setSpeakertype(keyNote.getSpeakertype());
        keyNoteModel.setOrganization(keyNote.getOrganization());
        keyNoteModel.setDescription(keyNote.getDescription());
        keyNoteModel.setStatus(keyNote.getStatus());
        keyNoteModel.setUser(keyNote.getUser());
        keyNoteModel.setDatetime(keyNote.getDatetime());

        // Saving keynote model data in database
        keyNoteModel = repository.save(keyNoteModel);

        // Assign auto generated key to object and return object
        keyNote.setId(keyNoteModel.getId());
        return keyNote;
    }

    @Override
    public List<KeyNote> getAll() {

        // Creating list model object
        // Assigning all keynotes data to list
        List<KeyNoteModel> keyNoteModels = repository.findAll();

        // Creating list keynote object
        List<KeyNote> keyNotes = new ArrayList<>();

        // Adding all keynotes to list object
        for (KeyNoteModel keyNoteModel : keyNoteModels) {
            KeyNote keyNote = new KeyNote();

            keyNote.setId(keyNoteModel.getId());
            keyNote.setSpeakername(keyNoteModel.getSpeakername());
            keyNote.setSpeakertype(keyNoteModel.getSpeakertype());
            keyNote.setOrganization(keyNoteModel.getOrganization());
            keyNote.setDescription(keyNoteModel.getDescription());
            keyNote.setStatus(keyNoteModel.getStatus());
            keyNote.setUser(keyNoteModel.getUser());
            keyNote.setDatetime(keyNoteModel.getDatetime());

            keyNotes.add(keyNote);
        }

        // Return keynote list
        return keyNotes;
    }

    @Override
    public void deleteById(String id) {
        try {
            // Calling deletebyid function by parameter as id
            repository.deleteById(id);
        } catch (Exception exception) {
            // print error in console
            exception.printStackTrace();
        }
    }

    @Override
    public KeyNote update(KeyNote keyNote) {
        // Creating keynote model and calling find and modify method
        KeyNoteModel keyNoteModel = mongoTemplate.findAndModify(
                // find keynote by id
                Query.query(Criteria.where("id").is(keyNote.getId())),
                // setting values to founded
                new Update()
                        .set("speakername", keyNote.getSpeakername())
                        .set("speakertype", keyNote.getSpeakertype())
                        .set("organization", keyNote.getOrganization())
                        .set("description", keyNote.getDescription())
                        .set("status", keyNote.getStatus())
                        .set("user", keyNote.getUser()),
                KeyNoteModel.class
        );
        // Assign current date time and return object
        keyNote.setDatetime(keyNoteModel.getDatetime());
        return keyNote;
    }

    @Override
    public List<KeyNote> getById(String id) {

        // Creating keynote model object
        KeyNoteModel keyNoteModel = new KeyNoteModel();

        // Getting keynote find by id and assign to model object
        keyNoteModel = repository.findById(id).get();

        // Creating array list keynote object
        List<KeyNote> keyNoteList = new ArrayList<>();

        // Creating keynote object
        KeyNote keyNote = new KeyNote();

        // Setting values to keynote object that founded by id
        keyNote.setId(keyNoteModel.getId());
        keyNote.setSpeakername(keyNoteModel.getSpeakername());
        keyNote.setSpeakertype(keyNoteModel.getSpeakertype());
        keyNote.setOrganization(keyNoteModel.getOrganization());
        keyNote.setDescription(keyNoteModel.getDescription());
        keyNote.setStatus(keyNoteModel.getStatus());
        keyNote.setUser(keyNoteModel.getUser());
        keyNote.setDatetime(keyNoteModel.getDatetime());

        // Adding keynote object array list object and return it
        keyNoteList.add(keyNote);
        return keyNoteList;
    }

    @Override
    public KeyNote updateStatus(KeyNote keyNote) {
        // Creating keynote model and calling find and modify method
        KeyNoteModel keyNoteModel = mongoTemplate.findAndModify(
                // find keynote by id
                Query.query(Criteria.where("id").is(keyNote.getId())),
                // setting status to founded
                new Update()
                        .set("status", keyNote.getStatus()),
                KeyNoteModel.class
        );

        // Setting values to keynote object that founded by id
        if (keyNoteModel != null) {
            keyNote.setSpeakername(keyNoteModel.getSpeakername());
            keyNote.setSpeakertype(keyNoteModel.getSpeakertype());
            keyNote.setOrganization(keyNoteModel.getOrganization());
            keyNote.setDescription(keyNoteModel.getDescription());
            keyNote.setUser(keyNoteModel.getUser());
            keyNote.setDatetime(keyNoteModel.getDatetime());

            // Return updated keynote object
            return keyNote;
        } else {
            return null;
        }
    }

    @Override
    public List<KeyNote> getByStatus(String status) {
        // Creating list keynote object and assigning values find by status
        List<KeyNoteModel> keyNoteModels = repository.findByStatus(status);

        // Creating array list keynote object
        List<KeyNote> keyNoteList = new ArrayList<>();

        // Adding all keynotes to list object
        for (KeyNoteModel keyNoteModel : keyNoteModels) {

            // Creating keynote object
            KeyNote keyNote = new KeyNote();

            // Setting values to keynote object
            keyNote.setId(keyNoteModel.getId());
            keyNote.setSpeakername(keyNoteModel.getSpeakername());
            keyNote.setSpeakertype(keyNoteModel.getSpeakertype());
            keyNote.setOrganization(keyNoteModel.getOrganization());
            keyNote.setDescription(keyNoteModel.getDescription());
            keyNote.setStatus(keyNoteModel.getStatus());
            keyNote.setUser(keyNoteModel.getUser());
            keyNote.setDatetime(keyNoteModel.getDatetime());

            // Adding keynote object to array object
            keyNoteList.add(keyNote);
        }
        // Return array list object
        return keyNoteList;
    }

}
