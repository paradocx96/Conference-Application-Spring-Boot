package com.rhna.conference.dal.adapter;

import com.rhna.conference.dal.model.DownloadModel;
import com.rhna.conference.dal.repository.DownloadMongoRepository;
import com.rhna.conference.domain.Download;
import com.rhna.conference.domain.DownloadDataAdapter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Component
public class DownloadAdapterMongoImpl implements DownloadDataAdapter {

    private final DownloadMongoRepository repository;
    private final MongoTemplate mongoTemplate;

    @Autowired
    public DownloadAdapterMongoImpl(DownloadMongoRepository repository, MongoTemplate mongoTemplate) {
        this.repository = repository;
        this.mongoTemplate = mongoTemplate;
    }

    @Override
    public String save(Download download, MultipartFile multipartFile) throws IOException {
        return null;
    }

    @Override
    public List<DownloadModel> getAll() {
        return null;
    }

    @Override
    public Download updateStatus(Download download) {
        return null;
    }

    @Override
    public HttpEntity<byte[]> getById(String id) {
        return null;
    }

    @Override
    public HttpEntity<byte[]> getByType(String type, String status) {
        return null;
    }
}
