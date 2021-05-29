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

    public String addDownload(MultipartFile multipartFile, String name, String type, String user) {
        Download download = new Download();
        download.setName(name);
        download.setType(type);
        download.setStatus("Inactive");
        download.setUser(user);
        download.setDatetime(LocalDateTime.now());

        String ID;
        try {
            ID = adapter.save(download, multipartFile);
            return ID;
        } catch (IOException exception) {
            System.out.println("Exception in adding research paper in API : " + exception);
            exception.printStackTrace();
            return "Not Saved!";
        }
    }

    public List<Download> getAll() {
        List<Download> downloadList = new ArrayList<Download>();
        List<DownloadModel> downloadModelList = new ArrayList<DownloadModel>();

        downloadModelList = adapter.getAll();
        for (DownloadModel downloadModel : downloadModelList) {
            Download download = new Download();

            download.setId(downloadModel.getId());
            download.setName(downloadModel.getName());
            download.setType(downloadModel.getType());
            download.setStatus(downloadModel.getStatus());
            download.setUser(downloadModel.getUser());
            download.setDatetime(downloadModel.getDatetime());
            downloadList.add(download);
        }
        return downloadList;
    }

    public Download updateStatus(String id, String status) {
        Download download = new Download();

        download.setId(id);
        download.setStatus(status);

        download = adapter.updateStatus(download);
        return download;
    }

    public HttpEntity<byte[]> getDownloadById(String id) {
        return adapter.getById(id);
    }

    public String deleteDownloadById(String id) {
        return adapter.deleteById(id);
    }

    public List<Download> getByStatus(String status) {
        List<Download> downloadList = new ArrayList<Download>();
        List<DownloadModel> downloadModelList = new ArrayList<DownloadModel>();

        downloadModelList = adapter.getByStatus(status);
        for (DownloadModel downloadModel : downloadModelList) {
            Download download = new Download();

            download.setId(downloadModel.getId());
            download.setName(downloadModel.getName());
            download.setType(downloadModel.getType());
            download.setStatus(downloadModel.getStatus());
            download.setUser(downloadModel.getUser());
            download.setDatetime(downloadModel.getDatetime());
            downloadList.add(download);
        }
        return downloadList;
    }

    public Download updateDownload(MultipartFile multipartFile,
                                 String id,
                                 String name,
                                 String type,
                                 String user,
                                 String status) {

        Download download = new Download();
        download.setId(id);
        download.setName(name);
        download.setType(type);
        download.setUser(user);
        download.setStatus(status);
        download.setDatetime(LocalDateTime.now());

        try {
            return adapter.updateDownload(download,multipartFile);
        } catch (Exception exception) {
            exception.printStackTrace();
            return null;
        }
    }
}
