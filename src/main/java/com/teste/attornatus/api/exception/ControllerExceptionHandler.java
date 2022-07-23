package com.teste.attornatus.api.exception;


import com.teste.attornatus.api.error.ApiError;
import com.teste.attornatus.api.error.ArgumentInvalidError;
import com.teste.attornatus.api.error.FieldValidationError;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import javax.servlet.http.HttpServletRequest;
import java.time.Instant;
import java.util.List;
import java.util.stream.Collectors;

@RestControllerAdvice
public class ControllerExceptionHandler {

    @ExceptionHandler(ResourceNotFoundException.class)
    @ApiResponse(responseCode = "404", content = @Content(mediaType = "application/json",
            schema = @Schema(implementation = ApiError.class)))
    public ResponseEntity<ApiError> resourceNotFound(ResourceNotFoundException e,HttpServletRequest request){

        ApiError apiError = new ApiError()
                .setTimeStamp(Instant.now())
                .setStatus(HttpStatus.NOT_FOUND.value())
                .setError("Resource not found ")
                .setMessage(e.getMessage())
                .setPath(request.getRequestURI());

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(apiError);
    }

    @ExceptionHandler(MissingServletRequestParameterException.class)
    @ApiResponse(responseCode = "404", content = @Content(mediaType = "application/json",
            schema = @Schema(implementation = ApiError.class)))
    public ResponseEntity<ApiError> requestParameterException(MissingServletRequestParameterException e, HttpServletRequest request){

        ApiError apiError = new ApiError()
                .setTimeStamp(Instant.now())
                .setStatus(HttpStatus.NOT_FOUND.value())
                .setError("Bad Request")
                .setMessage(e.getMessage())
                .setPath(request.getRequestURI());

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(apiError);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ApiResponse(responseCode = "400", content = @Content(mediaType = "application/json",
            schema = @Schema(implementation = ArgumentInvalidError.class)))
    public ResponseEntity<ArgumentInvalidError> argumentInvalidException(MethodArgumentNotValidException exception,
                                                                         HttpServletRequest request){

        List<FieldValidationError> fieldValidationErrors = exception
                .getBindingResult()
                .getFieldErrors()
                .stream()
                .map(ex ->{
                    return  new FieldValidationError()
                            .setField(ex.getField())
                            .setMessage(ex.getDefaultMessage())
                            .setValue(String.valueOf(ex.getRejectedValue()));
                }).collect(Collectors.toList());



        ApiError apiError = new ApiError();
        apiError.setTimeStamp(Instant.now())
                .setStatus(HttpStatus.BAD_REQUEST.value())
                .setError("argument invalid")
                .setMessage("Validation failed for argument")
                .setPath(request.getRequestURI());

        ArgumentInvalidError argumentInvalidError = new ArgumentInvalidError();
        argumentInvalidError.setApiError(apiError);
        argumentInvalidError.setFieldValidationErrors(fieldValidationErrors);


        return  ResponseEntity.status(HttpStatus.BAD_REQUEST).body(argumentInvalidError);
    }

    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    @ApiResponse(responseCode = "405", content = @Content(mediaType = "application/json",
            schema = @Schema(implementation = ApiError.class)))
    public ResponseEntity<ApiError> resourceNotFound(HttpRequestMethodNotSupportedException e,HttpServletRequest request){

        ApiError apiError = new ApiError()
                .setTimeStamp(Instant.now())
                .setStatus(HttpStatus.METHOD_NOT_ALLOWED.value())
                .setError("Method Not Allowed ")
                .setMessage(e.getMessage())
                .setPath(request.getRequestURI());

        return ResponseEntity.status(HttpStatus.METHOD_NOT_ALLOWED).body(apiError);
    }

    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    @ApiResponse(responseCode = "404", content = @Content(mediaType = "application/json",
            schema = @Schema(implementation = ApiError.class)))
    public ResponseEntity<ApiError> typeMismatchException(MethodArgumentTypeMismatchException e, HttpServletRequest request){

        ApiError apiError = new ApiError()
                .setTimeStamp(Instant.now())
                .setStatus(HttpStatus.NOT_FOUND.value())
                .setError("failed to convert ")
                .setMessage(e.getMessage())
                .setPath(request.getRequestURI());

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(apiError);
    }

    @ExceptionHandler(ResultUniqueException.class)
    @ApiResponse(responseCode = "400", content = @Content(mediaType = "application/json",
            schema = @Schema(implementation = ApiError.class)))
    public ResponseEntity<ApiError> resultUniqueException(ResultUniqueException e,HttpServletRequest request){

        ApiError apiError = new ApiError()
                .setTimeStamp(Instant.now())
                .setStatus(HttpStatus.BAD_REQUEST.value())
                .setError("unique constraint violation")
                .setMessage(e.getMessage())
                .setPath(request.getRequestURI());

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(apiError);
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    @ApiResponse(responseCode = "400", content = @Content(mediaType = "application/json",
            schema = @Schema(implementation = ApiError.class)))
    public ResponseEntity<ApiError> resultUniqueException(HttpMessageNotReadableException e,HttpServletRequest request){

        ApiError apiError = new ApiError()
                .setTimeStamp(Instant.now())
                .setStatus(HttpStatus.BAD_REQUEST.value())
                .setError("\"Required request body is missing")
                .setMessage(e.getMessage())
                .setPath(request.getRequestURI());

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(apiError);
    }


    //HttpRequestMethodNotSupportedException
    //MissingServletRequestParameterException
    //(MethodArgumentNotValidException
    //MethodArgumentTypeMismatchException
    //HttpMessageNotReadableException

}
