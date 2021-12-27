package com.thermondo.notetakingapp.exception;

import lombok.Getter;

@Getter
public class InvalidUserSessionException extends  RuntimeException{
    private String fieldName;
    private Object fieldValue;

    public InvalidUserSessionException( String fieldName, Object fieldValue) {
        super(String.format("%s not found  %s ", fieldName, fieldValue));
        this.fieldName = fieldName;
        this.fieldValue = fieldValue;
    }
}
