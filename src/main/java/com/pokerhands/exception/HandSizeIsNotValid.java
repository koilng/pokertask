package com.pokerhands.exception;

public class HandSizeIsNotValid extends RuntimeException {
    public HandSizeIsNotValid() {
        super();
    }

    public HandSizeIsNotValid(String message) {
        super(message);
    }

    public HandSizeIsNotValid(String message, Throwable cause) {
        super(message, cause);
    }

    public HandSizeIsNotValid(Throwable cause) {
        super(cause);
    }

    protected HandSizeIsNotValid(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
