package com.thermondo.notetakingapp.repository;

import com.thermondo.notetakingapp.model.entities.Session;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SessionRepository extends JpaRepository<Session,Long> {

    public Session findBySessionToken(String sessionToken);
}
