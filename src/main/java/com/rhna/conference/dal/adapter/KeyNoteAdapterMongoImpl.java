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

        KeyNoteModel keyNoteModel = new KeyNoteModel();

        keyNoteModel.setSpeakername(keyNote.getSpeakername());
        keyNoteModel.setSpeakertype(keyNote.getSpeakertype());
        keyNoteModel.setOrganization(keyNote.getOrganization());
        keyNoteModel.setDescription(keyNote.getDescription());
        keyNoteModel.setStatus(keyNote.getStatus());
        keyNoteModel.setUser(keyNote.getUser());
        keyNoteModel.setDatetime(keyNote.getDatetime());

        keyNoteModel = repository.save(keyNoteModel);
        keyNote.setId(keyNoteModel.getId());
        return keyNote;
    }

    @Override
    public List<KeyNote> getAll() {

        List<KeyNoteModel> keyNoteModels = repository.findAll();
        List<KeyNote> keyNotes = new ArrayList<>();

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
        return keyNotes;
    }

    @Override
    public void deleteById(String id) {
        try{
            repository.deleteById(id);
        } catch (Exception exception) {
            exception.printStackTrace();
        }
    }

    @Override
    public KeyNote update(KeyNote keyNote) {
        KeyNoteModel keyNoteModel = mongoTemplate.findAndModify(
                Query.query(Criteria.where("id").is(keyNote.getId())),
                new Update()
                        .set("speakername", keyNote.getSpeakername())
                        .set("speakertype", keyNote.getSpeakertype())
                        .set("organization", keyNote.getOrganization())
                        .set("description", keyNote.getDescription())
                        .set("status", keyNote.getStatus())
                        .set("user", keyNote.getUser()),
                KeyNoteModel.class
        );
        keyNote.setDatetime(keyNoteModel.getDatetime());
        return keyNote;
    }

    @Override
    public Optional<KeyNote> getKeyNoteById(String id) {
        return null;
    }
}
