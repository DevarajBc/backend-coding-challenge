package com.thermondo.notetakingapp.service;

import com.thermondo.notetakingapp.model.UserContext;
import com.thermondo.notetakingapp.model.entities.User;

public interface UserService {

     User register(User userRequest);

     UserContext login(User userRequest);
}
