package com.rhna.conference.dal.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.rhna.conference.dal.model.ResearchPaperModel;

@Repository
public interface ResearchPaperMongoRepository extends MongoRepository<ResearchPaperModel, String> {

}
