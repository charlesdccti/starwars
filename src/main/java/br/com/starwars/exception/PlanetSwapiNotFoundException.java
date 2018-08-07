package br.com.starwars.exception;

public class PlanetSwapiNotFoundException extends RuntimeException{

	private static final long serialVersionUID = -6426201250280223502L;

	public PlanetSwapiNotFoundException(String message) {
        super(message);
    }
}
