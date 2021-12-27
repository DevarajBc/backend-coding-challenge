package com.thermondo.notetakingapp.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class AuthenticationRequest implements Serializable {

    private String username;
    private String password;



}
