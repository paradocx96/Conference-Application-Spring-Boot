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
        NewsModel newsModel =  new NewsModel();

        newsModel.setDescription(news.getDescription());
        newsModel.setDate(news.getDate());
        newsModel.setDatetime(news.getDatetime());
        newsModel.setStatus(news.getStatus());
        newsModel.setUser(news.getUser());

        newsModel = repository.save(newsModel);
        news.setId(newsModel.getId());
        return news;
    }

    @Override
    public List<News> getAll() {
        List<NewsModel> newsModels = repository.findAll();
        List<News> newses = new ArrayList<>();

        for (NewsModel newsModel : newsModels) {
            News news = new News();

            news.setId(newsModel.getId());
            news.setDescription(newsModel.getDescription());
            news.setDate(newsModel.getDate());
            news.setDatetime(newsModel.getDatetime());
            news.setStatus(newsModel.getStatus());
            news.setUser(newsModel.getUser());

            newses.add(news);
        }
        return newses;
    }

    @Override
    public void deleteById(String id) {
        try {
            repository.deleteById(id);
        } catch (Exception exception) {
            exception.printStackTrace();
        }
    }

    @Override
    public News update(News news) {
        NewsModel newsModel = mongoTemplate.findAndModify(
                Query.query(Criteria.where("id").is(news.getId())),
                new Update()
                        .set("description", news.getDescription())
                        .set("date", news.getDate())
                        .set("status", news.getStatus())
                        .set("user", news.getUser()),
                NewsModel.class
        );
        news.setDatetime(newsModel.getDatetime());
        return news;
    }

    @Override
    public List<News> getById(String id) {

        NewsModel newsModel = new NewsModel();
        newsModel = repository.findById(id).get();
        List<News> newsList = new ArrayList<>();

        News news = new News();

        news.setId(newsModel.getId());
        news.setDescription(newsModel.getDescription());
        news.setDate(newsModel.getDate());
        news.setDatetime(newsModel.getDatetime());
        news.setStatus(newsModel.getStatus());
        news.setUser(newsModel.getUser());

        newsList.add(news);
        return newsList;
    }

    @Override
    public News updateStatus(News news) {

        NewsModel newsModel = mongoTemplate.findAndModify(
                Query.query(Criteria.where("id").is(news.getId())),
                new Update()
                        .set("status", news.getStatus()),
                NewsModel.class
        );

        if(newsModel != null) {
            news.setDescription(newsModel.getDescription());
            news.setDate(newsModel.getDate());
            news.setDatetime(newsModel.getDatetime());
            news.setUser(newsModel.getUser());

            return news;
        } else {
            return null;
        }
    }

    @Override
    public List<News> getByStatus(String status) {

        List<NewsModel> newsModels = repository.findByStatus(status);
        List<News> newses = new ArrayList<>();

        for (NewsModel newsModel : newsModels) {
            News news = new News();

            news.setId(newsModel.getId());
            news.setDescription(newsModel.getDescription());
            news.setDate(newsModel.getDate());
            news.setDatetime(newsModel.getDatetime());
            news.setStatus(newsModel.getStatus());
            news.setUser(newsModel.getUser());

            newses.add(news);
        }
        return newses;
    }


}
