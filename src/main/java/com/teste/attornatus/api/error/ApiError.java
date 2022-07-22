package com.teste.attornatus.api.error;

import java.io.Serializable;
import java.time.Instant;


public class ApiError implements Serializable {

   private Instant timestamp;
   private Integer status;
   private String error;
   private String message;
   private String path;



    public ApiError setTimeStamp(Instant timeStamp){
        this.timestamp = timeStamp;
        return this;
    }

    public ApiError setStatus(Integer status){
        this.status = status;
        return this;
    }

    public ApiError setError(String error){
        this.error = error;
        return this;
    }

    public ApiError setMessage(String message){
        this.message = message;
        return this;
    }

    public ApiError setPath(String path){
        this.path = path;
        return this;
    }

    public Instant getTimestamp() {
        return timestamp;
    }

    public Integer getStatus() {
        return status;
    }

    public String getError() {
        return error;
    }

    public String getMessage() {
        return message;
    }

    public String getPath() {
        return path;
    }
}
