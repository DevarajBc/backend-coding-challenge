package com.thermondo.notetakingapp.service;

import com.thermondo.notetakingapp.model.entities.Note;
import com.thermondo.notetakingapp.repository.CustomizedNotesRepository;
import org.hibernate.search.jpa.FullTextEntityManager;
import org.hibernate.search.jpa.Search;
import org.hibernate.search.query.dsl.QueryBuilder;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Service
public class CustomizedNotesServiceImpl implements CustomizedNotesRepository {

    @PersistenceContext
    private EntityManager em;

    @Override
    public List<Note> search(String keyword) {
        FullTextEntityManager fullTextEntityManager = Search.getFullTextEntityManager(em);
        QueryBuilder queryBuilder = fullTextEntityManager.getSearchFactory()
                .buildQueryBuilder().forEntity(Note.class).get();
        org.apache.lucene.search.Query luceneQuery = queryBuilder
                .keyword()
                .onFields("body")
                .matching(keyword)
                .createQuery();
        javax.persistence.Query jpaQuery =
                fullTextEntityManager.createFullTextQuery(luceneQuery, Note.class);
        return jpaQuery.getResultList();

    }
}
