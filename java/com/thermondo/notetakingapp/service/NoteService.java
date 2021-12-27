package com.thermondo.notetakingapp.service;

import com.thermondo.notetakingapp.exception.InvalidUserSessionException;
import com.thermondo.notetakingapp.model.UserContext;
import com.thermondo.notetakingapp.model.entities.Note;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface NoteService {

    List<Note> findAllNotes();

    Note createNote(UserContext userContext) throws InvalidUserSessionException;

    List<Note> findByUserId(UserContext userContext) throws InvalidUserSessionException;

    List<Note> updateNote(UserContext userContext) throws  InvalidUserSessionException;

    ResponseEntity<?> delete(UserContext userContext) throws  InvalidUserSessionException;

    List<Note> retrieveByTag(String tag);

}
