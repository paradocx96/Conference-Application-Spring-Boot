package com.rhna.conference.dal.repository;

import com.rhna.conference.dal.model.WorkshopModel;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WorkshopMongoRepository extends MongoRepository<WorkshopModel, String> {
    WorkshopModel findByUsername(String username);
    WorkshopModel findByTitle(String title);

}
