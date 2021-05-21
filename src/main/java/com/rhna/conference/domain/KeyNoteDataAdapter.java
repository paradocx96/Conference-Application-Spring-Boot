package com.rhna.conference.domain;

import java.util.List;

public interface KeyNoteDataAdapter {
	
	KeyNote save(KeyNote keyNote);

	List<KeyNote> getall();
}
