package com.rhna.conference.controller;

import com.rhna.conference.api.WorkshopAPI;
import com.rhna.conference.domain.Workshop;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*", maxAge = 3600)
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
    public Workshop addWorkshop(@RequestBody Workshop workshop) {
        System.out.println(workshop);
        return workshopAPI.addWorkshop(workshop);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.CREATED)
    public List<Workshop> getAllWorkshops() {
        return workshopAPI.getAllWorkshops();
    }

}
