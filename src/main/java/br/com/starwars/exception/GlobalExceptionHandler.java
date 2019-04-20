package br.com.starwars.exception;

import static br.com.starwars.component.MessageBuilder.*;
import static org.springframework.http.HttpStatus.CONFLICT;
import static org.springframework.http.HttpStatus.NOT_FOUND;
import static org.springframework.http.ResponseEntity.badRequest;
import static org.springframework.http.ResponseEntity.status;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    @ExceptionHandler(value = { PlanetAlreadyExistsException.class })
    public ResponseEntity<?> genericConflict(Exception e, WebRequest req) {
    	
    	log.error(e.getMessage());
    	
        return status(CONFLICT).body(message(e.getMessage()));
    }
	
    @ExceptionHandler(value = { PlanetNotFoundException.class, PlanetSwapiNotFoundException.class })
    public ResponseEntity<?> genericNotFound(Exception e, WebRequest req) {

        log.error(e.getMessage());
    	
        return status(NOT_FOUND).body(message(e.getMessage()));
    }
    
    @ExceptionHandler(value = { FieldRequiredException.class })
    public ResponseEntity<?> genericBadRequest(Exception e, WebRequest req) {

        log.error(e.getMessage());
    	
        return badRequest().body(messages(e.getMessage().split(", ")));
    }

    @ExceptionHandler(value = { HttpMessageNotReadableException.class })
    public ResponseEntity<?> errorNotRecognized(Exception e, WebRequest req) {

        log.error(e.getMessage());

        return badRequest().body(message(ERROR_NOT_RECOGNIZED));
    }
}