package com.rhna.conference.api;

import com.rhna.conference.domain.KeyNote;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rhna.conference.domain.KeyNoteDataAdapter;

import java.time.LocalDateTime;

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
	
	
}
