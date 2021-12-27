package com.thermondo.notetakingapp.controller;

import com.thermondo.notetakingapp.exception.InvalidUserSessionException;
import com.thermondo.notetakingapp.model.UserContext;
import com.thermondo.notetakingapp.model.entities.Note;
import com.thermondo.notetakingapp.service.CustomizedNotesServiceImpl;
import com.thermondo.notetakingapp.service.NoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api")
public class NoteApp {

    @Autowired
    NoteService noteService;

    @Autowired
    CustomizedNotesServiceImpl customizedNotesService;

    /**
     *
     * @return
     */
    @GetMapping("/notes/all")
    // in full: @RequestMapping(value="/notes", method=RequestMethod.GET).
    public List<Note> getAllNotes() {
        // Get all notes
        return  noteService.findAllNotes();
    }

    /**
     *
     * @param userContext
     * @return
     * @throws InvalidUserSessionException
     */
    @PostMapping("/notes")
    public Note creaNote(@RequestBody UserContext userContext) throws InvalidUserSessionException {

        // Create a new note
        // @Valid makes sure the request body is valid
        // @RequestBody binds the request body within the method parameter
      return  noteService.createNote(userContext);
    }


    @GetMapping("/notes")
    public List<Note> getNoteById(@RequestBody UserContext userContext) throws InvalidUserSessionException {
        // Get a single note
        return noteService.findByUserId(userContext);
    }

    /**
     *
     * @param userContext
     * @return
     * @throws Exception
     */
    @PutMapping("/notes")
    public List<Note> updateNote(@RequestBody UserContext userContext) throws Exception {
        // Update a note
      return noteService.updateNote(userContext);
    }


    /**
     *
     * @param userContext
     * @return
     * @throws Exception
     */
    @DeleteMapping("/notes")
    public ResponseEntity<?> deleteNote(@RequestBody UserContext userContext) throws Exception {
        // Delete a note
        return noteService.delete(userContext);
    }

    /**
     *
     * @param tag
     * @return
     */
    @GetMapping("/notes/tags/{tag}")
    public List<Note> retrieveByTag(@PathVariable(value = "tag") String tag) {
        // Get notes for a tag.
        return noteService.retrieveByTag(tag);
    }

    /**
     *
     * @param keyword
     * @return
     */
    @GetMapping("/notes/keywords/{keyword}")
    public List<Note> retrieveNotesByKeyword(@PathVariable(value = "keyword") String keyword) {
        return customizedNotesService.search(keyword);
    }

}
