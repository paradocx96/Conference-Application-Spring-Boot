package com.rhna.conference.dal.adapter;

import com.rhna.conference.dal.model.DownloadModel;
import com.rhna.conference.dal.repository.DownloadMongoRepository;
import com.rhna.conference.domain.Download;
import com.rhna.conference.domain.DownloadDataAdapter;
import org.bson.BsonBinarySubType;
import org.bson.types.Binary;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.http.ContentDisposition;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
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
        DownloadModel downloadModel = new DownloadModel();

        downloadModel.setName(download.getName());
        downloadModel.setType(download.getType());
        downloadModel.setStatus(download.getStatus());
        downloadModel.setUser(download.getUser());
        downloadModel.setDatetime(download.getDatetime());
        downloadModel.setFile(
                new Binary(BsonBinarySubType.BINARY, multipartFile.getBytes())
        );

        downloadModel = repository.insert(downloadModel);
        return downloadModel.getId();
    }

    @Override
    public List<DownloadModel> getAll() {
        List<DownloadModel> downloadModels = repository.findAll();
        return downloadModels;
    }

    @Override
    public Download updateStatus(Download download) {
        DownloadModel downloadModel = mongoTemplate.findAndModify(
                Query.query(Criteria.where("id").is(download.getId())),
                new Update()
                        .set("status", download.getStatus()),
                DownloadModel.class
        );

        if (downloadModel != null) {
            download.setName(downloadModel.getName());
            download.setType(downloadModel.getType());
            download.setUser(downloadModel.getUser());
            download.setDatetime(downloadModel.getDatetime());

            return download;
        } else {
            return null;
        }
    }

    @Override
    public HttpEntity<byte[]> getById(String id) {
        DownloadModel downloadModel = new DownloadModel();
        downloadModel = repository.findById(id).get();

        String filename = downloadModel.getName();
        Binary file = downloadModel.getFile();

        if (file != null) {
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
            ContentDisposition contentDisposition = ContentDisposition.builder("inline").filename(filename).build();
            headers.setContentDisposition(contentDisposition);
            return new HttpEntity<byte[]>(file.getData(), headers);
        } else {
            return null;
        }
    }

    @Override
    public HttpEntity<byte[]> getByType(String type, String status) {
        return null;
    }

    @Override
    public String deleteById(String id) {
        try {
            repository.deleteById(id);
            return "File is deleted. ID : " + id;
        } catch (Exception exception) {
            exception.printStackTrace();
            return null;
        }
    }
}
