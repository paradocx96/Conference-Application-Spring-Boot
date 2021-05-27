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
        KeyNote keyNote = new KeyNote();
        keyNote.setSpeakername(keyNoteDto.getSpeakername());
        keyNote.setSpeakertype(keyNoteDto.getSpeakertype());
        keyNote.setOrganization(keyNoteDto.getOrganization());
        keyNote.setDescription(keyNoteDto.getDescription());
        keyNote.setStatus("Inactive");
        keyNote.setUser(keyNoteDto.getUser());

        return keyNoteApi.addKeyNote(keyNote);
    }

    @GetMapping
    public List<KeyNote> getKeyNotes(){
        return keyNoteApi.getKeyNotes();
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteKeyNoteById(@PathVariable String id) {
        keyNoteApi.deleteKeyNote(id);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public KeyNote updateKeyNote(@RequestBody KeyNoteDto keyNoteDto, @PathVariable String id) {
        KeyNote keyNote = new KeyNote();

        keyNote.setId(id);
        keyNote.setSpeakername(keyNoteDto.getSpeakername());
        keyNote.setSpeakertype(keyNoteDto.getSpeakertype());
        keyNote.setOrganization(keyNoteDto.getOrganization());
        keyNote.setDescription(keyNoteDto.getDescription());
        keyNote.setStatus(keyNoteDto.getStatus());
        keyNote.setUser(keyNoteDto.getUser());

        return keyNoteApi.updateKeyNote(keyNote);
    }

    @GetMapping("/get-by-id/{id}")
    @ResponseStatus(HttpStatus.OK)
    public List<KeyNote> getKeyNoteById(@PathVariable String id) {
        return keyNoteApi.getKeyNoteById(id);
    }

    @GetMapping("/get-by-status/{status}")
    @ResponseStatus(HttpStatus.OK)
    public List<KeyNote> getKeyNoteByStatus(@PathVariable String status) {
        return keyNoteApi.getKeyNoteByStatus(status);
    }

    @PutMapping("/update-status/{id}")
    @ResponseStatus(HttpStatus.OK)
    public KeyNote updateKeyNoteStatus(@RequestBody KeyNoteDto keyNoteDto, @PathVariable String id) {
        KeyNote keyNote = new KeyNote();
        keyNote.setId(id);
        keyNote.setStatus(keyNoteDto.getStatus());

        return keyNoteApi.updateStatus(keyNote);
    }
}
