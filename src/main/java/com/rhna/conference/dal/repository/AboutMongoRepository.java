package com.rhna.conference.dal.repository;

import com.rhna.conference.dal.model.AboutModel;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AboutMongoRepository extends MongoRepository<AboutModel,String> {
}
