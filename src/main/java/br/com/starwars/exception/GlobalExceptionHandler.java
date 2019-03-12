package br.com.starwars.exception;

import static org.springframework.http.HttpStatus.CONFLICT;
import static org.springframework.http.HttpStatus.NOT_FOUND;
import static org.springframework.http.ResponseEntity.badRequest;
import static org.springframework.http.ResponseEntity.status;

import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    @ExceptionHandler(value = { PlanetAlreadyExistsException.class })
    public ResponseEntity<?> genericConflict(Exception e, WebRequest req) {
    	
    	log.error(e.getMessage());
    	
        return status(CONFLICT).body(e.getMessage());
    }
	
    @ExceptionHandler(value = { PlanetNotFoundException.class, PlanetSwapiNotFoundException.class })
    public ResponseEntity<?> genericNotFound(Exception e, WebRequest req) {

        log.error(e.getMessage());
    	
        return status(NOT_FOUND).body(e.getMessage());
    }
    
    @ExceptionHandler(value = { FieldRequiredException.class })
    public ResponseEntity<?> genericBadRequest(Exception e, WebRequest req) {

        log.error(e.getMessage());
    	
        return badRequest().body(e.getMessage());
    }
}