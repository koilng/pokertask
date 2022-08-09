package com.pokerhands;

import com.pokerhands.exception.CardSuitDoesNotExists;
import com.pokerhands.exception.CardValueDoesNotExists;

public class CardMapper {

    public static Card.Value mapCharToValue(char value) {
        return switch (value) {
            case '1' -> Card.Value.One;
            case '2' -> Card.Value.Two;
            case '3' -> Card.Value.Three;
            case '4' -> Card.Value.Four;
            case '5' -> Card.Value.Five;
            case '6' -> Card.Value.Six;
            case '7' -> Card.Value.Seven;
            case '8' -> Card.Value.Eight;
            case '9' -> Card.Value.Nine;
            case 'T' -> Card.Value.Ten;
            case 'J' -> Card.Value.Jack;
            case 'Q' -> Card.Value.Queen;
            case 'K' -> Card.Value.King;
            case 'A' -> Card.Value.Ace;
            default -> throw new CardValueDoesNotExists("Wrong card value");
        };
    }

    public static Card.Suit mapCharToSuit(char suit) {
        return switch (suit) {
            case 'S' -> Card.Suit.Spades;
            case 'H' -> Card.Suit.Hearts;
            case 'D' -> Card.Suit.Diamonds;
            case 'C' -> Card.Suit.Clubs;
            default -> throw new CardSuitDoesNotExists("Wrong card suit");
        };
    }
}
