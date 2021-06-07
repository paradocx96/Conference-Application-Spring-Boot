package com.rhna.conference.controller;

import com.rhna.conference.api.AboutApi;
import com.rhna.conference.domain.About;
import com.rhna.conference.dto.AboutDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/about")
@CrossOrigin(origins = "*", allowedHeaders = "*", exposedHeaders = "*")
public class AboutEndpoint {

    private final AboutApi api;

    @Autowired
    public AboutEndpoint(AboutApi api) {
        this.api = api;
    }

    @PostMapping("/add")
    @ResponseStatus(HttpStatus.CREATED)
    public About saveAboutDetails(@RequestBody AboutDto aboutDto) {
        // Create about object
        About about = new About();

        // Set values to about object
        about.setDescription(aboutDto.getDescription());
        about.setVenue(aboutDto.getVenue());
        about.setDate(aboutDto.getDate());

        // Calling saveAbout method
        return api.saveAbout(about);
    }

    @PutMapping("/update/{id}")
    @ResponseStatus(HttpStatus.OK)
    public About updateById(@RequestBody AboutDto aboutDto, @PathVariable String id) {
        // Creating about object
        About about = new About();

        // Assign about id getting from path variable
        about.setId(id);

        // Set values to about object
        about.setDescription(aboutDto.getDescription());
        about.setVenue(aboutDto.getVenue());
        about.setDate(aboutDto.getDate());

        // Calling updateAbout method
        return api.updateAbout(about);
    }

    @GetMapping("/get")
    @ResponseStatus(HttpStatus.OK)
    public List<About> getAllAbout() {
        // Calling getAllAbout method and requesting array list
        return api.getAllAbout();
    }

    @GetMapping("/get-id/{id}")
    @ResponseStatus(HttpStatus.OK)
    public List<About> getById(@PathVariable String id) {
        // Calling get by id method using parameter as path variable id
        return api.getByIdAbout(id);
    }

    @DeleteMapping("/delete/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteById(@PathVariable String id) {
        // Getting id as parameter and calling delete method
        api.deleteByIdAbout(id);
    }

}
