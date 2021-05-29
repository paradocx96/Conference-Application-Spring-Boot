package com.rhna.conference.dal.adapter;

import com.rhna.conference.dal.model.AboutModel;
import com.rhna.conference.dal.repository.AboutMongoRepository;
import com.rhna.conference.domain.About;
import com.rhna.conference.domain.AboutDataAdapter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class AboutAdapterMongoImpl implements AboutDataAdapter {

    private final AboutMongoRepository repository;
    private final MongoTemplate mongoTemplate;

    @Autowired
    public AboutAdapterMongoImpl(AboutMongoRepository repository, MongoTemplate mongoTemplate) {
        this.repository = repository;
        this.mongoTemplate = mongoTemplate;
    }

    @Override
    public About saveAbout(About about) {
        AboutModel aboutModel = new AboutModel();
        aboutModel.setDescription(about.getDescription());
        aboutModel.setVenue(about.getVenue());
        aboutModel.setDate(about.getDate());
        aboutModel.setDatetime(about.getDatetime());
        aboutModel = repository.save(aboutModel);
        about.setId(aboutModel.getId());
        return about;
    }

    @Override
    public List<About> getAllAbout() {
        List<AboutModel> aboutModels = repository.findAll();
        List<About> aboutList = new ArrayList<>();

        for (AboutModel aboutModel : aboutModels) {
            About about = new About();
            about.setId(aboutModel.getId());
            about.setDescription(aboutModel.getDescription());
            about.setVenue(aboutModel.getVenue());
            about.setDate(aboutModel.getDate());
            about.setDatetime(aboutModel.getDatetime());
            aboutList.add(about);
        }
        return aboutList;
    }

    @Override
    public List<About> getByIdAbout(String id) {
        AboutModel aboutModel = repository.findById(id).get();
        List<About> aboutList = new ArrayList<>();
        About about = new About();

        about.setId(aboutModel.getId());
        about.setDescription(aboutModel.getDescription());
        about.setVenue(aboutModel.getVenue());
        about.setDate(aboutModel.getDate());
        about.setDatetime(aboutModel.getDatetime());
        aboutList.add(about);
        return aboutList;
    }

    @Override
    public void deleteByIdAbout(String id) {
        try {
            repository.deleteById(id);
        } catch (Exception exception) {
            exception.printStackTrace();
        }
    }

    @Override
    public About updateAbout(About about) {
        AboutModel aboutModel = mongoTemplate.findAndModify(
                Query.query(Criteria.where("id").is(about.getId())),
                new Update()
                        .set("description", about.getDescription())
                        .set("venue", about.getVenue())
                        .set("date", about.getDate()),
                AboutModel.class
        );
        about.setDatetime(aboutModel.getDatetime());
        return about;
    }
}
