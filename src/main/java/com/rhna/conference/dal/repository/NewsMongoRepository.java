package com.rhna.conference.dal.repository;

import com.rhna.conference.dal.model.NewsModel;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NewsMongoRepository extends MongoRepository<NewsModel, String> {
}
