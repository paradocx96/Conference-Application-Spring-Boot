package com.rhna.conference.controller;

import com.rhna.conference.api.DownloadApi;
import com.rhna.conference.domain.Download;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@CrossOrigin(origins = "*", allowedHeaders = "*", exposedHeaders = "*")
@RestController
@RequestMapping("/download")
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
        // Declare a string varibale and assign result of calling addDownload method
        // Pass multipart file and user insert data to addDownload method
        String ID = api.addDownload(multipartFile, name, type, user);
        return ID;
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<Download> getAllDownloads() {
        // Calling get All method
        return api.getAll();
    }

    @PutMapping("/update-status")
    @ResponseStatus(HttpStatus.OK)
    public Download updateStatus(@RequestParam("id") String id,
                                 @RequestParam("status") String status) {
        // Passing id and status to update Status method
        return api.updateStatus(id, status);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public String deleteFileById(@PathVariable String id) {
        // Getting id as parameter and calling delete method
        return api.deleteDownloadById(id);
    }

    @PostMapping("/download-id/{id}")
    public HttpEntity<byte[]> getFileById(@PathVariable String id) {
        // Calling get download by id method using parameter as path variable id
        return api.getDownloadById(id);
    }

    @PostMapping("/download-id-param")
    public HttpEntity<byte[]> getFileByParamId(@RequestParam("id") String id) {
        // Calling get download by id method using parameter as form variable id
        return api.getDownloadById(id);
    }

    @GetMapping("/get-by-status/{status}")
    public List<Download> getListByStatus(@PathVariable String status) {
        // Calling get by status method passing status as parameter
        return api.getByStatus(status);
    }

    @PutMapping(value = "/update-file")
    public Download updateFileById(@RequestParam("file") MultipartFile multipartFile,
                                   @RequestParam("id") String id,
                                   @RequestParam("name") String name,
                                   @RequestParam("type") String type,
                                   @RequestParam("user") String user,
                                   @RequestParam("status") String status) {
        // Calliing update Download method with new data as parameter
        return api.updateDownload(multipartFile, id, name, type, user, status);
    }

}
