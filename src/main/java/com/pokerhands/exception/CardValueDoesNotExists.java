package com.pokerhands.exception;

public class CardValueDoesNotExists extends RuntimeException {
    public CardValueDoesNotExists() {
        super();
    }

    public CardValueDoesNotExists(String message) {
        super(message);
    }

    public CardValueDoesNotExists(String message, Throwable cause) {
        super(message, cause);
    }

    public CardValueDoesNotExists(Throwable cause) {
        super(cause);
    }

    protected CardValueDoesNotExists(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
