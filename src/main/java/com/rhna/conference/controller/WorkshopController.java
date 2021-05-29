package com.rhna.conference.controller;

import com.rhna.conference.api.WorkshopAPI;
import com.rhna.conference.domain.Workshop;
import org.bson.BsonBinarySubType;
import org.bson.types.Binary;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@CrossOrigin(origins = "*", maxAge = 3600, allowedHeaders = "*", exposedHeaders = "*")
//@CrossOrigin(origins = "http://localhost:1234")
@RestController
@RequestMapping("/workshop")
public class WorkshopController {

    private final WorkshopAPI workshopAPI;

    @Autowired
    public WorkshopController(WorkshopAPI workshopAPI) {
        this.workshopAPI = workshopAPI;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Workshop addWorkshop(
            @RequestParam("username") String username,
            @RequestParam("title") String title,
            @RequestParam("courseCode") String courseCode,
            @RequestParam("venue") String venue,
            @RequestParam("date") String date,
            @RequestParam("startingTime") String startingTime,
            @RequestParam("endTime") String endTime,
            @RequestParam("description") String description,
            @RequestParam("documents") MultipartFile multipartFile) throws IOException {
        Workshop workshop = new Workshop();
        workshop.setUsername(username);
        workshop.setTitle(title);
        workshop.setCourseCode(courseCode);
        workshop.setVenue(venue);
        workshop.setDate(date);
        workshop.setStartingTime(startingTime);
        workshop.setEndTime(endTime);
        workshop.setDescription(description);
        workshop.setDocuments(new Binary(BsonBinarySubType.BINARY, multipartFile.getBytes()));
        return workshopAPI.addWorkshop(workshop);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.CREATED)
    public List<Workshop> getAllWorkshops() {
        return workshopAPI.getAllWorkshops();
    }

    @PostMapping(value = "/downloadDocuments")
    public HttpEntity<byte[]> getResearchPaperFileByWorkshopId(@RequestParam("id") String id) {
        return workshopAPI.getWorkshopDocumentsByUser(id);
    }

    @PutMapping(value = "/update")
    @ResponseStatus(HttpStatus.CREATED)
    public Workshop updateWorkshop(
            @RequestParam("id") String id,
            @RequestParam("username") String username,
            @RequestParam("title") String title,
            @RequestParam("courseCode") String courseCode,
            @RequestParam("venue") String venue,
            @RequestParam("date") String date,
            @RequestParam("startingTime") String startingTime,
            @RequestParam("endTime") String endTime,
            @RequestParam("description") String description,
            @RequestParam("documents") MultipartFile multipartFile) throws IOException {
        Workshop workshop = new Workshop();
        workshop.setId(id);
        workshop.setUsername(username);
        workshop.setTitle(title);
        workshop.setCourseCode(courseCode);
        workshop.setVenue(venue);
        workshop.setDate(date);
        workshop.setStartingTime(startingTime);
        workshop.setEndTime(endTime);
        workshop.setDescription(description);
        workshop.setDocuments(new Binary(BsonBinarySubType.BINARY, multipartFile.getBytes()));
        return workshopAPI.updateWorkshop(workshop);
    }
}
