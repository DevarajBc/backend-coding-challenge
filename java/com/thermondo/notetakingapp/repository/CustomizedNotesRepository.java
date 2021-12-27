package com.thermondo.notetakingapp.repository;

import com.thermondo.notetakingapp.model.entities.Note;

import java.util.List;

public interface CustomizedNotesRepository {
    List<Note> search(String keyword);
}
