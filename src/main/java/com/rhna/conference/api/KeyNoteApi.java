package com.rhna.conference.api;

import com.rhna.conference.domain.KeyNoteDataAdapter;
import com.rhna.conference.domain.KeyNote;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.*;

@Service
public class KeyNoteApi {

    private final KeyNoteDataAdapter keyNoteDataAdapter;

    @Autowired
    public KeyNoteApi(KeyNoteDataAdapter keyNoteDataAdapter) {
        this.keyNoteDataAdapter = keyNoteDataAdapter;
    }

    // Add new keynote method
    public KeyNote addKeyNote(KeyNote keyNote) {
        // Assign current date and time to keynote object
        keyNote.setDatetime(LocalDateTime.now());

        // Calling save method in Adapter calss and return value
        keyNote = keyNoteDataAdapter.save(keyNote);
        return keyNote;
    }

    // Getting all keynote method
    public List<KeyNote> getKeyNotes() {
        // Getting values as array list
        return new ArrayList<>(keyNoteDataAdapter.getAll());
    }

    // Delete keynote by id
    public void deleteKeyNote(String id) {
        // Calling delete method id as parameter
        keyNoteDataAdapter.deleteById(id);
    }

    // Update keynote method
    public KeyNote updateKeyNote(KeyNote keyNote) {
        // calling update method
        return keyNoteDataAdapter.update(keyNote);
    }

    // Keynote get by id method
    public List<KeyNote> getKeyNoteById(String id) {
        // calling getbyid method using parameter as id
        return keyNoteDataAdapter.getById(id);
    }

    // Getting all keynote by status method
    public List<KeyNote> getKeyNoteByStatus(String status) {
        // Getting values as array list. Passing status as parameter to call.
        return new ArrayList<>(keyNoteDataAdapter.getByStatus(status));
    }

    // Update keynote's status method
    public KeyNote updateStatus(KeyNote keyNote) {
        // Calling update status method passing keynote object
        return keyNoteDataAdapter.updateStatus(keyNote);
    }
}
