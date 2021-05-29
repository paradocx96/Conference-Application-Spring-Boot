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

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public About saveAboutDetails(@RequestBody AboutDto aboutDto) {
        About about = new About();
        about.setDescription(aboutDto.getDescription());
        about.setVenue(aboutDto.getVenue());
        about.setDate(aboutDto.getDate());

        return api.saveAbout(about);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public About updateById(@RequestBody AboutDto aboutDto, @PathVariable String id) {
        About about = new About();
        about.setId(id);
        about.setDescription(aboutDto.getDescription());
        about.setVenue(aboutDto.getVenue());
        about.setDate(aboutDto.getDate());

        return api.updateAbout(about);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<About> getAllAbout() {
        return api.getAllAbout();
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public List<About> getById(@PathVariable String id) {
        return api.getByIdAbout(id);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteById(@PathVariable String id) {
        api.deleteByIdAbout(id);
    }

}
