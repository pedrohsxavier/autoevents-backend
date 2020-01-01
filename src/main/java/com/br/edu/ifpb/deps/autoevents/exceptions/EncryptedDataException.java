package com.br.edu.ifpb.deps.autoevents.exceptions;

public class EncryptedDataException extends RuntimeException {
    private static final long serialVerionUID = 1L;

    public EncryptedDataException(String message) {
        super(message);
    }

    public EncryptedDataException(String message, Throwable cause) {
        super(message, cause);
    }
}
