package com.pokerhands;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CombinationParser {

    private final Map<Combination, Card.Value> combinations = new HashMap<>();

    public Map<Combination, Card.Value> parseCombinations(PokerHand pokerHand) {
        checkStraight(pokerHand);
        checkFlush(pokerHand);
        checkPairs(pokerHand);
        checkFullHouse();
        checkStraightFlush();
        checkRoyalFlush();
        checkHighHand(pokerHand);
        optimizeCombinations();
        return this.combinations;
    }

    private void optimizeCombinations() {
        List<Combination> keysToRemove = new ArrayList<>();
        Combination highestKey = getHighestCombination();

        this.combinations.keySet().forEach(key -> {
            if (!key.equals(highestKey)) {
                keysToRemove.add(key);
            }
        });

        keysToRemove.forEach(this.combinations::remove);
    }

    private Combination getHighestCombination() {
        Combination highestKey = Combination.HIGH_HAND;
        for (Combination key : this.combinations.keySet()) {
            if (Integer.parseInt(key.getValue()) > Integer.parseInt(highestKey.getValue())) {
                highestKey = key;
            }
        }
        return highestKey;
    }

    private void checkRoyalFlush() {
        if (combinations.containsKey(Combination.STRAIGHT_FLUSH)
                && combinations.get(Combination.STRAIGHT_FLUSH) == Card.Value.Ace) {

            combinations.put(Combination.ROYAL_FLUSH, combinations.get(Combination.STRAIGHT_FLUSH));

            combinations.remove(Combination.STRAIGHT_FLUSH);
        }
    }

    private void checkStraightFlush() {
        if (combinations.containsKey(Combination.STRAIGHT) && combinations.containsKey(Combination.FLUSH)) {

            combinations.put(Combination.STRAIGHT_FLUSH, combinations.get(Combination.STRAIGHT));

            combinations.remove(Combination.STRAIGHT);
            combinations.remove(Combination.FLUSH);
        }
    }

    private void checkFullHouse() {

        if (combinations.containsKey(Combination.PAIR) && combinations.containsKey(Combination.THREE_OF_A_KIND)) {

            combinations.put(Combination.FULL_HOUSE, combinations.get(Combination.THREE_OF_A_KIND));

            combinations.remove(Combination.PAIR);
            combinations.remove(Combination.THREE_OF_A_KIND);
        }
    }

    private void checkStraight(PokerHand pokerHand) {

        boolean isStraight = true;
        Card.Value highest = null;
        List<Card> cards = pokerHand.getCards();

        for (int slow = 0, fast = 1; fast < cards.size(); slow++, fast++) {

            Card slowCard = cards.get(slow);
            Card fastCard = cards.get(fast);

            if (highest == null) {
                highest = slowCard.getValue();
            }

            if (slowCard.getIntegerValue() + 1 != fastCard.getIntegerValue()) {
                isStraight = false;
                break;
            }

            if (highest.getValue() < fastCard.getIntegerValue()) {
                highest = fastCard.getValue();
            }

        }
        if (isStraight) {
            combinations.put(Combination.STRAIGHT, highest);
        }
    }

    private void checkHighHand(PokerHand pokerHand) {

        if (combinations.size() == 0) {

            pokerHand.getCards().forEach(e -> {
                if (combinations.containsKey(Combination.HIGH_HAND)) {

                    if (e.getIntegerValue() > combinations.get(Combination.HIGH_HAND).getValue()) {

                        combinations.replace(Combination.HIGH_HAND, e.getValue());
                    }
                } else {

                    combinations.put(Combination.HIGH_HAND, e.getValue());
                }
            });
        }
    }

    private void checkFlush(PokerHand pokerHand) {

        if (pokerHand.getAllSuits().size() == 1) {

            this.combinations.put(Combination.FLUSH, pokerHand.getHighCardValue());
        }
    }

    private void checkPairs(PokerHand pokerHand) {

        pokerHand.groupByValue().forEach(this::pairsHelper);
    }

    private void pairsHelper(List<Card.Value> valueList) {
        switch (valueList.size()) {
            case 2 -> {
                if (this.combinations.containsKey(Combination.PAIR) &&
                        this.combinations.get(Combination.PAIR) != valueList.get(0)) {

                    if (this.combinations.get(Combination.PAIR).getValue() < valueList.get(0).getValue()) {

                        this.combinations.put(Combination.TWO_PAIRS, valueList.get(0));
                        this.combinations.remove(Combination.PAIR);
                    } else {

                        this.combinations.put(Combination.TWO_PAIRS, this.combinations.get(Combination.PAIR));
                        this.combinations.remove(Combination.PAIR);
                    }
                    break;
                }

                this.combinations.put(Combination.PAIR, valueList.get(0));
            }

            case 3 -> this.combinations.put(Combination.THREE_OF_A_KIND, valueList.get(0));
            case 4 -> this.combinations.put(Combination.FOUR_OF_A_KIND, valueList.get(0));
        }
    }
}
