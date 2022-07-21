package com.teste.attornatus.api.exception;


import com.teste.attornatus.api.exception.ResourceNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

import java.util.ArrayList;
import java.util.List;

@RestControllerAdvice
public class ExceptionHandler {


    /*
    @org.springframework.web.bind.annotation.ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ApiErrorResponse invalidArgument(MethodArgumentNotValidException exception){

        List<ApiError> apiErrorList = exception
                .getBindingResult()
                .getFieldErrors()
                .stream()
                .map(e -> new ApiError(e.getCode(), e.getDefaultMessage()))
                .collect(Collectors.toList());

        return new ApiErrorResponse(apiErrorList);
    }

    @org.springframework.web.bind.annotation.ExceptionHandler(ResourceNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ResponseEntity<ApiErrorResponse> objectNotFound(Exception exception, WebRequest webRequest){
       ApiError apiError = new ApiError(String.valueOf(HttpStatus.NOT_FOUND),
               exception.getMessage());

       List<ApiError> list = new ArrayList<>();
       list.add(apiError);

       return new ResponseEntity<>(new ApiErrorResponse(list),HttpStatus.NOT_FOUND);
    }

    @org.springframework.web.bind.annotation.ExceptionHandler(MethodArgumentTypeMismatchException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<ApiErrorResponse> uriException(Exception exception, WebRequest webRequest){
        ApiError apiError = new ApiError(String.valueOf(HttpStatus.BAD_REQUEST),
                "nao existe recurso que atenda a uri informada : "
                        + webRequest.getDescription(false));

        List<ApiError> list = new ArrayList<>();
        list.add(apiError);

        return new ResponseEntity<>(new ApiErrorResponse(list),HttpStatus.BAD_REQUEST);
    }

    @org.springframework.web.bind.annotation.ExceptionHandler(MissingServletRequestParameterException.class)
    public ResponseEntity<ApiErrorResponse> parameterNotInformed(Exception exception, WebRequest webRequest){
        ApiError apiError = new ApiError(String.valueOf(HttpStatus.BAD_REQUEST),
                "nao existe recurso que atenda a uri informada : "
                        + webRequest.getDescription(false));

        List<ApiError> list = new ArrayList<>();
        list.add(apiError);

        return new ResponseEntity<>(new ApiErrorResponse(list),HttpStatus.NOT_FOUND);
    }

    @org.springframework.web.bind.annotation.ExceptionHandler(ServiceException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ResponseEntity<ApiErrorResponse> invalidOption(Exception exception, WebRequest webRequest){
        ApiError apiError = new ApiError(String.valueOf(HttpStatus.NOT_FOUND),
                exception.getMessage());

        List<ApiError> list = new ArrayList<>();
        list.add(apiError);

        return new ResponseEntity<>(new ApiErrorResponse(list),HttpStatus.NOT_FOUND);
    }

    //HttpMessageNotReadableException - erro para tratar
    @org.springframework.web.bind.annotation.ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<ApiErrorResponse> httpMethodErrord(Exception exception, WebRequest webRequest){
        ApiError apiError = new ApiError(String.valueOf(HttpStatus.BAD_REQUEST),
                "nao existe recurso que atenda a uri informada : "
                        + webRequest.getDescription(false));

        List<ApiError> list = new ArrayList<>();
        list.add(apiError);

        return new ResponseEntity<>(new ApiErrorResponse(list),HttpStatus.NOT_FOUND);
    }

    @org.springframework.web.bind.annotation.ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    public ResponseEntity<ApiErrorResponse> methodNotSuported(Exception exception, WebRequest webRequest){
        ApiError apiError = new ApiError(String.valueOf(HttpStatus.BAD_REQUEST),
                "nao existe recurso que atenda a uri informada : "
                        + webRequest.getDescription(false));

        List<ApiError> list = new ArrayList<>();
        list.add(apiError);

        return new ResponseEntity<>(new ApiErrorResponse(list),HttpStatus.NOT_FOUND);
    }




    @org.springframework.web.bind.annotation.ExceptionHandler(ServletException.class)
    public ResponseEntity<ApiErrorResponse> httpErrors(ServletException exception, WebRequest webRequest){
        ApiError apiError = new ApiError(
                "nao existe recurso que atenda a uri informada : "
                        + webRequest.getDescription(false));

        List<ApiError> list = new ArrayList<>();
        list.add(apiError);

        return new ResponseEntity<>(new ApiErrorResponse(list),HttpStatus.BAD_REQUEST);
    }



    @org.springframework.web.bind.annotation.ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ApiErrorResponse> objectNotFound(Exception exception, WebRequest webRequest){
        ApiError apiError = new ApiError(webRequest.getDescription(true),
                exception.getMessage());

        List<ApiError> list = new ArrayList<>();
        list.add(apiError);

        return new ResponseEntity<>(new ApiErrorResponse(list),HttpStatus.NOT_FOUND);
    }


    @org.springframework.web.bind.annotation.ExceptionHandler(RuntimeException.class)
    public ResponseEntity<ApiErrorResponse> runtimeError(Exception exception, WebRequest webRequest){
        ApiError apiError = new ApiError(String.valueOf(HttpStatus.NOT_FOUND),
                exception.getLocalizedMessage());

        List<ApiError> list = new ArrayList<>();
        list.add(apiError);

        return new ResponseEntity<>(new ApiErrorResponse(list),HttpStatus.NOT_FOUND);
    }

    */



}
