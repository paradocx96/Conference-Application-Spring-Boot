package com.rhna.conference.domain;

import java.util.List;
import java.util.Optional;

public interface KeyNoteDataAdapter {
	
	KeyNote save(KeyNote keyNote);

	List<KeyNote> getAll();

	void deleteById(String id);

	KeyNote update(KeyNote keyNote);

	List<KeyNote> getById(String id);

	KeyNote updateStatus(KeyNote keyNote);

	List<KeyNote> getByStatus(String status);
}
