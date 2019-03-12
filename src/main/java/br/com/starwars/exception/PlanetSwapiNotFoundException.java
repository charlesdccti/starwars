package br.com.starwars.exception;

public class PlanetSwapiNotFoundException extends RuntimeException{
	public PlanetSwapiNotFoundException(String message) {
        super(message);
    }
}