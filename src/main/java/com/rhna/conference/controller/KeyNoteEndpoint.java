package com.rhna.conference.controller;

import com.rhna.conference.api.KeyNoteApi;
import com.rhna.conference.domain.KeyNote;
import com.rhna.conference.dto.KeyNoteDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

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
        keyNote.setUser(keyNoteDto.getUser());

        return keyNoteApi.addKeyNote(keyNote);
    }

}
