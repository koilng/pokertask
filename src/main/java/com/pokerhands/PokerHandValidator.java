package com.pokerhands;

import com.pokerhands.exception.CardLengthIsNotValid;
import com.pokerhands.exception.HandSizeIsNotValid;
import com.pokerhands.exception.FiveSimilarCards;

import java.util.Arrays;
import java.util.stream.Collectors;

public class PokerHandValidator {

    public static void ValidateHand(String[] cardList) {
        checkLength(cardList);
        checkFiveCards(cardList);
        checkHandSize(cardList);
    }

    private static void checkLength(String[] cardList) {
        Arrays.stream(cardList).forEach(e -> {
            if (e.length() != 2) {
                throw new CardLengthIsNotValid("Insufficient card length");
            }
        });
    }

    private static void checkFiveCards(String[] cardList) {
        Arrays.stream(cardList)
                .collect(Collectors.groupingBy(e -> e.charAt(0)))
                .values()
                .forEach(e -> {
                    if (e.size() == 5) {
                        throw new FiveSimilarCards("Wrong input hand: five similar cards");
                    }
                });
    }

    private static void checkHandSize(String[] cardList) {
        if (cardList.length != 5) {
            throw new HandSizeIsNotValid("Hand size must be 5 cards long");
        }
    }
}
