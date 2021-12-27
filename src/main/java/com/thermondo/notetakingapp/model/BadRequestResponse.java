package com.thermondo.notetakingapp.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.Map;

@AllArgsConstructor
@Getter
@Setter
public class BadRequestResponse {
    private Map<String, String> data;
}
