package com.rhna.conference.api;

import com.rhna.conference.domain.About;
import com.rhna.conference.domain.AboutDataAdapter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class AboutApi {

    private final AboutDataAdapter adapter;

    @Autowired
    public AboutApi(AboutDataAdapter adapter) {
        this.adapter = adapter;
    }

    // Save about details method
    public About saveAbout(About about) {
        // Assign current date and time to about object
        about.setDatetime(LocalDateTime.now());

        // Calling saveAbout method in Adapter class and return value
        about = adapter.saveAbout(about);
        return about;
    }

    // Getting all about method
    public List<About> getAllAbout(){
        // Getting values as array list
        return new ArrayList<>(adapter.getAllAbout());
    }

    // About details get by id method
    public List<About> getByIdAbout(String id) {
        // calling getbyid method using parameter as id
        return adapter.getByIdAbout(id);
    }

    // Delete about details by id method
    public void deleteByIdAbout(String id) {
        // Calling delete method, id as parameter
        adapter.deleteByIdAbout(id);
    }

    // Update about method
    public About updateAbout(About about) {
        // Calling update method passing about object
        return adapter.updateAbout(about);
    }
}
