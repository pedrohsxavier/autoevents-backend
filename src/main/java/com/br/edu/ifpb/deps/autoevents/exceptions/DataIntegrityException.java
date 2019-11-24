package com.br.edu.ifpb.deps.autoevents.exceptions;

public class DataIntegrityException extends RuntimeException {
    private static final long serialVerionUID = 1L;

    public DataIntegrityException(String message) {
        super(message);
    }

    public DataIntegrityException(String message, Throwable cause) {
        super(message, cause);
    }
}
