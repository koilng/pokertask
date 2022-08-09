package com.pokerhands;

import java.util.ArrayList;
import java.util.List;

public class PokerHandParser {
    public static List<Card> parseHand(String hand) {

        String[] cardsString = hand.split(" ");
        List<Card> parsedCards = new ArrayList<>();
        PokerHandValidator.ValidateHand(cardsString);

        for (String card : cardsString) {
            char value = card.charAt(0);
            char suit = card.charAt(1);

            parsedCards.add(
                    new Card(
                            CardMapper.mapCharToValue(value),
                            CardMapper.mapCharToSuit(suit))
            );
        }
        return parsedCards;
    }
}
