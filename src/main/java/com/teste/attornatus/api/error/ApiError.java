package com.teste.attornatus.api.error;

public class ApiError {

    private String status;
    private String message;




    public ApiError() {
    }

    public ApiError(String message) {
        this.message = message;
    }

    public ApiError(String status, String message) {
        this.status = status;
        this.message = message;
    }



    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }


}
