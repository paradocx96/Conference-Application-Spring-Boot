package com.rhna.conference.api;

import com.rhna.conference.dal.model.DownloadModel;
import com.rhna.conference.domain.Download;
import com.rhna.conference.domain.DownloadDataAdapter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class DownloadApi {

    private final DownloadDataAdapter adapter;

    @Autowired
    public DownloadApi(DownloadDataAdapter adapter) {
        this.adapter = adapter;
    }

    // Add download method
    public String addDownload(MultipartFile multipartFile, String name, String type, String user) {
        // Create download object
        Download download = new Download();

        // Set values to download object
        download.setName(name);
        download.setType(type);
        download.setStatus("Inactive");
        download.setUser(user);
        download.setDatetime(LocalDateTime.now());

        String ID;
        try {
            // Calling save method and assign result
            ID = adapter.save(download, multipartFile);
            return ID;
        } catch (IOException exception) {
            // Printing error in console
            System.out.println("Exception in adding research paper in API : " + exception);
            exception.printStackTrace();
            return "Not Saved!";
        }
    }

    // Get all download method
    public List<Download> getAll() {
        // Creating list model object
        List<DownloadModel> downloadModelList = new ArrayList<DownloadModel>();

        // Assigning all data to list
        downloadModelList = adapter.getAll();

        // Creating list download object
        List<Download> downloadList = new ArrayList<Download>();

        // Adding all download to list object
        for (DownloadModel downloadModel : downloadModelList) {

            //create download object
            Download download = new Download();

            // Setting values to download object
            download.setId(downloadModel.getId());
            download.setName(downloadModel.getName());
            download.setType(downloadModel.getType());
            download.setStatus(downloadModel.getStatus());
            download.setUser(downloadModel.getUser());
            download.setDatetime(downloadModel.getDatetime());

            // Adding download object to array object
            downloadList.add(download);
        }
        // Return download list
        return downloadList;
    }

    // Method for Update download item status
    public Download updateStatus(String id, String status) {
        // Creating download object
        Download download = new Download();

        // Set values to download object
        download.setId(id);
        download.setStatus(status);

        // Calling update status method passing download object
        download = adapter.updateStatus(download);
        return download;
    }

    // Method for Download file get by id
    public HttpEntity<byte[]> getDownloadById(String id) {
        // calling getbyid method using parameter as id
        return adapter.getById(id);
    }

    // Delete Downloads by id method
    public String deleteDownloadById(String id) {
        // Calling delete method, id as parameter
        return adapter.deleteById(id);
    }

    // Method to getting all download items by status
    public List<Download> getByStatus(String status) {
        // Creating list model object
        List<DownloadModel> downloadModelList = new ArrayList<DownloadModel>();

        // Assigning values find by status
        downloadModelList = adapter.getByStatus(status);

        // Creating array list download object
        List<Download> downloadList = new ArrayList<Download>();

        // Adding all download to list object
        for (DownloadModel downloadModel : downloadModelList) {
            // Creating download object
            Download download = new Download();

            // Setting values to download object
            download.setId(downloadModel.getId());
            download.setName(downloadModel.getName());
            download.setType(downloadModel.getType());
            download.setStatus(downloadModel.getStatus());
            download.setUser(downloadModel.getUser());
            download.setDatetime(downloadModel.getDatetime());

            // Adding download object to array object
            downloadList.add(download);
        }

        // Return array list object
        return downloadList;
    }

    // Method for update download item
    public Download updateDownload(MultipartFile multipartFile,
                                 String id,
                                 String name,
                                 String type,
                                 String user,
                                 String status) {

        //create download object
        Download download = new Download();

        // Set values to download object
        download.setId(id);
        download.setName(name);
        download.setType(type);
        download.setUser(user);
        download.setStatus(status);
        download.setDatetime(LocalDateTime.now());

        try {
            // Calling updateDownload method
            return adapter.updateDownload(download,multipartFile);
        } catch (Exception exception) {
            // Printing error in console
            exception.printStackTrace();
            return null;
        }
    }
}
