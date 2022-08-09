package com.pokerhands.exception;

public class CardSuitDoesNotExists extends RuntimeException {
    public CardSuitDoesNotExists() {
        super();
    }

    public CardSuitDoesNotExists(String message) {
        super(message);
    }

    public CardSuitDoesNotExists(String message, Throwable cause) {
        super(message, cause);
    }

    public CardSuitDoesNotExists(Throwable cause) {
        super(cause);
    }

    protected CardSuitDoesNotExists(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
