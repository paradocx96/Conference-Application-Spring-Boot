package com.rhna.conference.dal.repository;

import com.rhna.conference.dal.model.DownloadModel;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DownloadMongoRepository extends MongoRepository<DownloadModel,String>{

    // Find by Status method
    List<DownloadModel> findByStatus(String status);
}
