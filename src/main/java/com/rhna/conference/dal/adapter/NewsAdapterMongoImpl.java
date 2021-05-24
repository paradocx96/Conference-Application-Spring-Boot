package com.rhna.conference.dal.adapter;

import com.rhna.conference.dal.repository.NewsMongoRepository;
import com.rhna.conference.domain.News;
import com.rhna.conference.domain.NewsDataAdapter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Component;

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
        return null;
    }

    @Override
    public List<News> getAll() {
        return null;
    }

    @Override
    public void deleteById(String id) {

    }

    @Override
    public News update(News news) {
        return null;
    }

    @Override
    public Optional<News> getNewsById(String id) {
        return Optional.empty();
    }
}
