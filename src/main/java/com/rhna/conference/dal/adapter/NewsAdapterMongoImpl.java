package com.rhna.conference.dal.adapter;

import com.rhna.conference.dal.model.NewsModel;
import com.rhna.conference.dal.repository.NewsMongoRepository;
import com.rhna.conference.domain.News;
import com.rhna.conference.domain.NewsDataAdapter;

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
public class NewsAdapterMongoImpl implements NewsDataAdapter {

    private final NewsMongoRepository repository;
    private final MongoTemplate mongoTemplate;

    @Autowired
    public NewsAdapterMongoImpl(NewsMongoRepository repository, MongoTemplate mongoTemplate) {
        this.repository = repository;
        this.mongoTemplate = mongoTemplate;
    }

    @Override
    public News save(News news) {
        // Creat model object
        NewsModel newsModel =  new NewsModel();

        // Setting values to model object
        newsModel.setDescription(news.getDescription());
        newsModel.setDate(news.getDate());
        newsModel.setDatetime(news.getDatetime());
        newsModel.setStatus(news.getStatus());
        newsModel.setUser(news.getUser());

        // Saving news model data in database
        newsModel = repository.save(newsModel);

        // Assign auto generated key to object and return object
        news.setId(newsModel.getId());
        return news;
    }

    @Override
    public List<News> getAll() {
        // Creating list model object
        // Assigning all news data to list
        List<NewsModel> newsModels = repository.findAll();

        // Creating list news object
        List<News> newses = new ArrayList<>();

        // Adding all news to list object
        for (NewsModel newsModel : newsModels) {
            //create news object
            News news = new News();

            // Setting values to news object
            news.setId(newsModel.getId());
            news.setDescription(newsModel.getDescription());
            news.setDate(newsModel.getDate());
            news.setDatetime(newsModel.getDatetime());
            news.setStatus(newsModel.getStatus());
            news.setUser(newsModel.getUser());

            // Adding news object to array object
            newses.add(news);
        }
        // Return news list
        return newses;
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
    public News update(News news) {
        // Creating news model and calling find and modify method
        NewsModel newsModel = mongoTemplate.findAndModify(
                // find news by id
                Query.query(Criteria.where("id").is(news.getId())),
                // setting values to founded
                new Update()
                        .set("description", news.getDescription())
                        .set("date", news.getDate())
                        .set("status", news.getStatus())
                        .set("user", news.getUser()),
                NewsModel.class
        );
        // Assign current date time and return object
        news.setDatetime(newsModel.getDatetime());
        return news;
    }

    @Override
    public List<News> getById(String id) {
        // Creating news model object
        NewsModel newsModel = new NewsModel();

        // Getting news from find by id and assign to model object
        newsModel = repository.findById(id).get();

        // Creating array list news object
        List<News> newsList = new ArrayList<>();

        // Creating news object
        News news = new News();

        // Setting values to news object that founded by id
        news.setId(newsModel.getId());
        news.setDescription(newsModel.getDescription());
        news.setDate(newsModel.getDate());
        news.setDatetime(newsModel.getDatetime());
        news.setStatus(newsModel.getStatus());
        news.setUser(newsModel.getUser());

        // Adding news object to array list object and return it
        newsList.add(news);
        return newsList;
    }

    @Override
    public News updateStatus(News news) {
        // Creating news model and calling find and modify method
        NewsModel newsModel = mongoTemplate.findAndModify(
                // find news by id
                Query.query(Criteria.where("id").is(news.getId())),
                // setting status to founded
                new Update()
                        .set("status", news.getStatus()),
                NewsModel.class
        );

        // Setting values to news object that founded by id
        if(newsModel != null) {
            news.setDescription(newsModel.getDescription());
            news.setDate(newsModel.getDate());
            news.setDatetime(newsModel.getDatetime());
            news.setUser(newsModel.getUser());

            // Return updated news object
            return news;
        } else {
            return null;
        }
    }

    @Override
    public List<News> getByStatus(String status) {
        // Creating list news object and assigning values find by status
        List<NewsModel> newsModels = repository.findByStatus(status);

        // Creating array list news object
        List<News> newses = new ArrayList<>();

        // Adding all news to list object
        for (NewsModel newsModel : newsModels) {

            // Creating news object
            News news = new News();

            // Setting values to news object
            news.setId(newsModel.getId());
            news.setDescription(newsModel.getDescription());
            news.setDate(newsModel.getDate());
            news.setDatetime(newsModel.getDatetime());
            news.setStatus(newsModel.getStatus());
            news.setUser(newsModel.getUser());

            // Adding news object to array object
            newses.add(news);
        }
        // Return array list object
        return newses;
    }


}
