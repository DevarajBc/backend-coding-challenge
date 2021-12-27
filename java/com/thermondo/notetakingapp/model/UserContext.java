package com.thermondo.notetakingapp.model;

import com.thermondo.notetakingapp.model.entities.Note;
import lombok.Getter;

import java.io.Serializable;

@Getter
public class UserContext implements Serializable {

    private String sessionToken;

    private String loginName;

    private Note note;

    public UserContext(String sessionToken, String loginName) {
        this.sessionToken = sessionToken;
        this.loginName = loginName;
    }


}
