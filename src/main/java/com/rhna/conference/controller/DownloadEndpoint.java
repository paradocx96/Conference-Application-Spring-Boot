package com.rhna.conference.controller;

import com.rhna.conference.api.DownloadApi;
import com.rhna.conference.domain.Download;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/download")
@CrossOrigin(origins = "*", allowedHeaders = "*", exposedHeaders = "*")
public class DownloadEndpoint {

    private final DownloadApi api;

    @Autowired
    public DownloadEndpoint(DownloadApi api) {
        this.api = api;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public String addDownload(@RequestParam("file") MultipartFile multipartFile,
                              @RequestParam("name") String name,
                              @RequestParam("type") String type,
                              @RequestParam("user") String user) {
        String ID = api.addDownload(multipartFile, name, type, user);
        return ID;
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<Download> getAllDownloads() {
        return api.getAll();
    }

    @PutMapping("/update-status")
    @ResponseStatus(HttpStatus.OK)
    public Download updateStatus(@RequestParam("id") String id,
                                 @RequestParam("status") String status) {
        return api.updateStatus(id, status);
    }

    @PostMapping("/download-id/{id}")
    @ResponseStatus(HttpStatus.OK)
    public HttpEntity<byte[]> getFileById(@PathVariable String id) {
        return api.getDownloadById(id);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public String deleteFileById(@PathVariable String id) {
        return api.deleteDownloadById(id);
    }
}