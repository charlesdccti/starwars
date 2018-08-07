package br.com.starwars.exception;

public class PlanetNotFoundException extends RuntimeException{

	private static final long serialVersionUID = -6426201250280223502L;

	public PlanetNotFoundException(String message) {
        super(message);
    }
}
