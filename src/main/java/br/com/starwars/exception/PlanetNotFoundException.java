package br.com.starwars.exception;

public class PlanetNotFoundException extends RuntimeException{
	public PlanetNotFoundException(String message) {
        super(message);
    }
}