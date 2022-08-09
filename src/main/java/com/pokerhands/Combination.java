package com.pokerhands;

public enum Combination {
    HIGH_HAND("1"),
    PAIR("2"),
    TWO_PAIRS("3"),
    THREE_OF_A_KIND("4"),
    STRAIGHT("5"),
    FLUSH("6"),
    FULL_HOUSE("7"),
    FOUR_OF_A_KIND("8"),
    STRAIGHT_FLUSH("9"),
    ROYAL_FLUSH("10");

    private final String value;

    Combination(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
