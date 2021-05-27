package com.rhna.conference.domain;

import com.rhna.conference.dal.model.DownloadModel;
import org.springframework.http.HttpEntity;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface DownloadDataAdapter {

    String save(Download download, MultipartFile multipartFile) throws IOException;

    List<DownloadModel> getAll();

    Download updateStatus(Download download);

    HttpEntity<byte[]> getById(String id);

    HttpEntity<byte[]> getByType(String type, String status);
}
