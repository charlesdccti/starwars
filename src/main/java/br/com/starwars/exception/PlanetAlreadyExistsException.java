package br.com.starwars.exception;

public class PlanetAlreadyExistsException extends RuntimeException{
	public PlanetAlreadyExistsException(String message) {
        super(message);
    }
}