package br.com.starwars.exception;

import static org.springframework.http.HttpStatus.CONFLICT;
import static org.springframework.http.HttpStatus.NOT_FOUND;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

@RestControllerAdvice
public class GlobalExceptionHandler {

	private static final Logger LOGGER = LoggerFactory.getLogger(GlobalExceptionHandler.class);
	
    @ExceptionHandler(value = { PlanetAlreadyExistsException.class })
    public ResponseEntity<?> genericConflict(Exception e, WebRequest req) {
    	
    	LOGGER.error(e.getMessage());
    	
        return ResponseEntity.status(CONFLICT).body(e.getMessage());
    }
	
    @ExceptionHandler(value = { PlanetNotFoundException.class, PlanetSwapiNotFoundException.class })
    public ResponseEntity<?> genericNotFound(Exception e, WebRequest req) {
    	
    	LOGGER.error(e.getMessage());    	
    	
        return ResponseEntity.status(NOT_FOUND).body(e.getMessage());
    }
}
