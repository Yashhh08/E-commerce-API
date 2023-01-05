package com.masai.exception;

import org.springframework.data.crossstore.*;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.*;

import java.time.*;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(CustomException.class)
    public ResponseEntity<MyErrorDetails> CustomExceptionHandler(CustomException e, WebRequest req){

        MyErrorDetails err = new MyErrorDetails();
        err.setLocalDateTime(LocalDateTime.now());
        err.setMessage(e.getMessage());
        err.setDetails(req.getDescription(false));

        return new ResponseEntity<>(err,HttpStatus.NOT_FOUND);

    }

    @ExceptionHandler(ChangeSetPersister.NotFoundException.class)
    public ResponseEntity<MyErrorDetails> NotFoundExceptionHandler(ChangeSetPersister.NotFoundException e, WebRequest req){

        MyErrorDetails err = new MyErrorDetails();
        err.setLocalDateTime(LocalDateTime.now());
        err.setMessage(e.getMessage());
        err.setDetails(req.getDescription(false));

        return new ResponseEntity<>(err,HttpStatus.NOT_FOUND);

    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<MyErrorDetails> ExceptionHandler(Exception e, WebRequest req){

        MyErrorDetails err = new MyErrorDetails();
        err.setLocalDateTime(LocalDateTime.now());
        err.setMessage(e.getMessage());
        err.setDetails(req.getDescription(false));

        return new ResponseEntity<>(err,HttpStatus.NOT_FOUND);

    }

}
