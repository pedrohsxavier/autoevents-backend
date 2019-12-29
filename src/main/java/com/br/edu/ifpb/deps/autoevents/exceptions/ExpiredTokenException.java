package com.br.edu.ifpb.deps.autoevents.exceptions;

public class ExpiredTokenException extends RuntimeException {
    private static final long serialVerionUID = 1L;

    public ExpiredTokenException(String message) {
        super(message);
    }

    public ExpiredTokenException(String message, Throwable cause) {
        super(message, cause);
    }
}
