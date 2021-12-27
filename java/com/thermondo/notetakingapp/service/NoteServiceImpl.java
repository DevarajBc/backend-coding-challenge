package com.thermondo.notetakingapp.service;

import com.thermondo.notetakingapp.exception.InvalidUserSessionException;
import com.thermondo.notetakingapp.exception.ResourceNotFoundException;
import com.thermondo.notetakingapp.model.UserContext;
import com.thermondo.notetakingapp.model.entities.Note;
import com.thermondo.notetakingapp.model.entities.Session;
import com.thermondo.notetakingapp.model.entities.User;
import com.thermondo.notetakingapp.repository.NoteRepository;
import com.thermondo.notetakingapp.repository.SessionRepository;
import com.thermondo.notetakingapp.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class NoteServiceImpl implements NoteService{

    @Autowired
    NoteRepository noteRepository;

    @Autowired
    SessionRepository sessionRepository;

    @Autowired
    UserRepository userRepository;

    /**
     * Return all the notes in the system.
     * @return
     */
    @Override
    public List<Note> findAllNotes() {
        Optional<List<Note>> notes = Optional.of(noteRepository.findAll());
        return notes.get();
    }

    /**
     * Create a note for the given user.
     * @param userContext
     * @return
     * @throws InvalidUserSessionException
     */
    @Override
    public Note createNote(UserContext userContext) throws InvalidUserSessionException {
        validateUserSession(userContext);
        User user = userRepository.findByLoginName(userContext.getLoginName());
        Note note = userContext.getNote();
        note.setUserId(user.getUserId());
        return noteRepository.save(note);
    }

    /**
     * Retrieve all the notes belong to the user.
     * @param userContext
     * @return
     * @throws InvalidUserSessionException
     */
    @Override
    public List<Note> findByUserId(UserContext userContext) throws InvalidUserSessionException {
        validateUserSession(userContext);
        User user = userRepository.findByLoginName(userContext.getLoginName());
        List<Note> notes =  noteRepository.findByUserId(user.getUserId());
        if(notes == null && notes.isEmpty()) {
            throw new ResourceNotFoundException("User", "id", user.getUserId());
        }
        return notes;
    }

    /**
     * Update all the notes for the given user.
     * @param userContext
     * @return
     * @throws InvalidUserSessionException
     */
    @Override
    public List<Note> updateNote(UserContext userContext) throws InvalidUserSessionException {
        validateUserSession(userContext);
        //TODO: process PUT request
        User user = userRepository.findByLoginName(userContext.getLoginName());
        List<Note> notes = noteRepository.findByUserId(user.getUserId());
        if(notes == null && notes.isEmpty()) {
           throw new ResourceNotFoundException("User", "id", user.getUserId());
        }
       for(Note note : notes) {
           note.setTitle(userContext.getNote().getTitle());
           note.setBody(userContext.getNote().getBody());
           noteRepository.save(note);
       }
        return notes;
    }

    /**
     * Delete all the notes for the given user.
     * @param userContext
     * @return
     * @throws InvalidUserSessionException
     */
    @Override
    public ResponseEntity<?> delete(UserContext userContext) throws InvalidUserSessionException {
        validateUserSession(userContext);
        User user = userRepository.findByLoginName(userContext.getLoginName());
        List<Note> notes = noteRepository.findByUserId(user.getUserId());
        if(notes == null && notes.isEmpty()) {
            throw new ResourceNotFoundException("User", "id", user.getUserId());
        }
        for(Note note : notes) {
            note.setTitle(userContext.getNote().getTitle());
            note.setBody(userContext.getNote().getBody());
            noteRepository.delete(note);
        }

        return ResponseEntity.ok().build();
    }

    /**
     * Retrieve all the notes for the given tag.
     * @param tag
     * @return
     */
    @Override
    public List<Note> retrieveByTag(String tag) {
        return noteRepository.retrieveByTag(tag);
    }

    /**
     * Validates the user session.
     * @param userContext
     * @throws InvalidUserSessionException
     */
    private void validateUserSession(UserContext userContext) throws InvalidUserSessionException {
        Session session = sessionRepository.findBySessionToken(userContext.getSessionToken());
        if(session == null)
        {
            throw new InvalidUserSessionException(" Login Name ",userContext.getLoginName());
        }
        if(session != null && session.getExpiryTime() < System.currentTimeMillis()) {
            throw new InvalidUserSessionException(" Expiry Time ",session.getExpiryTime());
        }
    }
}
