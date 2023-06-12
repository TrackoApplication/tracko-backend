package com.app.tracko.exception;

public class SystemUserNotFoundException extends Exception{
    public SystemUserNotFoundException() {
        super();
    }

    public SystemUserNotFoundException(String message) {
        super(message);
    }

    public SystemUserNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public SystemUserNotFoundException(Throwable cause) {
        super(cause);
    }

    protected SystemUserNotFoundException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

}
