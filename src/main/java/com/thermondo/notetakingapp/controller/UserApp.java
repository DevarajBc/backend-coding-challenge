package com.thermondo.notetakingapp.controller;

import com.thermondo.notetakingapp.model.UserContext;
import com.thermondo.notetakingapp.model.entities.Session;
import com.thermondo.notetakingapp.model.entities.User;
import com.thermondo.notetakingapp.repository.SessionRepository;
import com.thermondo.notetakingapp.repository.UserRepository;
import com.thermondo.notetakingapp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.SecureRandom;
import java.util.Base64;

@RestController
@RequestMapping("/user")
public class UserApp {

    @Autowired
    UserService userService;

    /**
     *
     * @param userRequest
     * @return
     */
    @RequestMapping("/register")
    public User register(@RequestBody User userRequest) {

        return userService.register(userRequest);
    }

    /**
     *
     * @param userRequest
     * @return
     */
    @RequestMapping("/login")
    public UserContext login(@RequestBody User userRequest) {
        return userService.login(userRequest);
    }


}
