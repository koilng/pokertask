package com.pokerhands.exception;

public class CardLengthIsNotValid extends RuntimeException {
    public CardLengthIsNotValid() {
        super();
    }

    public CardLengthIsNotValid(String message) {
        super(message);
    }

    public CardLengthIsNotValid(String message, Throwable cause) {
        super(message, cause);
    }

    public CardLengthIsNotValid(Throwable cause) {
        super(cause);
    }

    protected CardLengthIsNotValid(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
