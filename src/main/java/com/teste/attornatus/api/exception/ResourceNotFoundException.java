package com.teste.attornatus.api.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class ResourceNotFoundException extends RuntimeException{

    private String message;
    private String field;
    private String status;

    public ResourceNotFoundException() {
    }

    public ResourceNotFoundException(String message1) {
        this.message = message1;
    }

    public ResourceNotFoundException(String message, String field, String status) {
        this.message = message;
        this.field = field;
        this.status = status;
    }

    @Override
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getField() {
        return field;
    }

    public void setField(String field) {
        this.field = field;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
