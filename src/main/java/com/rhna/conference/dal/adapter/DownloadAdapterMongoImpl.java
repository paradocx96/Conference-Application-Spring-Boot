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
        // Creat model object
        DownloadModel downloadModel = new DownloadModel();

        // Setting values to model object
        downloadModel.setName(download.getName());
        downloadModel.setType(download.getType());
        downloadModel.setStatus(download.getStatus());
        downloadModel.setUser(download.getUser());
        downloadModel.setDatetime(download.getDatetime());
        // Convert multipartfile to Binary and assign to model
        downloadModel.setFile(
                new Binary(BsonBinarySubType.BINARY, multipartFile.getBytes())
        );

        // Saving model data in database
        downloadModel = repository.insert(downloadModel);

        // Return auto generated id
        return downloadModel.getId();
    }

    @Override
    public List<DownloadModel> getAll() {
        // Getting all download data and assign to model and return it
        List<DownloadModel> downloadModels = repository.findAll();
        return downloadModels;
    }

    @Override
    public Download updateStatus(Download download) {
        // Creating download model and calling find and modify method
        DownloadModel downloadModel = mongoTemplate.findAndModify(
                // find download by id
                Query.query(Criteria.where("id").is(download.getId())),
                // setting status to founded
                new Update()
                        .set("status", download.getStatus()),
                DownloadModel.class
        );

        // Setting values to download object that founded by id
        if (downloadModel != null) {
            download.setName(downloadModel.getName());
            download.setType(downloadModel.getType());
            download.setUser(downloadModel.getUser());
            download.setDatetime(downloadModel.getDatetime());

            // Return updated download object
            return download;
        } else {
            return null;
        }
    }

    @Override
    public HttpEntity<byte[]> getById(String id) {
        // Creating model object
        DownloadModel downloadModel = new DownloadModel();

        // Getting item from find by id and assign to model object
        downloadModel = repository.findById(id).get();

        // Assigning values to variables that founded by id
        String filename = downloadModel.getName();
        Binary file = downloadModel.getFile();

        // Convert binary data to HttpHeader object and return file
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
    public String deleteById(String id) {
        try {
            // Calling deletebyid function by parameter as id
            repository.deleteById(id);
            return "File is deleted. ID : " + id;
        } catch (Exception exception) {
            // print error in console
            exception.printStackTrace();
            return null;
        }
    }

    @Override
    public List<DownloadModel> getByStatus(String status) {
        // Getting all download data find by status and assign to model and return it
        List<DownloadModel> downloadModels = repository.findByStatus(status);
        return downloadModels;
    }

    @Override
    public Download updateDownload(Download download, MultipartFile multipartFile) throws IOException {

        // Creating binary object that getting from user and converted to binary
        Binary file = new Binary(BsonBinarySubType.BINARY, multipartFile.getBytes());

        // Creating download model and calling find and modify method
        DownloadModel downloadModel =  mongoTemplate.findAndModify(
                // find download by id
                Query.query(Criteria.where("id").is(download.getId())),
                // setting values to founded
                        new Update()
                                .set("name", download.getName())
                                .set("type", download.getType())
                                .set("status", download.getStatus())
                                .set("user", download.getUser())
                                .set("file", file),
                DownloadModel.class
        );
        // Assign current date time and return object
        download.setDatetime(downloadModel.getDatetime());
        return download;
    }
}
