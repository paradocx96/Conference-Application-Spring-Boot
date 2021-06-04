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
        // Creat model object
        AboutModel aboutModel = new AboutModel();

        // Setting values to model object
        aboutModel.setDescription(about.getDescription());
        aboutModel.setVenue(about.getVenue());
        aboutModel.setDate(about.getDate());
        aboutModel.setDatetime(about.getDatetime());

        // Saving about model data in database
        aboutModel = repository.save(aboutModel);

        // Assign auto generated key to object and return object
        about.setId(aboutModel.getId());
        return about;
    }

    @Override
    public List<About> getAllAbout() {
        // Creating list model object
        // Assigning all about data to list
        List<AboutModel> aboutModels = repository.findAll();

        // Creating list about object
        List<About> aboutList = new ArrayList<>();

        // Adding all about to list object
        for (AboutModel aboutModel : aboutModels) {
            //create about object
            About about = new About();

            // Setting values to news object
            about.setId(aboutModel.getId());
            about.setDescription(aboutModel.getDescription());
            about.setVenue(aboutModel.getVenue());
            about.setDate(aboutModel.getDate());
            about.setDatetime(aboutModel.getDatetime());

            // Adding about object to array object
            aboutList.add(about);
        }
        // Return about list
        return aboutList;
    }

    @Override
    public List<About> getByIdAbout(String id) {
        // Creating about model object
        // Getting about details from find by id and assign to model object
        AboutModel aboutModel = repository.findById(id).get();

        // Creating array list about object
        List<About> aboutList = new ArrayList<>();

        // Creating about object
        About about = new About();

        // Setting values to about object that founded by id
        about.setId(aboutModel.getId());
        about.setDescription(aboutModel.getDescription());
        about.setVenue(aboutModel.getVenue());
        about.setDate(aboutModel.getDate());
        about.setDatetime(aboutModel.getDatetime());

        // Adding about object to array list object and return it
        aboutList.add(about);
        return aboutList;
    }

    @Override
    public void deleteByIdAbout(String id) {
        try {
            // Calling deletebyid function by parameter as id
            repository.deleteById(id);
        } catch (Exception exception) {
            // print error in console
            exception.printStackTrace();
        }
    }

    @Override
    public About updateAbout(About about) {
        // Creating about model and calling find and modify method
        AboutModel aboutModel = mongoTemplate.findAndModify(
                // find about by id
                Query.query(Criteria.where("id").is(about.getId())),
                // setting values to founded
                new Update()
                        .set("description", about.getDescription())
                        .set("venue", about.getVenue())
                        .set("date", about.getDate()),
                AboutModel.class
        );
        // Assign current date time and return object
        about.setDatetime(aboutModel.getDatetime());
        return about;
    }
}
