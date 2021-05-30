package com.rhna.conference.controller;

import com.rhna.conference.api.KeyNoteApi;
import com.rhna.conference.domain.KeyNote;
import com.rhna.conference.dto.KeyNoteDto;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*", allowedHeaders = "*", exposedHeaders = "*")
@RestController
@RequestMapping("/keynote")
public class KeyNoteEndpoint {

    private final KeyNoteApi keyNoteApi;

    @Autowired
    public KeyNoteEndpoint(KeyNoteApi keyNoteApi) {
        this.keyNoteApi = keyNoteApi;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public KeyNote addKeyNote(@RequestBody KeyNoteDto keyNoteDto) {
        // Create keynote object
        KeyNote keyNote = new KeyNote();

        // Set values to keynote object
        keyNote.setSpeakername(keyNoteDto.getSpeakername());
        keyNote.setSpeakertype(keyNoteDto.getSpeakertype());
        keyNote.setOrganization(keyNoteDto.getOrganization());
        keyNote.setDescription(keyNoteDto.getDescription());
        keyNote.setStatus("Inactive");
        keyNote.setUser(keyNoteDto.getUser());

        // Calling addKeyNote method
        return keyNoteApi.addKeyNote(keyNote);
    }

    @GetMapping
    public List<KeyNote> getKeyNotes(){
        return keyNoteApi.getKeyNotes();
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteKeyNoteById(@PathVariable String id) {
        // Getting id as parameter and calling delete method
        keyNoteApi.deleteKeyNote(id);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public KeyNote updateKeyNote(@RequestBody KeyNoteDto keyNoteDto, @PathVariable String id) {
        // Creating keynote object
        KeyNote keyNote = new KeyNote();

        // Assign keynote id getting from path variable
        keyNote.setId(id);

        // Set values to keynote object
        keyNote.setSpeakername(keyNoteDto.getSpeakername());
        keyNote.setSpeakertype(keyNoteDto.getSpeakertype());
        keyNote.setOrganization(keyNoteDto.getOrganization());
        keyNote.setDescription(keyNoteDto.getDescription());
        keyNote.setStatus(keyNoteDto.getStatus());
        keyNote.setUser(keyNoteDto.getUser());

        // Calling updateKeyNote method
        return keyNoteApi.updateKeyNote(keyNote);
    }

    @GetMapping("/get-by-id/{id}")
    @ResponseStatus(HttpStatus.OK)
    public List<KeyNote> getKeyNoteById(@PathVariable String id) {
        // Calling get keynote by id method using parameter as path variable id
        return keyNoteApi.getKeyNoteById(id);
    }

    @GetMapping("/get-by-status/{status}")
    @ResponseStatus(HttpStatus.OK)
    public List<KeyNote> getKeyNoteByStatus(@PathVariable String status) {
        // Calling get keynotes by status method passing status as parameter
        return keyNoteApi.getKeyNoteByStatus(status);
    }

    @PutMapping("/update-status/{id}")
    @ResponseStatus(HttpStatus.OK)
    public KeyNote updateKeyNoteStatus(@RequestBody KeyNoteDto keyNoteDto, @PathVariable String id) {
        // Creating keynote object
        KeyNote keyNote = new KeyNote();

        // Set values to keynote object
        keyNote.setId(id);
        keyNote.setStatus(keyNoteDto.getStatus());

        // Calling update status method passing keynote object
        return keyNoteApi.updateStatus(keyNote);
    }
}
