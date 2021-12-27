package com.thermondo.notetakingapp.repository;

import com.thermondo.notetakingapp.model.entities.Note;
import org.apache.lucene.util.QueryBuilder;
import org.hibernate.search.jpa.FullTextEntityManager;
import org.hibernate.search.jpa.Search;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

public class CustomizedNotesRepositoryImpl implements CustomizedNotesRepository{

    @PersistenceContext
    private EntityManager em;

    @Override
    public List<Note> search(String keyword) {
        FullTextEntityManager fullTextEntityManager = Search.getFullTextEntityManager(em);
       // QueryBuilder queryBuilder = fullTextEntityManager.getSearchFactory().
        return  null;
    }
}
