package com.rhna.conference.domain;

import java.util.List;
import java.util.Optional;

public interface KeyNoteDataAdapter {
	
	KeyNote save(KeyNote keyNote);

	List<KeyNote> getAll();

	void deleteById(String id);

	KeyNote update(KeyNote keyNote);

	Optional<KeyNote> getKeyNoteById(String id);
}
