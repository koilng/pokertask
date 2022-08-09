package com.pokerhands.exception;

public class FiveSimilarCards extends RuntimeException {
    public FiveSimilarCards() {
        super();
    }

    public FiveSimilarCards(String message) {
        super(message);
    }

    public FiveSimilarCards(String message, Throwable cause) {
        super(message, cause);
    }

    public FiveSimilarCards(Throwable cause) {
        super(cause);
    }

    protected FiveSimilarCards(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
