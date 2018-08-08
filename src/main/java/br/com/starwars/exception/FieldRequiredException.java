package br.com.starwars.exception;

public class FieldRequiredException extends RuntimeException{

    private static final long serialVersionUID = -4284518722371426764L;

    public FieldRequiredException(String message) {
        super(message);
    }
}
