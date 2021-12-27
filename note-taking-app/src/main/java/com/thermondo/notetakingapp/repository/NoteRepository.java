package com.thermondo.notetakingapp.repository;

import com.thermondo.notetakingapp.model.entities.Note;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository // tell Spring to bootstrap the repository during component scan
public interface NoteRepository extends JpaRepository<Note, Long> {

    @Query("SELECT s FROM Note s JOIN s.tags t WHERE t = LOWER(:tag)")
     List<Note> retrieveByTag(@Param("tag") String tag);

     List<Note> findByUserId(Long userId);

}
