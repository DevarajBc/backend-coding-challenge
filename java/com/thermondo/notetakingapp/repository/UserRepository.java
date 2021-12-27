package com.thermondo.notetakingapp.repository;

import com.thermondo.notetakingapp.model.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User,Long> {

    public User findByLoginName(String loginName);
}
