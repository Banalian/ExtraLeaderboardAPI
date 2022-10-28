package com.extraleaderboard.logic.exception;

public class NadeoRuntimeException extends RuntimeException {

    public NadeoRuntimeException(String message) {
        super(message);
    }

    public NadeoRuntimeException(String message, Throwable cause) {
        super(message, cause);
    }
}
