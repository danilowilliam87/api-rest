package com.teste.attornatus.api.error;

import java.util.List;

public class ArgumentInvalidError {

    private ApiError apiError;
    private List<FieldValidationError>fieldValidationErrors;

    public ApiError getApiError() {
        return apiError;
    }

    public void setApiError(ApiError apiError) {
        this.apiError = apiError;
    }

    public List<FieldValidationError> getFieldValidationErrors() {
        return fieldValidationErrors;
    }

    public void setFieldValidationErrors(List<FieldValidationError> fieldValidationErrors) {
        this.fieldValidationErrors = fieldValidationErrors;
    }
}
