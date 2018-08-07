package br.com.starwars.exception;

public class PlanetAlreadyExistsException extends RuntimeException{

	private static final long serialVersionUID = -7017546561847822661L;

	public PlanetAlreadyExistsException(String message) {
        super(message);
    }
}
