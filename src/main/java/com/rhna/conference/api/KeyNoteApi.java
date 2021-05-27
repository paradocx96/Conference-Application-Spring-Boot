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

	public KeyNote addKeyNote(KeyNote keyNote) {
		keyNote.setDatetime(LocalDateTime.now());
		keyNote = keyNoteDataAdapter.save(keyNote);
		return keyNote;
	}

	public List<KeyNote> getKeyNotes(){
		return new ArrayList<>(keyNoteDataAdapter.getAll());
	}

	public void deleteKeyNote(String id) {
		keyNoteDataAdapter.deleteById(id);
	}

	public KeyNote updateKeyNote(KeyNote keyNote) {
		return keyNoteDataAdapter.update(keyNote);
	}

	public List<KeyNote> getKeyNoteById(String id) {
		return keyNoteDataAdapter.getById(id);
	}

	public List<KeyNote> getKeyNoteByStatus(String status) {
		return new ArrayList<>(keyNoteDataAdapter.getByStatus(status));
	}

	public KeyNote updateStatus(KeyNote keyNote) {
		return keyNoteDataAdapter.updateStatus(keyNote);
	}
}
