package com.rhna.conference.dal.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.rhna.conference.dal.model.ResearchPaperModel;

@Repository
public interface ResearchPaperMongoRepository extends MongoRepository<ResearchPaperModel, String> {
	
	//use to fetch the paper by the uploaded user.
	public ResearchPaperModel findByUsername(String username);

}
