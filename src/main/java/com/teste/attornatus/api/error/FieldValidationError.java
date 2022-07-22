package com.teste.attornatus.api.error;

import java.io.Serializable;

public class FieldValidationError implements Serializable {

    private String field;
    private String value;
    private String message;

    public FieldValidationError setField(String field){
        this.field = field;
        return this;
    }

    public FieldValidationError setValue(String value){
        this.value = value;
        return this;
    }

    public FieldValidationError setMessage(String message){
        this.message = message;
        return this;
    }

    public String getField() {
        return field;
    }

    public String getValue() {
        return value;
    }

    public String getMessage() {
        return message;
    }
}
