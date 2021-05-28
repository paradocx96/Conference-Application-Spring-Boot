package com.rhna.conference.dal.repository;

import com.rhna.conference.dal.model.DownloadModel;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DownloadMongoRepository extends MongoRepository<DownloadModel,String>{

    DownloadModel findByType(String type, String status);
}
