package com.teste.attornatus.api.error;

import java.util.List;

public class ApiErrorResponse {

    List<ApiError> apiErrorList;

    public ApiErrorResponse() {
    }

    public ApiErrorResponse(List<ApiError> apiErrorList) {
        this.apiErrorList = apiErrorList;
    }

    public List<ApiError> getApiErrorList() {
        return apiErrorList;
    }

    public void setApiErrorList(List<ApiError> apiErrorList) {
        this.apiErrorList = apiErrorList;
    }
}
