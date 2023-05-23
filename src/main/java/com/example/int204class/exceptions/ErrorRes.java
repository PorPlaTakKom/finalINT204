package com.example.int204class.exceptions;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Getter
@Setter
@RequiredArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ErrorRes {

    private final int status;
    private final String message;

    private final String instance;
    private String stackTrace;

    private List<ValidateionError> errors;


    public void addValidationError(String field, String message){
        if(Objects.isNull(errors)){
            errors = new ArrayList<>();
        }
        errors.add(new ValidateionError(field, message));
    }

    @Getter
    @Setter
    @RequiredArgsConstructor
    private static class ValidateionError {
        private final String field;
        private final String message;
    }
}
