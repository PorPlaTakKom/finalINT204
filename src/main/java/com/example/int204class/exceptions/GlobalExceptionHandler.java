package com.example.int204class.exceptions;

import org.hibernate.cfg.beanvalidation.IntegrationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.io.FileNotFoundException;
import java.time.LocalDateTime;
@RestControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {
//    @ExceptionHandler({
//            java.io.FileNotFoundException.class
//    })
//    @ResponseStatus(HttpStatus.NOT_FOUND)
//    public FileException handlerFileNotFound(FileNotFoundException exception, WebRequest request){
//        return new FileException(exception.getMessage(), LocalDateTime.now().toString(), HttpStatus.NOT_FOUND.value());
//    }

    @ExceptionHandler(FileNotFoundException.class)
//    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ResponseEntity<ErrorRes> handlerFileNotFoundException(Exception ex, WebRequest request){
        System.out.println(request);
        ErrorRes errorRes = new ErrorRes(HttpStatus.INTERNAL_SERVER_ERROR.value(), ex.getMessage(), request.getDescription(false).substring(4));
        errorRes.addValidationError("Error2", "Unknown");
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorRes);
    }

    @ExceptionHandler(NullPointerException.class)
//    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ResponseEntity<ErrorRes> handlerNullPointerException(Exception ex, WebRequest request){
        System.out.println(request);
        ErrorRes errorRes = new ErrorRes(HttpStatus.INTERNAL_SERVER_ERROR.value(), ex.getMessage(), request.getDescription(false).substring(4));
        errorRes.addValidationError("Error1", "Unknown");
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorRes);
    }

    @ExceptionHandler(NumberFormatException.class)
//    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ResponseEntity<ErrorRes> handlerNumberFormatException(Exception ex, WebRequest request){
        System.out.println(request);
        ErrorRes errorRes = new ErrorRes(HttpStatus.BAD_REQUEST.value(), ex.getMessage(), request.getDescription(false).substring(4));
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorRes);
    }

    @ExceptionHandler(RuntimeException.class)
//    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ResponseEntity<ErrorRes> handlerRuntimeException(Exception ex, WebRequest request){
        System.out.println(request);
        ErrorRes errorRes = new ErrorRes(HttpStatus.BAD_REQUEST.value(), ex.getMessage(), request.getDescription(false).substring(4));
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorRes);
    }
}
