package com.br.edu.ifpb.deps.autoevents.exceptions;

public class InvalidTokenException extends RuntimeException {
    private static final long serialVerionUID = 1L;

    public InvalidTokenException(String message) {
        super(message);
    }

    public InvalidTokenException(String message, Throwable cause) {
        super(message, cause);
    }
}
