package com.rhna.conference.dal.adapter;

import com.rhna.conference.dal.model.KeyNoteModel;
import com.rhna.conference.dal.repository.KeyNoteMongoRepository;
import com.rhna.conference.domain.KeyNote;
import com.rhna.conference.domain.KeyNoteDataAdapter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Component;

import java.util.List;

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
        keyNoteModel.setUser(keyNote.getUser());
        keyNoteModel.setDatetime(keyNote.getDatetime());

        keyNoteModel = repository.save(keyNoteModel);
        keyNote.setId(keyNoteModel.getId());
        return keyNote;
    }

    @Override
    public List<KeyNote> getall() {
        return null;
    }
}
